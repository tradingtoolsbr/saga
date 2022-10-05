package br.com.tradingtools.payment.service;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tradingtools.payment.entity.UserBalance;
import br.com.tradingtools.payment.entity.UserTransaction;
import br.com.tradingtools.payment.repository.UserBalanceRepository;
import br.com.tradingtools.payment.repository.UserTransactionRepository;
import br.com.trandingtools.common.dto.OrderRequestDto;
import br.com.trandingtools.common.dto.PaymentRequestDto;
import br.com.trandingtools.common.event.OrderEvent;
import br.com.trandingtools.common.event.PaymentEvent;
import br.com.trandingtools.common.event.PaymentStatus;

@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepository balanceRepository;
	@Autowired
	private UserTransactionRepository transactionRepossitory;

	@PostConstruct
	public void initUserBalanceInDB() {
		balanceRepository
				.saveAll(Stream
						.of(new UserBalance(101L, 3000.0), new UserBalance(102L, 33000.0),
								new UserBalance(103L, 2000.0), new UserBalance(104L, 999.9))
						.collect(Collectors.toList()));

	}

	/**
	 * //get the user id // check balance availability // if balance sufficient ->
	 * Payment completed and deduct amount form DB // if payment failed or not
	 * sufficient -> cancel order event and update the amount in DB
	 * 
	 * @param event
	 * @return
	 */

	@Transactional
	public PaymentEvent newOrderEvent(OrderEvent event) {
		
		OrderRequestDto orderDto = event.getOrderRequestDto();
		PaymentRequestDto paymentDto = new PaymentRequestDto(
				orderDto.getOrderId(), 
				orderDto.getUserId(), 
				orderDto.getAmount()
		);
		
		return balanceRepository.findById(orderDto.getUserId())
			.filter(balance-> balance.getPrice() > orderDto.getAmount())
			.map(balance ->{
				balance.setPrice(balance.getPrice()-orderDto.getAmount());
				transactionRepossitory.save(
						new UserTransaction(orderDto.getOrderId(), orderDto.getUserId(), orderDto.getAmount())
				);
				return new PaymentEvent(paymentDto, PaymentStatus.PAYMENT_COMPLETED);
			}).orElse(new PaymentEvent(paymentDto, PaymentStatus.PAYMENT_FAILED));
	}

	@Transactional
	public void CancelOrderEvent(OrderEvent event) {
		transactionRepossitory.findById(event.getOrderRequestDto().getUserId())
			.ifPresent(ut->{
				transactionRepossitory.delete(ut);
				transactionRepossitory.findById(ut.getUserId())
					.ifPresent(ub->ub.setAmount(ub.getAmount()+ut.getAmount()));
			});

	}
}
