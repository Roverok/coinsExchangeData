package com.coinsExchangeData.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pairs_aggregated")
@NamedQueries({ 
	@NamedQuery(name = "PairAggregatedModel.findByDate", 
			query = "SELECT pam FROM PairAggregatedModel pam WHERE pam.date = :date"),
})
public class PairAggregatedModel {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	private Date date = new Date();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="PAIR_ID", nullable=false)
	private PairModel pair;
	
	@Column(name = "LAST_TRADE_PRICE_AVG")
	private double lastTradePriceAvg;
	
	@Column(name = "VOLUME_AVG")
	private double volumeAvg;
	
	public double getVolumeAvg() {
		return volumeAvg;
	}
	public void setVolumeAvg(double volumeAvg) {
		this.volumeAvg = volumeAvg;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getLastTradePriceAvg() {
		return lastTradePriceAvg;
	}
	public void setLastTradePriceAvg(double lastTradePriceAvg) {
		this.lastTradePriceAvg = lastTradePriceAvg;
	}
}
