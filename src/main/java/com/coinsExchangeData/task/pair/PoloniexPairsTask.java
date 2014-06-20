package com.coinsExchangeData.task.pair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IPair;
import com.coinsExchangeData.contract.IPairsTask;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.model.PoloniexPairModel;
import com.coinsExchangeData.repository.IPoloniexPairsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("poloniexPairsTask")
public class PoloniexPairsTask extends PairsTask implements IPairsTask, Callable<Void> {
	
	@Autowired
	private IPoloniexPairsRepository poloniexPairsRepository;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@Autowired
	private QueryManager queryManager;
	@PersistenceContext
	EntityManager em;
	
	private String[] symbols               = new String[] {"DRK_BTC", "NMC_BTC", "PTS_BTC", "XPM_BTC"};
	private List<IPair> pairs              = new ArrayList<IPair>();
	private String[] symbolsToInterpolate  = new String[] {"pts_drk", "xpm_drk", "drk_nmc", "xpm_nmc"};
	
	private String formatSymbol(String symbol) {
		String[] parts = symbol.split("_");
		return parts[1] + "_" + parts[0];
	}
	
	public Void call() throws Exception {
		this.getPairs();
		return null;
	}

	public void getPairs() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			JsonNode root = mapper.readTree(new URL("https://poloniex.com/public?command=returnTicker"));
			
			for (int i = 0; i < symbols.length; i++) {
				String symbol = symbols[i];
				
				JsonNode pairNode = root.get(formatSymbol(symbol));
				
				symbol = symbol.toLowerCase();
		
				// get the pair object.
				PairModel pm = queryManager.findPairBySymbol(symbol);
				
				PoloniexPairModel poloniexPairModel = new PoloniexPairModel();
				
				poloniexPairModel.setPair(pm);
				poloniexPairModel.setBaseVolume(pairNode.get("baseVolume").asDouble());
				poloniexPairModel.setHighestBid(pairNode.get("highestBid").asDouble());
				poloniexPairModel.setIsFrozen(pairNode.get("isFrozen").asInt());
				poloniexPairModel.setLast(pairNode.get("last").asDouble());
				poloniexPairModel.setLowestAsk(pairNode.get("lowestAsk").asDouble());
				poloniexPairModel.setPercentChange(pairNode.get("percentChange").asDouble());
				poloniexPairModel.setQuoteVolume(pairNode.get("quoteVolume").asDouble());
				
				poloniexPairsRepository.save(poloniexPairModel);
				pairs.add(poloniexPairModel);
			}
			
			this.interpolate();
			marketsResourcesManager.addMarketsResource(pairs);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void interpolate() {
		for (int i = 0; i < symbolsToInterpolate.length; i++) {
			String symbol = symbolsToInterpolate[i];
			String[] coins = symbol.split("_");
			double coin1Price   = this.getLasttradePriceBuyCoinName(coins[0], pairs);
			double coin2Price   = this.getLasttradePriceBuyCoinName(coins[1], pairs);
			double interpolated = coin1Price / coin2Price; 
			
			if (Double.isNaN(interpolated) == true) 
				continue;
			
			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbol);
			
			PoloniexPairModel poloniexPairModel = new PoloniexPairModel();
			
			poloniexPairModel.setPair(pm);
			poloniexPairModel.setBaseVolume(0);
			poloniexPairModel.setHighestBid(0);
			poloniexPairModel.setIsFrozen(0);
			poloniexPairModel.setLast( interpolated );
			poloniexPairModel.setLowestAsk(0);
			poloniexPairModel.setPercentChange(0);
			poloniexPairModel.setQuoteVolume(0);
			
			poloniexPairsRepository.save(poloniexPairModel);
			pairs.add(poloniexPairModel);
		}
		
	}

}
