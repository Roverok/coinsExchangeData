package com.coinsExchangeData.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.coinsExchangeData.model.PairAggregatedModel;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.model.ProviderModel;

@Component
public class QueryManager {
	
	@PersistenceContext
	EntityManager em;
	
	public PairModel findPairBySymbol(String symbol) {
		return em.createNamedQuery("PairModel.findPairBySymbol", PairModel.class)
		.setParameter("symbol", symbol)
		.getSingleResult();
	}
	
	public PairModel findPairByName(String name) {
		return em.createNamedQuery("PairModel.findPairByName", PairModel.class)
		.setParameter("name", name)
		.getSingleResult();
	}
	
	public List<PairAggregatedModel> findPairAggregatedModelsByDate(Date date) {
		return em.createNamedQuery("PairAggregatedModel.findByDate", PairAggregatedModel.class)
			.setParameter("date", date, TemporalType.DATE)
			.getResultList();
	}
	
	public ProviderModel findProviderByName(String name) {
		return em.createNamedQuery("ProviderModel.findByName", ProviderModel.class)
			.setParameter("name", name)
			.getSingleResult();
	}

}
