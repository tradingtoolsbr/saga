package br.com.trandingtools.common.event;

import java.util.Date;
import java.util.UUID;

import br.com.trandingtools.common.dto.OrderRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderEvent implements Event{
	
	private UUID eventId = UUID.randomUUID();
	private Date eventDate = new Date();
	private OrderRequestDto orderRequestDto;
	private OrderStatus orderStatus;
	
	@Override
	public UUID getEventID() {
		// TODO Auto-generated method stub
		return eventId;
	}
	
	@Override
	public  Date getEventDate() {
		// TODO Auto-generated method stub
		return eventDate;
	}
	
	public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
		super();
		this.orderRequestDto = orderRequestDto;
		this.orderStatus = orderStatus;
	}
}
