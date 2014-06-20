package com.coinsExchangeData.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.bean.PairManager;
import com.coinsExchangeData.bean.ProviderManager;

@Component
public class Setup implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private PairManager pairManager;
	@Autowired
	private ProviderManager providerManager;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName() != "Root WebApplicationContext") return;
		
		pairManager.createPairs();
		providerManager.createProviders();
	}
	
	
	
}
