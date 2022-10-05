package br.com.trandingtools.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  OrderPayment {
	private Integer userId; 
	private Integer amount;
	private Integer orderId;
}
