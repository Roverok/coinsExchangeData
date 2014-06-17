package com.coinsExchangeData.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IHeatMapService;
import com.coinsExchangeData.dto.HeatMapDTO;
import com.coinsExchangeData.model.PairAggregatedModel;

@Service("heatMapService")
public class HeatMapService implements IHeatMapService {
	
	@Autowired
	private QueryManager queryManager;
	
	public HeatMapDTO getHeatMap() {
		
		Calendar toady     = Calendar.getInstance();
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		
		List<PairAggregatedModel> pairAggregatedModelsToday     = queryManager.findPairAggregatedModelsByDate(toady.getTime());
		List<PairAggregatedModel> pairAggregatedModelsYesterday = queryManager.findPairAggregatedModelsByDate(yesterday.getTime());
		
		HeatMapDTO heatMapDTO= new HeatMapDTO();
		heatMapDTO.setData(pairAggregatedModelsToday);
		
		return heatMapDTO;
		
	}

}
