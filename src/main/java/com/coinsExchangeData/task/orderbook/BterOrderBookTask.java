package com.coinsExchangeData.task.orderbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coinsExchangeData.bean.HttpClientHelper;
import com.coinsExchangeData.bean.QueryManager;
import com.coinsExchangeData.model.BuyOrderModel;
import com.coinsExchangeData.model.ProviderModel;
import com.coinsExchangeData.model.SellOrderModel;
import com.coinsExchangeData.repository.IAbstractOrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

@Component
public class BterOrderBookTask implements Callable<Void> {
	
	private final String URL = "https://bter.com/json_svr/query/";
	
	@Autowired
	private IAbstractOrderRepository abstractOrderRepository;
	@Autowired
	private QueryManager queryManager;
	
	public Void call() throws Exception {
		this.fetch();
		return null;
	}
	
	private String cleanHtml(String html) {
		String cleanedHtml = html
			.replaceAll("(\\\\t|\\\\r|\\\\n)", "")
			.replaceAll("\\\\", "");
	
		return cleanedHtml;
	}
	
	private void fetch() throws IOException {
		String resultAsText = "";
		
		// initialize jackson mapper.
		ObjectMapper mapper = new ObjectMapper();
		
		// get provider from database.
		ProviderModel pm    = queryManager.findProviderByName("bter");
		
		// http.
		HttpClient client = HttpClientHelper.getClient();
		HttpPost post     = new HttpPost(URL);
		
		// add headers.
		post.setHeader("User-Agent", "Mozilla/5.0");
		
		// add post params.
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("type", "ask_bid_list_table"));
		urlParameters.add(new BasicNameValuePair("symbol", "btc_usd"));
		
		// ?
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		// fetch.
		HttpResponse response = client.execute(post);
		
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		
		resultAsText = HttpClientHelper.getResultAsText(response);
		
		JsonNode root         = mapper.readTree(resultAsText);
		JsonNode askListTable = root.get("ask_list_table");
		JsonNode bidListTable = root.get("bid_list_table");
		
		String askListTableStr = cleanHtml(askListTable.asText()); 
		
		Document askListTableDoc = Jsoup.parse(askListTableStr);
		Elements lis1 = askListTableDoc.getElementsByTag("li");
		for (Element li : lis1) {
			Element price  = li.child(0);
			Element amount = li.child(1);
			Element total  = li.child(2);
			
			SellOrderModel sellOrder = new SellOrderModel();
			
			sellOrder.setProvider(pm);
			sellOrder.setPrice(new Double(price.childNode(0).toString()));
			sellOrder.setCoin1(new Double(amount.childNode(0).toString()));
			sellOrder.setCoin2(new Double(total.childNode(0).toString()));
			
			abstractOrderRepository.save(sellOrder);
		}
		
		String bidListTableStr = cleanHtml(bidListTable.asText()); 
		
		Document bidListTableDoc = Jsoup.parse(bidListTableStr);
		Elements lis2 = bidListTableDoc.getElementsByTag("li");
		for (Element li : lis2) {
			Element price  = li.child(0);
			Element amount = li.child(1);
			Element total  = li.child(2);
			
			BuyOrderModel buyOrder = new BuyOrderModel();
			
			buyOrder.setProvider(pm);
			buyOrder.setPrice(new Double(price.childNode(0).toString()));
			buyOrder.setCoin1(new Double(amount.childNode(0).toString()));
			buyOrder.setCoin2(new Double(total.childNode(0).toString()));
			
			abstractOrderRepository.save(buyOrder);
		}
				
	}
}
