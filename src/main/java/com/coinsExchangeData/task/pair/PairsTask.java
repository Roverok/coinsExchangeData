package com.coinsExchangeData.task.pair;

import java.util.List;

import com.coinsExchangeData.contract.IPair;

public class PairsTask {
	
	public PairsTask() {}
	
	public double getLasttradePriceBuyCoinName(String coin, List<IPair> pairs) {
		double lastTradePrice = 0;
		for (IPair pair : pairs) {
			if (pair.getSymbol().startsWith(coin)) {
				lastTradePrice = pair.getLastTradePrice();
				break;
			}
		}
		return lastTradePrice;
	}
}
