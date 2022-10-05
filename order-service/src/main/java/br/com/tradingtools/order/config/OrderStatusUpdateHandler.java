package br.com.tradingtools.order.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.tradingtools.order.entity.PurchaseOrder;
import br.com.tradingtools.order.repository.OrderRepository;
import br.com.tradingtools.order.service.OrderStatusPublisher;
import br.com.trandingtools.common.dto.OrderRequestDto;
import br.com.trandingtools.common.event.OrderStatus;
import br.com.trandingtools.common.event.PaymentStatus;

@Configuration
public class OrderStatusUpdateHandler {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderStatusPublisher publisher;;

	public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {

		orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));

	}

	private void updateOrder(PurchaseOrder purchaseOrder) {
		boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getOrderStatus());
		OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELED;

		if (isPaymentComplete) {
			publisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
		}

	}

	private OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
		OrderRequestDto  dto = new OrderRequestDto(
				purchaseOrder.getUserId().longValue(), 
				purchaseOrder.getProductId(), 
				purchaseOrder.getPrice(), 
				purchaseOrder.getId(), 
				null);
		
		return dto;
	}
}
