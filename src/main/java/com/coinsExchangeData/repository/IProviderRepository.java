package com.coinsExchangeData.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coinsExchangeData.model.ProviderModel;

public interface IProviderRepository extends JpaRepository<ProviderModel, Long> {

}
