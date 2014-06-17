package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsExchangeData.model.PairAggregatedModel;

public interface IPairAggregatedRepository extends JpaRepository<PairAggregatedModel, Long> { 

}
