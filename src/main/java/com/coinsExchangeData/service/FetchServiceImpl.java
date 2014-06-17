package com.coinsExchangeData.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IBtcEService;
import com.coinsExchangeData.contract.IBterService;
import com.coinsExchangeData.contract.ICryptsyService;
import com.coinsExchangeData.contract.IFetchService;
import com.coinsExchangeData.contract.IMintPalService;
import com.coinsExchangeData.contract.IPair;
import com.coinsExchangeData.contract.IPoloniexService;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairAggregatedModel;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.IPairAggregatedRepository;

@Service("fetchService")
public class FetchServiceImpl{
	
	/*@Autowired
	private IBtcEService btcEService;
	@Autowired
	private ICryptsyService cryptsyService;
	@Autowired
	private IMintPalService mintPalService;
	@Autowired
	private IBterService bterService;
	@Autowired
	private IPoloniexService poloniexService;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@Autowired
	private IPairAggregatedRepository pairAggregatedRepository;
	@Autowired
	private QueryManager queryManager;
	@PersistenceContext
	EntityManager em;
	
	private String[] symbols = new String[] {
		"ltc_btc",
		
		"doge_btc",
		"doge_ltc",
		
		"ppc_btc",
		"ppc_ltc",
		"ppc_doge",
		
		"nmc_btc",
		"nmc_ltc",
		"nmc_doge",
		"nmc_ppc",
		
		"drk_btc",
		"drk_ltc",
		"drk_doge",
		"drk_ppc",
		"drk_nmc",
		
		"pts_btc",
		"pts_ltc",
		"pts_doge",
		"pts_ppc",
		"pts_nmc",
		"pts_drk",
		
		"qrk_btc",
		"qrk_ltc",
		"qrk_doge",
		"qrk_ppc",
		"qrk_nmc",
		"qrk_drk",
		"qrk_pts",
		
		"zet_btc",
		"zet_ltc",
		"zet_doge",
		"zet_ppc",
		"zet_nmc",
		"zet_drk",
		"zet_pts",
		"zet_qrk",
		
		"xpm_btc",
		"xpm_ltc",
		"xpm_doge",
		"xpm_ppc",
		"xpm_nmc",
		"xpm_drk",
		"xpm_pts",
		"xpm_qrk",
		"xpm_zet",
		
		"ftc_btc",
		"ftc_ltc",
		"ftc_doge",
		"ftc_ppc",
		"ftc_nmc",
		"ftc_drk",
		"ftc_pts",
		"ftc_qrk",
		"ftc_zet",
		"ftc_xpm"
	};*/
	
	public void fetch() {
		
		/*btcEService.fetch();
		cryptsyService.fetch();
		mintPalService.fetch();
		bterService.fetch();
		poloniexService.fetch();
		
		for (int i = 0; i < symbols.length; i++) {
			String symbol = symbols[i];
			createPairAggregatedModel(symbol);
		}	*/	
		
	}
	
	/*public void createPairAggregatedModel(String symbol) {
		
		int found             = 0;
		double lastTradePrice = 0;
		double volume         = 0;
		
		for  (List<IPair> marketResourceList : marketsResourcesManager.getMarketsResourcesList()) {
			for (IPair pair : marketResourceList) {
				if (pair.getSymbol().equals(symbol)) {
					lastTradePrice += pair.getLastTradePrice();
					volume         += pair.getVolume();
					found++;
					break;
				}
			}
		}
		
		if (found == 0) return;
		
		PairAggregatedModel pairAggregatedModel = new PairAggregatedModel();
		
		PairModel pm = queryManager.findPairBySymbol(symbol);
		
		pairAggregatedModel.setPair(pm);

		double lastTradePriceAvg = lastTradePrice / found;
		double volumeAvg         = volume / found;
		
		pairAggregatedModel.setLastTradePriceAvg(lastTradePriceAvg);
		pairAggregatedModel.setVolumeAvg(volumeAvg);
		
		pairAggregatedRepository.save(pairAggregatedModel);
	}*/

}
