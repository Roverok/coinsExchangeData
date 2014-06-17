package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.AbstractOrder;

public interface IAbstractOrderRepository  extends JpaRepository<AbstractOrder, Long> {

}
