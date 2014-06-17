package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coinsExchangeData.model.PairModel;

public interface IPairsRepository extends JpaRepository<PairModel, Long> {

}
