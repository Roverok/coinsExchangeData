package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsExchangeData.model.CoinModel;

public interface ICoinRepository extends JpaRepository<CoinModel, Long> {

}
