package com.coinsExchangeData.task.pair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IPair;
import com.coinsExchangeData.contract.IPairsTask;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.MintPalPairModel;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.IMintPalPairsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("mintPalPairsTask")
public class MintPalPairsTask extends PairsTask implements IPairsTask, Callable<Void> {
	
	@Autowired
	private IMintPalPairsRepository mintPalPairsRepository;
	@Autowired
	private QueryManager queryManager;
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	
	private List<IPair> pairs              = new ArrayList<IPair>();
	private String[] symbolsToInterpolated = new String[] {"drk_doge", "zet_doge"};
	
	public Void call() throws Exception {
		this.getPairs();
		return null;
	}

	public void getPairs() {
		Map<String, String> pairsMap = new HashMap<String, String>();
		pairsMap.put("LTC", "BTC");
		pairsMap.put("DOGE", "BTC");
		pairsMap.put("DRK", "BTC");
		pairsMap.put("ZET", "BTC");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			JsonNode root = mapper.readTree(new URL("https://api.mintpal.com/v2/market/summary/"));
			JsonNode dataNode = root.get("data");
			
			for (Map.Entry<String, String> entry : pairsMap.entrySet()) {
			    String key   = entry.getKey();
			    String value = entry.getValue();
			    
			    for (JsonNode jsonNode : dataNode) {
					
			    	String code     = jsonNode.get("code").asText();
					String exchange = jsonNode.get("exchange").asText();
					
					if (code.equals(key) && exchange.equals(value)) {
						
						String pairName = code + "/" + exchange;						
						
						// get the pair object.
						PairModel pm = queryManager.findPairByName(pairName);
						
						MintPalPairModel mintPalPairModel = new MintPalPairModel();
						
						mintPalPairModel.setPair(pm);
						mintPalPairModel.set_24hhigh(jsonNode.get("24hhigh").asDouble());
						mintPalPairModel.set_24hlow(jsonNode.get("24hlow").asDouble());
						mintPalPairModel.set_24hvol(jsonNode.get("24hvol").asDouble());
						mintPalPairModel.set_change(jsonNode.get("change").asText());
						mintPalPairModel.setCode(jsonNode.get("code").asText());
						mintPalPairModel.setExchange(jsonNode.get("exchange").asText());
						mintPalPairModel.setLastPrice(jsonNode.get("last_price").asDouble());
						mintPalPairModel.setMarketId(jsonNode.get("market_id").asInt());
						mintPalPairModel.setTopAsk(jsonNode.get("top_ask").asDouble());
						mintPalPairModel.setTopBid(jsonNode.get("top_bid").asDouble());
						mintPalPairModel.setYesterdayPrice(jsonNode.get("yesterday_price").asDouble());
						
						mintPalPairsRepository.save(mintPalPairModel);
						pairs.add(mintPalPairModel);
					}
					
				}
			    
			}
			
			this.interpolate();
			marketsResourcesManager.addMarketsResource(pairs);
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void interpolate() {
		for (int i = 0; i < symbolsToInterpolated.length; i++) {
			String symbol = symbolsToInterpolated[i];
			String[] coins = symbol.split("_");
			double coin1Price   = this.getLasttradePriceBuyCoinName(coins[0], pairs);
			double coin2Price   = this.getLasttradePriceBuyCoinName(coins[1], pairs);
			double interpolated = coin1Price / coin2Price; 
			
			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbol);
			
			MintPalPairModel mintPalPairModel = new MintPalPairModel();
			
			mintPalPairModel.setPair(pm);
			mintPalPairModel.set_24hhigh(0);
			mintPalPairModel.set_24hlow(0);
			mintPalPairModel.set_24hvol(0);
			mintPalPairModel.set_change("");
			mintPalPairModel.setCode("");
			mintPalPairModel.setExchange("");
			mintPalPairModel.setLastPrice(interpolated);
			mintPalPairModel.setMarketId(0);
			mintPalPairModel.setTopAsk(0);
			mintPalPairModel.setTopBid(0);
			mintPalPairModel.setYesterdayPrice(0);
			
			mintPalPairsRepository.save(mintPalPairModel);
			pairs.add(mintPalPairModel);
		}
		
	}
	
}
