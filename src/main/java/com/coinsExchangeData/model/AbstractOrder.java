package com.coinsExchangeData.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="abstract_order")
public class AbstractOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "ID")
	protected Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	protected Date date = new Date();
	
	@Column(name = "PRICE")
	protected double price;
	
	@Column(name = "COIN1")
	protected double coin1;
	
	@Column(name = "COIN2")
	protected double coin2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCoin1() {
		return coin1;
	}

	public void setCoin1(double coin1) {
		this.coin1 = coin1;
	}

	public double getCoin2() {
		return coin2;
	}

	public void setCoin2(double coin2) {
		this.coin2 = coin2;
	}
	
}
