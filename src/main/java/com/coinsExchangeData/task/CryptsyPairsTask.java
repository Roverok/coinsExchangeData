package com.coinsExchangeData.task;

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
import com.coinsExchangeData.model.CryptsyPairModel;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.ICryptsytPairsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("cryptsyPairsTask")
public class CryptsyPairsTask extends PairsTask implements IPairsTask, Callable<Void> {
	
	@Autowired
	private ICryptsytPairsRepository cryptsytPairsRepository;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@PersistenceContext
	EntityManager em;
	@Autowired
	private QueryManager queryManager; 
	
	private String[] markets  = new String[] {"LTC/BTC", "DOGE/BTC", 
				"PPC/BTC", "LTC/USD", "DRK/BTC", "NMC/BTC", 
				"PTS/BTC", "QRK/BTC", "XPM/BTC", "FTC/BTC", "DOGE/LTC",
				"PPC/LTC", "QRK/LTC", "ZET/LTC", "XPM/LTC", "ZET/BTC"};
	private List<IPair> pairs = new ArrayList<IPair>();
	private String[] symbolsToInterpolate = new String[] {
			"nmc_ltc", 
			"drk_ltc", 
			"pts_ltc", 
			"ftc_ltc", 
			"ppc_doge", 
			"nmc_doge",
			"drk_doge",
			"pts_doge",
			"qrk_doge",
			"zet_doge",
			"xpm_doge",
			"ftc_doge",
			"drk_nmc",
			"pts_nmc",
			"qrk_nmc",
			"zet_nmc",
			"xpm_nmc",
			"ftc_nmc",
			"pts_drk",
			"qrk_drk",
			"zet_drk",
			"xpm_drk",
			"ftc_drk",
			"qrk_pts",
			"zet_pts",
			"xpm_pts",
			"ftc_pts",
			"zet_qrk",
			"xpm_qrk",
			"ftc_qrk",
			"xpm_zet",
			"ftc_zet",
			"ftc_xpm"
			};
	
	public Void call() throws Exception {
		this.getPairs();
		return null;
	}

	public void getPairs() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			
			JsonNode root = mapper.readTree(new URL("http://pubapi.cryptsy.com/api.php?method=marketdatav2"));
			for (int i = 0; i < markets.length; i++) {
				String market = markets[i];
				JsonNode marketNode = root.get("return").get("markets").get(market);
				
				String label = marketNode.get("label").asText();
				
				PairModel pm = queryManager.findPairByName(label);
				
				CryptsyPairModel cryptsyPairModel = new CryptsyPairModel();
				
				cryptsyPairModel.setPair(pm);
				cryptsyPairModel.setLabel(marketNode.get("label").asText());
				cryptsyPairModel.setLasttradeprice(marketNode.get("lasttradeprice").asDouble());
				cryptsyPairModel.setLasttradetime(marketNode.get("lasttradetime").asText());
				cryptsyPairModel.setMarketid(marketNode.get("marketid").asInt());
				cryptsyPairModel.setPrimarycode(marketNode.get("primarycode").asText());
				cryptsyPairModel.setPrimaryname(marketNode.get("primaryname").asText());
				cryptsyPairModel.setSecondaryname(marketNode.get("secondaryname").asText());
				cryptsyPairModel.setVolume(marketNode.get("volume").asDouble());
				
				cryptsytPairsRepository.save(cryptsyPairModel);
				pairs.add(cryptsyPairModel);
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
		for (int i = 0; i < symbolsToInterpolate.length; i++) {
			
			String symbol = symbolsToInterpolate[i];
			String[] coins = symbol.split("_");
			
			double coin1Price   = this.getLasttradePriceBuyCoinName(coins[0], pairs);
			double coin2Price   = this.getLasttradePriceBuyCoinName(coins[1], pairs);
			double interpolated = coin1Price / coin2Price; 
			
			// get the pair object.
			PairModel pm = queryManager.findPairBySymbol(symbol);
			
			CryptsyPairModel cryptsyPairModel = new CryptsyPairModel();
			
			cryptsyPairModel.setPair(pm);
			cryptsyPairModel.setLabel("");
			cryptsyPairModel.setLasttradeprice(interpolated);
			cryptsyPairModel.setLasttradetime("");
			cryptsyPairModel.setMarketid(0);
			cryptsyPairModel.setPrimarycode("");
			cryptsyPairModel.setPrimaryname("");
			cryptsyPairModel.setSecondaryname("");
			cryptsyPairModel.setVolume(0);
			
			cryptsytPairsRepository.save(cryptsyPairModel);
			pairs.add(cryptsyPairModel);
		}
		
	}

}
