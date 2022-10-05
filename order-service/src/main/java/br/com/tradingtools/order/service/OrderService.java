package br.com.tradingtools.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tradingtools.order.entity.PurchaseOrder;
import br.com.tradingtools.order.repository.OrderRepository;
import br.com.trandingtools.common.dto.OrderRequestDto;
import br.com.trandingtools.common.event.OrderStatus;
import br.com.trandingtools.common.event.PaymentStatus;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStatusPublisher publisher;

	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto dto) {
		PurchaseOrder order =  orderRepository.save(convertDtoToEntity(dto));
		dto.setOrderId(order.getId());
		
		publisher.publishOrderEvent(dto, OrderStatus.ORDER_CREATED); 		
		return order;
	}
	
	

	private PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		return new PurchaseOrder(
				0, 
				dto.getUserId().intValue(), 
				dto.getProductId(), 
				dto.getAmount(), 
				OrderStatus.ORDER_CREATED,
				PaymentStatus.PAYMENT_UNKNOWN);
	}



	public List<PurchaseOrder> getAllOrders() { 
		return orderRepository.findAll();
	}

}
