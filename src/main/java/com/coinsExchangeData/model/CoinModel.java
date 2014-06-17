package com.coinsExchangeData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coins")
public class CoinModel {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SYMBOL")
	private String symbol;
	
	public CoinModel() {}
	
	public CoinModel(String name, String symbol) {
		this.setName(name);
		this.setSymbol(symbol);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
