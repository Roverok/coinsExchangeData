package com.coinsExchangeData.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coinsExchangeData.contract.IPair;

@Component("marketsResourcesManager")
public class MarketsResourcesManager {
	
	private List<List<IPair>> pairsResourcesList = new ArrayList<List<IPair>>();
	
	public List<List<IPair>> getMarketsResourcesList() {
		return pairsResourcesList;
	}
	
	public void addMarketsResource(List<IPair> marketResource) {
		this.pairsResourcesList.add(marketResource);
	}
}
