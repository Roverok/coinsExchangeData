package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.MintPalPairModel;

public interface IMintPalPairsRepository extends JpaRepository<MintPalPairModel, Long> {

}

