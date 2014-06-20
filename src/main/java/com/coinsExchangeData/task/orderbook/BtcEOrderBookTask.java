package com.coinsExchangeData.task.orderbook;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.model.BuyOrderModel;
import com.coinsExchangeData.model.ProviderModel;
import com.coinsExchangeData.model.SellOrderModel;
import com.coinsExchangeData.repository.IAbstractOrderRepository;

@Component
public class BtcEOrderBookTask implements Callable<Void> {
	
	@Autowired
	private IAbstractOrderRepository abstractOrderRepository;
	@Autowired
	private QueryManager queryManager;
	
	public Void call() throws Exception {
		this.fetch();
		return null;
	}
	
	private void fetch() {
		
		ProviderModel pm = queryManager.findProviderByName("btc_e");
		
		try {
			Document doc1  = Jsoup.connect("https://btc-e.com/").get();
			Element script = doc1.getElementsByTag("script").first();
			DataNode node  = script.dataNodes().get(0);
			String text    = node.getWholeData();
			
			Pattern regex = Pattern.compile("a=[0-9a-z]+");
			Matcher match = regex.matcher(text);
			String cookieStr = null;
			
			while (match.find()) {
				if (match.group().length() != 0) {
					cookieStr = match.group();
					break;
				}
			}
			
			String[] parts = cookieStr.split("=");
			
			Document doc2  = Jsoup.connect("https://btc-e.com/").cookie(parts[0], parts[1]).get();
			
			Element orders_1 = doc2.getElementById("orders_1");
			Elements trs1    = orders_1.getElementsByTag("tr");
			
			for (Element tr : trs1) {
				Node price = tr.childNodes().get(0).childNode(0);
				Node coin1 = tr.childNodes().get(1).childNode(0);
				Node coin2 = tr.childNodes().get(2).childNode(0);
				
				if (price.toString().matches("\\d+(.\\d+)?")) {
					
					SellOrderModel sellOrder = new SellOrderModel();
					
					sellOrder.setPrice(new Double(price.toString()));
					sellOrder.setCoin1(new Double(coin1.toString()));
					sellOrder.setCoin2(new Double(coin2.toString()));
					sellOrder.setProvider(pm);
					
					abstractOrderRepository.save(sellOrder);
				}
				
			}
		
			Element orders_2 = doc2.getElementById("orders_2");
			Elements trs2    = orders_2.getElementsByTag("tr");
			
			for (Element tr : trs2) {
				
				Node price = tr.childNodes().get(0).childNode(0);
				Node coin1  = tr.childNodes().get(1).childNode(0);
				Node coin2  = tr.childNodes().get(2).childNode(0);
				
				if (price.toString().matches("\\d+(.\\d+)?")) {
					
					BuyOrderModel buyOrder = new BuyOrderModel ();
					
					buyOrder.setPrice(new Double(price.toString()));
					buyOrder.setCoin1(new Double(coin1.toString()));
					buyOrder.setCoin2(new Double(coin2.toString()));
					buyOrder.setProvider(pm);
					
					abstractOrderRepository.save(buyOrder);
				}	
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
