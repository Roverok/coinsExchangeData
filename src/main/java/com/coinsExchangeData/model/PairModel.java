package com.coinsExchangeData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pairs")
@NamedQueries({ 
	@NamedQuery(name = "PairModel.findPairBySymbol", query = "SELECT pm FROM PairModel pm WHERE pm.symbol = :symbol"),
	@NamedQuery(name = "PairModel.findPairByName", query = "SELECT pm FROM PairModel pm WHERE pm.name = :name")
})
public class PairModel {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "COIN_1_ID")
	private int coin1Id;
	@Column(name = "COIN_2_ID")
	private int coin2Id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SYMBOL")
	private String symbol;

	public PairModel() {
	}

	public PairModel(int coin1Id, int coin2Id, String name, String symbol) {
		this.setCoin1Id(coin1Id);
		this.setCoin2Id(coin2Id);
		this.setName(name);
		this.setSymbol(symbol);
	}

	public int getCoin1Id() {
		return coin1Id;
	}

	public int getCoin2Id() {
		return coin2Id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setCoin1Id(int coin1Id) {
		this.coin1Id = coin1Id;
	}

	public void setCoin2Id(int coin2Id) {
		this.coin2Id = coin2Id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String toString() {
		return "symbol: " + this.getSymbol() + " , and name is: "
				+ this.getName();
	}
}
