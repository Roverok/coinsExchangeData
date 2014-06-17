package com.coinsExchangeData.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sell_order")
public class SellOrder extends AbstractOrder {
	
}
