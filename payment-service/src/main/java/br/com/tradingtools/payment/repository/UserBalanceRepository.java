package br.com.tradingtools.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tradingtools.payment.entity.UserBalance;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long>{

}
