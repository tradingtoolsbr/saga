package br.com.tradingtools.payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_TRANSACTION_TB")
public class UserTransaction {
	
	@Id
	private Integer id;
	private Long userId;	
	private Double amount;

	
	
 
}
