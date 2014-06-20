package com.coinsExchangeData.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientHelper {
	
	public static HttpClient getClient() {
		RequestConfig.Builder requestBuilder = RequestConfig.custom();         
		requestBuilder = requestBuilder.setConnectTimeout(10000);              
		requestBuilder = requestBuilder.setConnectionRequestTimeout(1000);     
		                                                                       
		HttpClientBuilder builder = HttpClientBuilder.create();                
		builder.setDefaultRequestConfig(requestBuilder.build());               
		HttpClient client = builder.build();
		
		return client;
	}
	
	public static String getResultAsText(HttpResponse response) throws IllegalStateException, IOException {
		String line        = "";
		BufferedReader rd   = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();                                                          
		                                                                                                   
		while ((line = rd.readLine()) != null) {                                                           
			result.append(line);                                                                           
		}                                                                                                  
		                        
		return result.toString();
	}
	                                
}
