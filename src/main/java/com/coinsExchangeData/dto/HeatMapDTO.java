package com.coinsExchangeData.dto;

import java.util.List;

import com.coinsExchangeData.model.PairAggregatedModel;

public class HeatMapDTO {
	private List<PairAggregatedModel> data;

	public List<PairAggregatedModel> getData() {
		return data;
	}

	public void setData(List<PairAggregatedModel> pairs) {
		this.data = pairs;
	}
}
