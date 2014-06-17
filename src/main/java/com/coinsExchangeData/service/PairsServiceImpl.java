package com.coinsExchangeData.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.contract.IPair;
import com.coinsExchangeData.contract.IPairsService;
import com.coinsExchangeData.model.MarketsResourcesManager;
import com.coinsExchangeData.model.PairAggregatedModel;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.IPairAggregatedRepository;
import com.coinsExchangeData.task.BtcEPairsTask;
import com.coinsExchangeData.task.BterPairsTask;
import com.coinsExchangeData.task.CryptsyPairsTask;
import com.coinsExchangeData.task.MintPalPairsTask;
import com.coinsExchangeData.task.PoloniexPairsTask;

@Service("pairsService")
public class PairsServiceImpl implements IPairsService{
	
	@Autowired
	private BtcEPairsTask btcEPairsTask;
	@Autowired
	private BterPairsTask bterPairsTask;
	@Autowired
	private CryptsyPairsTask cryptsyPairsTask;
	@Autowired
	private MintPalPairsTask mintPalPairsTask;
	@Autowired
	private PoloniexPairsTask poloniexPairsTask;
	@Autowired
	private MarketsResourcesManager marketsResourcesManager;
	@Autowired
	private QueryManager queryManager;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private IPairAggregatedRepository pairAggregatedRepository;
	
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
		};
	
	public void fetch() {
		List<Future<Void>> futures = null;
		List<Callable<Void>> tasks = new ArrayList<>();
		
		tasks.add(btcEPairsTask);
		tasks.add(bterPairsTask);
		tasks.add(cryptsyPairsTask);
		tasks.add(mintPalPairsTask);
		tasks.add(poloniexPairsTask);
		
		ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
		
		try {
			futures = executor.invokeAll(tasks, 10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Future<Void> f : futures) {
			try {
				f.get();
			} catch (InterruptedException e) {
				System.out.println("CancellationException");
				e.printStackTrace();
			} catch (ExecutionException e) {
				System.out.println("ExecutionException");
				e.printStackTrace();
			} catch (CancellationException e) {
				System.out.println("CancellationException");
				e.printStackTrace();
			}
		}
		executor.shutdown();
		this.doAggregation();
		
	}
	
	private void doAggregation() {
		for (int i = 0; i < symbols.length; i++) {
			String symbol = symbols[i];
			this.createPairAggregatedModel(symbol);
		}
	}
	
	private void createPairAggregatedModel(String symbol) {
		
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
	}
}
