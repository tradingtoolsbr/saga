package br.com.tradingtools.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tradingtools.payment.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long>{

}
