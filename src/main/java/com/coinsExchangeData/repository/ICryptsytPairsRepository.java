package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.CryptsyPairModel;

public interface ICryptsytPairsRepository extends JpaRepository<CryptsyPairModel, Long> {

}

