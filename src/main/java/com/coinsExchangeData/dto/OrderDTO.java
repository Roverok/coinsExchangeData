package com.coinsExchangeData.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coinsExchangeData.model.AbstractOrder;

public class OrderDTO {
	private Map<String, List<AbstractOrder>> orders;
	
	public OrderDTO() {
		orders = new HashMap<String, List<AbstractOrder>>();
	}
	
	public Map<String, List<AbstractOrder>> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, List<AbstractOrder>> orders) {
		this.orders = orders;
	}
}
