package com.coinsExchangeData.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coinsExchangeData.contract.IHeatMapService;
import com.coinsExchangeData.dto.HeatMapDTO;
import com.coinsExchangeData.dto.OrderDTO;
import com.coinsExchangeData.model.AbstractOrder;

@Controller
public class homeController {
	
	@Autowired
	private IHeatMapService heatMapService;
	
	private List<AbstractOrder> sellOrders = new ArrayList<AbstractOrder>();
	private List<AbstractOrder> buyOrders  = new ArrayList<AbstractOrder>();
	
	@RequestMapping(value="/heatmap",  method = RequestMethod.GET) 
	public @ResponseBody HeatMapDTO heatMap() {
		return heatMapService.getHeatMap();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/permissions",  method = RequestMethod.GET) 
	public @ResponseBody String permissions()  {
		System.out.println("inside permissions method");
		return "Success";
	}
}