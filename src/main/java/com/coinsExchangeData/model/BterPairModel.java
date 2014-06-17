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
@Table(name = "bter")
public class BterPairModel implements IPair {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	private Date date = new Date();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PAIR_ID", nullable = false)
	private PairModel pair;

	@Column(name = "AVG")
	private double avg;
	@Column(name = "BUY")
	private double buy;
	@Column(name = "HIGH")
	private double high;
	@Column(name = "LAST")
	private double last;
	@Column(name = "LOW")
	private double low;
	@Column(name = "SELL")
	private double sell;
	@Column(name = "VOL_BQC")
	private double volBqc;
	@Column(name = "VOL_BTC")
	private double volBtc;

	public double getAvg() {
		return avg;
	}

	public double getBuy() {
		return buy;
	}

	public Date getDate() {
		return date;
	}

	public double getHigh() {
		return high;
	}

	public int getId() {
		return id;
	}

	public double getLast() {
		return last;
	}

	public double getLow() {
		return low;
	}

	public PairModel getPair() {
		return pair;
	}

	public double getSell() {
		return sell;
	}

	public double getVolBqc() {
		return volBqc;
	}

	public double getVolBtc() {
		return volBtc;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setBuy(double buy) {
		this.buy = buy;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setPair(PairModel pair) {
		this.pair = pair;
	}

	public void setSell(double sell) {
		this.sell = sell;
	}

	public void setVolBqc(double volBqc) {
		this.volBqc = volBqc;
	}

	public void setVolBtc(double volBtc) {
		this.volBtc = volBtc;
	}
	
	public double getLastTradePrice() {
		return this.last;	
	}

	public String getSymbol() {
		return this.pair.getSymbol();
	}

	public double getVolume() {
		return this.volBtc;
	}

}
