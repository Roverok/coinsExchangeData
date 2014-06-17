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
@Table(name = "mint_pal")
public class MintPalPairModel implements IPair {

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

	@Column(name = "_24H_HIGH")
	private double _24hhigh;
	@Column(name = "_24H_LOW")
	private double _24hlow;
	@Column(name = "_24H_VOL")
	private double _24hvol;
	@Column(name = "_CHANGE")
	private String _change;
	@Column(name = "CODE")
	private String code;
	@Column(name = "EXCHANGE")
	private String exchange;
	@Column(name = "LAST_PRICE")
	private double lastPrice;
	@Column(name = "MARKET_ID")
	private int marketId;
	@Column(name = "TOP_ASK")
	private double topAsk;
	@Column(name = "TOP_BID")
	private double topBid;
	@Column(name = "YESTERDAY_PRICE")
	private double yesterdayPrice;
	
	public double get_24hhigh() {
		return _24hhigh;
	}

	public double get_24hlow() {
		return _24hlow;
	}

	public double get_24hvol() {
		return _24hvol;
	}

	public String get_change() {
		return _change;
	}

	public String getCode() {
		return code;
	}

	public Date getDate() {
		return date;
	}

	public String getExchange() {
		return exchange;
	}

	public Long getId() {
		return id;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public int getMarketId() {
		return marketId;
	}

	public PairModel getPair() {
		return pair;
	}

	public double getTopAsk() {
		return topAsk;
	}

	public double getTopBid() {
		return topBid;
	}

	public double getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void set_24hhigh(double _24hhigh) {
		this._24hhigh = _24hhigh;
	}

	public void set_24hlow(double _24hlow) {
		this._24hlow = _24hlow;
	}

	public void set_24hvol(double _24hvol) {
		this._24hvol = _24hvol;
	}

	public void set_change(String _change) {
		this._change = _change;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}

	public void setPair(PairModel pair) {
		this.pair = pair;
	}

	public void setTopAsk(double topAsk) {
		this.topAsk = topAsk;
	}

	public void setTopBid(double topBid) {
		this.topBid = topBid;
	}

	public void setYesterdayPrice(double yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}
	
	public double getLastTradePrice() {
		return this.lastPrice;
	}
	
	public String getSymbol() {
		return this.pair.getSymbol();
	}

	public double getVolume() {
		return this.get_24hvol();
	}
}
