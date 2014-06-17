package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.BtcEPairModel;

public interface IBtcEPairsRepository extends JpaRepository<BtcEPairModel, Long> {

}

