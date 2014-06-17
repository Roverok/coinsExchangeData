package com.coinsExchangeData.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


@Controller
public class TestController {
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String test() 
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage page  = webClient.getPage("http://www.freeproxylists.net/");
		Document jPage = Jsoup.parse(page.asXml());
		
		
		return "test";
	}
	
}
