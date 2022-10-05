package br.com.tradingtools.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tradingtools.order.repository.OrderRepository;
import br.com.trandingtools.common.dto.OrderRequestDto;
import br.com.trandingtools.common.event.OrderEvent;
import br.com.trandingtools.common.event.OrderStatus;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

	@Autowired
	private OrderRepository orderRepository;
	
	private Sinks.Many<OrderEvent> orderSinks;

	public void publishOrderEvent(OrderRequestDto dto, OrderStatus orderStatus) {

		OrderEvent event = new OrderEvent(dto, orderStatus);
		orderSinks.tryEmitNext(event);
	} 
}
