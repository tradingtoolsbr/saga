package br.com.tradingtools.order.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.trandingtools.common.event.OrderEvent;
import br.com.trandingtools.common.event.PaymentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class EventConsumerConfig {
	
	https://www.youtube.com/watch?v=6O5iJ7PKUhs&list=PLVz2XdJiJQxw1H3JVhclHc__WYDaiS1uL&index=3https://www.youtube.com/watch?v=6O5iJ7PKUhs&list=PLVz2XdJiJQxw1H3JVhclHc__WYDaiS1uL&index=3
	1:13
	
	@Autowired
	private OrderStatusUpdateHandler handler;
	
	public Consumer<PaymentEvent> paymentEventConsumer(){

		// Listen payment-event-topic
		// will check payment status
		// if payment status complete -> complete the order
		// if payment status failed -> cancel the order
		return (payment) -> handler.updateOrder(payment.getPaymentRequestDto().getOrderId(), 
				po -> {
					po.setPaymentStatus(payment.getPaymentStatus());
				});		
	}

	@Bean
	public Sinks.Many<OrderEvent> orderSinks(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}
	
	@Bean
	public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks){
		return sinks::asFlux;
	}
}
