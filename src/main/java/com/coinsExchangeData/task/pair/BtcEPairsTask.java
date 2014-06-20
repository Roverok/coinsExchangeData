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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IPair;
import com.coinsExchangeData.contract.IPairsTask;
import com.coinsExchangeData.model.BtcEPairModel;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.model.maping.BtcE;
import com.coinsExchangeData.repository.IBtcEPairsRepository;
import com.coinsExchangeData.repository.IPairsRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("btcEPairsTask")
public class BtcEPairsTask extends PairsTask implements IPairsTask, Callable<Void> {
	
	@Autowired
	private IBtcEPairsRepository btcEPairsRepository;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@Autowired
	private IPairsRepository pairsRepository;
	@Autowired
	private QueryManager queryManager;
	@PersistenceContext
	EntityManager em;
	
	private String[] symbols               = new String[] {"ltc_btc", "ppc_btc", "nmc_btc", "ftc_btc"};
	private List<IPair> pairs              = new ArrayList<IPair>();
	private String[] symbolsToInterpolated = new String[] {"nmc_ltc", "ftc_ltc", "nmc_ppc", "ftc_nmc"};
	
	public Void call() throws Exception {
		
		this.getPairs();
		return null;
	}
	
	public void getPairs() {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		for (int i = 0; i < symbols.length; i++) {	
			URL jsonUrl = null;
			
			try {
				jsonUrl = new URL("https://btc-e.com/api/2/" + symbols[i] + "/ticker");
			} catch (MalformedURLException e1) {
				
				e1.printStackTrace();
			}
			BtcE btce = null;
			try {
				btce = mapper.readValue(jsonUrl, BtcE.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbols[i]);
			
			BtcEPairModel btcEPairModel = new BtcEPairModel();
			
			btcEPairModel.setPair(pm);
			btcEPairModel.setAvg(btce.getTicker().getAvg());
			btcEPairModel.setBuy(btce.getTicker().getBuy());
			btcEPairModel.setHigh(btce.getTicker().getHigh());
			btcEPairModel.setLast(btce.getTicker().getLast());
			btcEPairModel.setLow(btce.getTicker().getLow());
			btcEPairModel.setSell(btce.getTicker().getSell());
			btcEPairModel.setServer_time(btce.getTicker().getServer_time());
			btcEPairModel.setUpdated(btce.getTicker().getUpdated());
			btcEPairModel.setVol(btce.getTicker().getVol());
			btcEPairModel.setVol_cur(btce.getTicker().getVol_cur());
			
			btcEPairsRepository.save(btcEPairModel);
			pairs.add(btcEPairModel);
		}
		
		this.interpolate();
		marketsResourcesManager.addMarketsResource(pairs);
	}
	
	public void interpolate() {
		for (int i = 0; i < symbolsToInterpolated.length; i++) {
			
			String symbol  = symbolsToInterpolated[i];
			String[] coins = symbol.split("_");
			
			double coin1Price   = this.getLasttradePriceBuyCoinName(coins[0], pairs);
			double coin2Price   = this.getLasttradePriceBuyCoinName(coins[1], pairs);
			double interpolated = coin1Price / coin2Price; 
			
			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbol);
			
			BtcEPairModel btcEPairModel = new BtcEPairModel();		
			btcEPairModel.setPair(pm);
			btcEPairModel.setAvg(0);
			btcEPairModel.setBuy(0);
			btcEPairModel.setHigh(0);
			btcEPairModel.setLast(interpolated);
			btcEPairModel.setLow(0);
			btcEPairModel.setSell(0);
			btcEPairModel.setServer_time(0);
			btcEPairModel.setUpdated(0);
			btcEPairModel.setVol(0);
			btcEPairModel.setVol_cur(0);
			
			btcEPairsRepository.save(btcEPairModel);
			pairs.add(btcEPairModel);
			
		}
	}

}
