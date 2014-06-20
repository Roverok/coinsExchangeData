package com.coinsExchangeData.task.pair;

import java.io.IOException;
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
import com.coinsExchangeData.model.BterPairModel;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.IBterPairsRepository;
import com.coinsExchangeData.repository.IPairsRepository;
import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.bter.BTERExchange;
import com.xeiam.xchange.bter.dto.marketdata.BTERTicker;
import com.xeiam.xchange.bter.service.polling.BTERPollingMarketDataServiceRaw;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.service.polling.PollingMarketDataService;

@Component("bterPairsTask")
public class BterPairsTask extends PairsTask implements IPairsTask, Callable<Void> {
	
	@Autowired
	private IBterPairsRepository bterPairsRepository;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@Autowired
	private IPairsRepository pairsRepository;
	@PersistenceContext
	EntityManager em;
	@Autowired
	QueryManager queryManager;
	
	private List<IPair> pairs = new ArrayList<IPair>();
	private String[] symbolsToInterpolate = new String[] {
			"nmc_ppc", 
			"drk_ppc", 
			"pts_ppc", 
			"qrk_ppc",
			"zet_ppc",
			"xpm_ppc",
			"ftc_ppc"
			};
	
	public Void call() throws Exception {
		this.getPairs();
		return null;
	}

	public void getPairs() {
		Map<String, Boolean> pairsMap = new HashMap<String, Boolean>();
		pairsMap.put("PPC/BTC", true);
		pairsMap.put("DRK/BTC", true);
		pairsMap.put("NMC/BTC", true);
		pairsMap.put("PTS/BTC", true);
		pairsMap.put("QRK/BTC", true);
		pairsMap.put("ZET/BTC", true);
		pairsMap.put("XMP/BTC", true);
		pairsMap.put("FTC/BTC", true);
		
		Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BTERExchange.class.getName());
	    PollingMarketDataService marketDataService = exchange.getPollingMarketDataService();
	    
	    Map<CurrencyPair, BTERTicker> tickers = null;
	    
	    try {
			tickers = ((BTERPollingMarketDataServiceRaw)marketDataService).getBTERTickers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (Map.Entry<CurrencyPair, BTERTicker> entry : tickers.entrySet()) {
	    	CurrencyPair pair = entry.getKey();
	    	BTERTicker object = entry.getValue();
	    	
	    	String pairName = pair.toString();
	    	
	    	if (pairsMap.containsKey(pairName)) {
	    		
	    		PairModel pm = queryManager.findPairByName(pairName);
	    		
	    		BterPairModel bterPairModel = new BterPairModel();
				
	    		bterPairModel.setPair(pm);
				
	    		bterPairModel.setAvg(object.getAvg().doubleValue());
	    		bterPairModel.setBuy(object.getBuy().doubleValue());
	    		bterPairModel.setHigh(object.getHigh().doubleValue());
	    		bterPairModel.setLast(object.getLast().doubleValue());
	    		bterPairModel.setLow(object.getLow().doubleValue());
	    		bterPairModel.setSell(object.getSell().doubleValue());
	    		bterPairModel.setVolBqc(object.getPriceCurrencyVolume().doubleValue());
	    		bterPairModel.setVolBtc(object.getTradeCurrencyVolume().doubleValue());
				
				bterPairsRepository.save(bterPairModel);
				pairs.add(bterPairModel);
	    	}
	    }
	    this.interpolate();
    	marketsResourcesManager.addMarketsResource(pairs);
		
	}

	public void interpolate() {
		for (int i = 0; i < symbolsToInterpolate.length; i++) {
			String symbol = symbolsToInterpolate[i];
			String[] coins = symbol.split("_");
			double coin1Price   = this.getLasttradePriceBuyCoinName(coins[0], pairs);
			double coin2Price   = this.getLasttradePriceBuyCoinName(coins[1], pairs);
			double interpolated = coin1Price / coin2Price; 
			
			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbol);
			
			BterPairModel bterPairModel = new BterPairModel();
			
			bterPairModel.setPair(pm);
			
			bterPairModel.setAvg(0);
			bterPairModel.setBuy(0);
			bterPairModel.setHigh(0);
			bterPairModel.setLast(interpolated);
			bterPairModel.setLow(0);
			bterPairModel.setSell(0);
			bterPairModel.setVolBqc(0);
			bterPairModel.setVolBtc(0);
			
			bterPairsRepository.save(bterPairModel);
			pairs.add(bterPairModel);
		}
		
	}

}
