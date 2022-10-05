package br.com.trandingtools.common.dto;

import br.com.trandingtools.common.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  OrderRequestDto {
	private Long userId;
	private Integer productId;
	private Double amount;
	private Integer orderId;
	private OrderStatus OrderStatus;
}

