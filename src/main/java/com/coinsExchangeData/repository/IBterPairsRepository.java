package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.BterPairModel;

public interface IBterPairsRepository extends JpaRepository<BterPairModel, Long> {

}

