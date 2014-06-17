package com.coinsExchangeData.contract;

public interface IPair {
	String getSymbol();
	double getLastTradePrice();
	double getVolume();
}
