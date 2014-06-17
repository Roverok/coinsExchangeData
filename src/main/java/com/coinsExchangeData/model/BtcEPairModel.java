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
@Table(name = "btc_e")
public class BtcEPairModel implements IPair {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	private Date date = new Date();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="PAIR_ID", nullable=false)
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
	@Column(name = "SERVER_TIME")
	private int server_time;
	@Column(name = "UPDATED")
	private int updated;
	@Column(name = "VOL")
	private double vol;
	@Column(name = "VOL_CUR")
	private double vol_cur;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getAvg() {
		return this.avg;
	}

	public double getBuy() {
		return this.buy;
	}

	public double getHigh() {
		return this.high;
	}

	public Long getId() {
		return id;
	}

	public double getLast() {
		return this.last;
	}

	public double getLow() {
		return this.low;
	}

	public PairModel getPair() {
		return pair;
	}

	public double getSell() {
		return this.sell;
	}

	public int getServer_time() {
		return this.server_time;
	}

	public int getUpdated() {
		return this.updated;
	}

	public double getVol() {
		return this.vol;
	}

	public double getVol_cur() {
		return this.vol_cur;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setBuy(double buy) {
		this.buy = buy;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setId(Long id) {
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

	public void setServer_time(int server_time) {
		this.server_time = server_time;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

	public void setVol_cur(double vol_cur) {
		this.vol_cur = vol_cur;
	}
	
	public double getLastTradePrice() {
		return this.last;
	}
	
	public String getSymbol() {
		return this.pair.getSymbol();
	}

	public double getVolume() {
		return this.vol_cur;
	}
}
