package com.coinsExchangeData.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buy_order")
public class BuyOrder extends AbstractOrder {

}
