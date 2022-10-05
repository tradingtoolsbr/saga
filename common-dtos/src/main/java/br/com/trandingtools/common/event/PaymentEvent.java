package br.com.trandingtools.common.event;

import java.util.Date;
import java.util.UUID;

import br.com.trandingtools.common.dto.PaymentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentEvent implements Event{
	
	private UUID eventId = UUID.randomUUID();
	private Date eventDate = new Date();
	private PaymentRequestDto paymentRequestDto;
	private PaymentStatus paymentStatus;
	
	@Override
	public UUID getEventID() { 
		return eventId;
	}

	@Override
	public Date getEventDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
		super();
		this.paymentRequestDto = paymentRequestDto;
		this.paymentStatus = paymentStatus;
	}
	
	
	
	
	 
	

}
