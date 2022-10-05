package br.com.tradingtools.payment.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tradingtools.payment.service.PaymentService;
import br.com.trandingtools.common.event.OrderEvent;
import br.com.trandingtools.common.event.OrderStatus;
import br.com.trandingtools.common.event.PaymentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PaymentConsumerConfig {
	
	@Autowired
	private PaymentService paymentService;
	
	@Bean
	public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor (){
		return OrderEventFlux -> OrderEventFlux.flatMap(this::processPayment);
	}
	
	private Mono<PaymentEvent> processPayment(OrderEvent event){
		//get the user id
		// check balance availability
		// if balance sufficient -> Payment completed and deduct amount form DB
		// if payment failed or not sufficient -> cancel order event and update the amount in DB
		
		if (OrderStatus.ORDER_CREATED.equals(event.getOrderStatus())) {
			return Mono.fromSupplier(()-> this.paymentService.newOrderEvent(event));
		}
		return null;
		
	}

}
