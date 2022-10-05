package br.com.trandingtools.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

	private Integer orderId;
	private Long userId;
	private Double amount;
}
