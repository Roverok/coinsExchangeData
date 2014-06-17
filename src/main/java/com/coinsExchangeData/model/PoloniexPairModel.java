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
@Table(name = "poloniex")
public class PoloniexPairModel implements IPair {

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

	@Column(name = "BASE_VOLUME")
	private double baseVolume;
	@Column(name = "HIGHEST_BID")
	private double highestBid;
	@Column(name = "IS_FROZEN")
	private int isFrozen;
	@Column(name = "LAST")
	private double last;
	@Column(name = "LOWEST_ASK")
	private double lowestAsk;
	@Column(name = "PERCENT_CHANGE")
	private double percentChange;
	@Column(name = "QUOTE_VOLUME")
	private double quoteVolume;
	
	
	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getBaseVolume() {
		return baseVolume;
	}

	public Date getDate() {
		return date;
	}

	public double getHighestBid() {
		return highestBid;
	}

	public Long getId() {
		return id;
	}

	public int getIsFrozen() {
		return isFrozen;
	}

	public double getLastTradePrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getLowestAsk() {
		return lowestAsk;
	}

	public PairModel getPair() {
		return pair;
	}

	public double getPercentChange() {
		return percentChange;
	}

	public double getQuoteVolume() {
		return quoteVolume;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsFrozen(int isFrozen) {
		this.isFrozen = isFrozen;
	}

	public void setLowestAsk(double lowestAsk) {
		this.lowestAsk = lowestAsk;
	}

	public void setPair(PairModel pair) {
		this.pair = pair;
	}

	public void setPercentChange(double percentChange) {
		this.percentChange = percentChange;
	}

	public void setQuoteVolume(double quoteVolume) {
		this.quoteVolume = quoteVolume;
	}
	
	public String getSymbol() {
		return this.pair.getSymbol();
	}

	public double getVolume() {
		return this.baseVolume;
	}
}
