package br.com.tradingtools.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tradingtools.order.entity.PurchaseOrder;
import br.com.tradingtools.order.service.OrderService;
import br.com.trandingtools.common.dto.OrderRequestDto;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public PurchaseOrder createOrder(@RequestBody OrderRequestDto dto) {
		return orderService.createOrder(dto);
		
	}

	@GetMapping
	public List<PurchaseOrder> getOrders(){
		return orderService.getAllOrders();
	}
}
