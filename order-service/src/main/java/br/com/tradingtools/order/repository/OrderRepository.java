package br.com.tradingtools.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tradingtools.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
