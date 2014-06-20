package com.coinsExchangeData.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.model.ProviderModel;
import com.coinsExchangeData.repository.IProviderRepository;

@Component("providerManager")
public class ProviderManager {
	
	@Autowired
	private IProviderRepository providerRepository;
	
	private List<ProviderModel> providers = new ArrayList<>();	
	
	public void createProviders() {
		providers.add(new ProviderModel("btc_e"));
		providers.add(new ProviderModel("bter"));
		providers.add(new ProviderModel("cryptsy"));
		providers.add(new ProviderModel("mint_pal"));
		providers.add(new ProviderModel("poloniex"));
		
		for (ProviderModel provider : providers) {
			providerRepository.save(provider);
		}
	}
	
}
