package com.coinsExchangeData.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coinsExchangeData.contract.IPair;

@Entity
@Table(name = "cryptsy")
public class CryptsyPairModel implements IPair {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	private Date date = new Date();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PAIR_ID", nullable = false)
	private PairModel pair;

	@Column(name = "LABEL")
	private String label;
	@Column(name = "LAST_TRADE_PRICE")
	private double lasttradeprice;
	@Column(name = "LAST_TRADE_TIME")
	private String lasttradetime;
	@Column(name = "MARKET_ID")
	private int marketid;
	@Column(name = "PRIMARY_CODE")
	private String primarycode;
	@Column(name = "PRIMARY_NAME")
	private String primaryname;
	@Column(name = "SECONDARY_NAME")
	private String secondaryname;
	@Column(name = "VOLUME")
	private double volume;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public PairModel getPair() {
		return pair;
	}

	public void setPair(PairModel pair) {
		this.pair = pair;
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public double getLasttradeprice() {
		return lasttradeprice;
	}

	public String getLasttradetime() {
		return lasttradetime;
	}

	public int getMarketid() {
		return marketid;
	}

	public String getPrimarycode() {
		return primarycode;
	}

	public String getPrimaryname() {
		return primaryname;
	}

	public String getSecondaryname() {
		return secondaryname;
	}

	public double getVolume() {
		return volume;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLasttradeprice(double lasttradeprice) {
		this.lasttradeprice = lasttradeprice;
	}

	public void setLasttradetime(String lasttradetime) {
		this.lasttradetime = lasttradetime;
	}

	public void setMarketid(int marketid) {
		this.marketid = marketid;
	}

	public void setPrimarycode(String primarycode) {
		this.primarycode = primarycode;
	}

	public void setPrimaryname(String primaryname) {
		this.primaryname = primaryname;
	}

	public void setSecondaryname(String secondaryname) {
		this.secondaryname = secondaryname;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public double getLastTradePrice() {
		return this.lasttradeprice;
	}
	
	public String getSymbol() {
		return this.pair.getSymbol();
	}

}