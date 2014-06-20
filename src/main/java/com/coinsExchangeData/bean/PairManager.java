package com.coinsExchangeData.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.model.CoinModel;
import com.coinsExchangeData.model.PairModel;
import com.coinsExchangeData.repository.ICoinRepository;
import com.coinsExchangeData.repository.IPairsRepository;

@Component("pairsManager")
public class PairManager {
	
	@Autowired
	private ICoinRepository coinsRepository;
	@Autowired
	private IPairsRepository pairsRepository;
	
	private List<CoinModel> coins = new ArrayList<CoinModel>();
	private List<PairModel> pairs = new ArrayList<PairModel>();
	
	public void createPairs() {
		
		// create coins.
		CoinModel btc  = new CoinModel("BTC", "btc");
		CoinModel usd  = new CoinModel("USD", "usd");
		CoinModel ltc  = new CoinModel("LTC", "ltc");
		CoinModel utc  = new CoinModel("UTC", "utc");
		CoinModel doge = new CoinModel("DOGE", "doge");
		CoinModel ppc  = new CoinModel("PPC", "ppc");
		CoinModel nmc  = new CoinModel("NMC", "nmc");
		CoinModel drk  = new CoinModel("DRK", "drk");
		CoinModel pts  = new CoinModel("PTS", "pts");
		CoinModel xpm  = new CoinModel("XPM", "xpm");
		CoinModel qrk  = new CoinModel("QRK", "qrk");
		CoinModel ftc  = new CoinModel("FTC", "ftc");
		CoinModel zet  = new CoinModel("ZET", "zet");
	
		
		coins.add(usd);
		coins.add(btc);
		coins.add(ltc);
		coins.add(utc);
		coins.add(doge);
		coins.add(ppc);
		coins.add(nmc);
		coins.add(drk);
		coins.add(pts);
		coins.add(xpm);
		coins.add(qrk);
		coins.add(ftc);
		coins.add(zet);
		
		// save coins.
		for (CoinModel coin : coins) {
			coinsRepository.save(coin);
		}
		System.out.println("Done generating coins.");
		
		// create pairs.
		PairModel btc_usd  = new PairModel(btc.getId(), usd.getId(), "BTC/USD", "btc_usd");
		PairModel btc_ltc  = new PairModel(btc.getId(), ltc.getId(), "BTC/LTC", "btc_ltc");
		PairModel ltc_usd  = new PairModel(ltc.getId(), usd.getId(), "LTC/USD", "ltc_usd");
		PairModel ltc_btc  = new PairModel(ltc.getId(), btc.getId(), "LTC/BTC", "ltc_btc");
		PairModel utc_btc  = new PairModel(utc.getId(), btc.getId(), "UTC/BTC", "utc_btc");
		PairModel doge_btc = new PairModel(doge.getId(), btc.getId(), "DOGE/BTC", "doge_btc");
		PairModel ppc_btc  = new PairModel(ppc.getId(), btc.getId(), "PPC/BTC", "ppc_btc");
		PairModel nmc_btc  = new PairModel(nmc.getId(), btc.getId(), "NMC/BTC", "nmc_btc");
		PairModel drk_btc  = new PairModel(drk.getId(), btc.getId(), "DRK/BTC", "drk_btc");
		PairModel pts_btc  = new PairModel(pts.getId(), btc.getId(), "PTS/BTC", "pts_btc");
		PairModel xpm_btc  = new PairModel(xpm.getId(), btc.getId(), "XPM/BTC", "xpm_btc");
		PairModel qrk_btc  = new PairModel(qrk.getId(), btc.getId(), "QRK/BTC", "qrk_btc");
		PairModel ftc_btc  = new PairModel(ftc.getId(), btc.getId(), "FTC/BTC", "ftc_btc");
		
		PairModel doge_ltc = new PairModel(doge.getId(), ltc.getId(), "DOGE/LTC", "doge_ltc");
		PairModel ppc_ltc  = new PairModel(ppc.getId(), ltc.getId(), "PPC/LTC", "ppc_ltc");
		PairModel qrk_ltc  = new PairModel(qrk.getId(), ltc.getId(), "QRK/LTC", "qrk_ltc");
		PairModel zet_ltc  = new PairModel(zet.getId(), ltc.getId(), "ZET/LTC", "zet_ltc");
		PairModel xpm_ltc  = new PairModel(xpm.getId(), ltc.getId(), "XPM/LTC", "xpm_ltc");
		
		PairModel zet_btc  = new PairModel(zet.getId(), btc.getId(), "ZET/BTC", "zet_btc");
		
		PairModel nmc_ltc  = new PairModel(nmc.getId(), ltc.getId(), "NMC/LTC", "nmc_ltc");
		PairModel ftc_ltc  = new PairModel(ftc.getId(), ltc.getId(), "FTC/LTC", "ftc_ltc");
		PairModel drk_ltc  = new PairModel(drk.getId(), ltc.getId(), "DRK/LTC", "drk_ltc");
		PairModel pts_ltc  = new PairModel(pts.getId(), ltc.getId(), "PTS/LTC", "pts_ltc");
		
		PairModel ppc_doge  = new PairModel(ppc.getId(), doge.getId(), "PPC/DOGE", "ppc_doge");
		PairModel nmc_doge  = new PairModel(nmc.getId(), doge.getId(), "NMC/DOGE", "nmc_doge");
		PairModel drk_doge  = new PairModel(drk.getId(), doge.getId(), "DRK/DOGE", "drk_doge");
		PairModel pts_doge  = new PairModel(pts.getId(), doge.getId(), "PTS/DOGE", "pts_doge");
		PairModel qrk_doge  = new PairModel(qrk.getId(), doge.getId(), "QRK/DOGE", "qrk_doge");
		PairModel zet_doge  = new PairModel(zet.getId(), doge.getId(), "ZET/DOGE", "zet_doge");
		PairModel xpm_doge  = new PairModel(xpm.getId(), doge.getId(), "XPM/DOGE", "xpm_doge");
		PairModel ftc_doge  = new PairModel(ftc.getId(), doge.getId(), "FTC/DOGE", "ftc_doge");
		
		PairModel nmc_ppc   = new PairModel(nmc.getId(), ppc.getId(), "NMC/PPC", "nmc_ppc");
		PairModel drk_ppc   = new PairModel(drk.getId(), ppc.getId(), "DRK/PPC", "drk_ppc");
		PairModel pts_ppc   = new PairModel(pts.getId(), ppc.getId(), "PTS/PPC", "pts_ppc");
		PairModel qrk_ppc   = new PairModel(qrk.getId(), ppc.getId(), "QRK/PPC", "qrk_ppc");
		PairModel zet_ppc   = new PairModel(zet.getId(), ppc.getId(), "ZET/PPC", "zet_ppc");
		PairModel xpm_ppc   = new PairModel(xpm.getId(), ppc.getId(), "XPM/PPC", "xpm_ppc");
		PairModel ftc_ppc   = new PairModel(ftc.getId(), ppc.getId(), "FTC/PPC", "ftc_ppc");
		
		PairModel drk_nmc   = new PairModel(drk.getId(), nmc.getId(), "DRK/NMC", "drk_nmc");
		PairModel pts_nmc   = new PairModel(pts.getId(), nmc.getId(), "PTS/NMC", "pts_nmc");
		PairModel qrk_nmc   = new PairModel(qrk.getId(), nmc.getId(), "QRK/NMC", "qrk_nmc");
		PairModel zet_nmc   = new PairModel(zet.getId(), nmc.getId(), "ZET/NMC", "zet_nmc");
		PairModel xpm_nmc   = new PairModel(xpm.getId(), nmc.getId(), "XPM/NMC", "xpm_nmc");
		PairModel ftc_nmc   = new PairModel(ftc.getId(), nmc.getId(), "FTC/NMC", "ftc_nmc");
		
		PairModel pts_drk   = new PairModel(pts.getId(), drk.getId(), "PTS/DRK", "pts_drk");
		PairModel qrk_drk   = new PairModel(qrk.getId(), drk.getId(), "QRK/DRK", "qrk_drk");
		PairModel zet_drk   = new PairModel(zet.getId(), drk.getId(), "ZET/DRK", "zet_drk");
		PairModel xpm_drk   = new PairModel(xpm.getId(), drk.getId(), "XPM/DRK", "xpm_drk");
		PairModel ftc_drk   = new PairModel(ftc.getId(), drk.getId(), "FTC/DRK", "ftc_drk");
		
		PairModel qrk_pts   = new PairModel(qrk.getId(), pts.getId(), "QRK/PTS", "qrk_pts");
		PairModel zet_pts   = new PairModel(zet.getId(), pts.getId(), "ZET/PTS", "zet_pts");
		PairModel xpm_pts   = new PairModel(xpm.getId(), pts.getId(), "XPM/PTS", "xpm_pts");
		PairModel ftc_pts   = new PairModel(ftc.getId(), pts.getId(), "FTC/PTS", "ftc_pts");
		
		PairModel zet_qrk   = new PairModel(zet.getId(), qrk.getId(), "ZET/QRK", "zet_qrk");
		PairModel xpm_qrk   = new PairModel(xpm.getId(), qrk.getId(), "XPM/QRK", "xpm_qrk");
		PairModel ftc_qrk   = new PairModel(ftc.getId(), qrk.getId(), "FTC/QRK", "ftc_qrk");
		
		PairModel xpm_zet   = new PairModel(xpm.getId(), zet.getId(), "XPM/ZET", "xpm_zet");
		PairModel ftc_zet   = new PairModel(ftc.getId(), zet.getId(), "FTC/ZET", "ftc_zet");
		
		PairModel ftc_xpm   = new PairModel(ftc.getId(), xpm.getId(), "FTC/XPM", "ftc_xpm");
		
		
		pairs.add(ftc_xpm);
		
		pairs.add(xpm_zet);
		pairs.add(ftc_zet);
		
		pairs.add(zet_qrk);
		pairs.add(xpm_qrk);
		pairs.add(ftc_qrk);
		
		pairs.add(qrk_pts);
		pairs.add(zet_pts);
		pairs.add(xpm_pts);
		pairs.add(ftc_pts);
		
		pairs.add(pts_drk);
		pairs.add(qrk_drk);
		pairs.add(zet_drk);
		pairs.add(xpm_drk);
		pairs.add(ftc_drk);
		
		pairs.add(drk_nmc);
		pairs.add(pts_nmc);
		pairs.add(qrk_nmc);
		pairs.add(zet_nmc);
		pairs.add(xpm_nmc);
		pairs.add(ftc_nmc);
		
		pairs.add(nmc_ppc);
		pairs.add(drk_ppc);
		pairs.add(pts_ppc);
		pairs.add(qrk_ppc);
		pairs.add(zet_ppc);
		pairs.add(xpm_ppc);
		pairs.add(ftc_ppc);
		
		pairs.add(btc_ltc);
		pairs.add(ltc_usd);
		pairs.add(ltc_btc);
		pairs.add(utc_btc);
		pairs.add(doge_btc);
		pairs.add(ppc_btc);
		pairs.add(nmc_btc);
		pairs.add(drk_btc);
		pairs.add(pts_btc);
		pairs.add(xpm_btc);
		pairs.add(qrk_btc);
		pairs.add(ftc_btc);
		pairs.add(doge_ltc);
		pairs.add(ppc_ltc);
		pairs.add(qrk_ltc);
		pairs.add(zet_ltc);
		pairs.add(xpm_ltc);
		pairs.add(zet_btc);
		pairs.add(nmc_ltc);
		pairs.add(ftc_ltc);
		pairs.add(drk_ltc);
		pairs.add(pts_ltc);
		pairs.add(ppc_doge);
		pairs.add(nmc_doge);
		pairs.add(drk_doge);
		pairs.add(pts_doge);
		pairs.add(qrk_doge);
		pairs.add(zet_doge);
		pairs.add(xpm_doge);
		pairs.add(ftc_doge);
		
		for (PairModel pair : pairs) {
			pairsRepository.save(pair);
		}
		System.out.println("Done generating pairs.");
	}
	
}
