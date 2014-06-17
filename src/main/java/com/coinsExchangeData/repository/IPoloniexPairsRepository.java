package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.PoloniexPairModel;

public interface IPoloniexPairsRepository extends JpaRepository<PoloniexPairModel, Long> {

}

