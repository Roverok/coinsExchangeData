
package com.coinsExchangeData.model.maping;

public class Ticker{
   	private double avg;
   	private double buy;
   	private double high;
   	private double last;
   	private double low;
   	private double sell;
   	private int server_time;
   	private int updated;
   	private double vol;
   	private double vol_cur;

 	public double getAvg(){
		return this.avg;
	}
	public double getBuy(){
		return this.buy;
	}
 	public double getHigh(){
		return this.high;
	}
	public double getLast(){
		return this.last;
	}
 	public double getLow(){
		return this.low;
	}
	public double getSell(){
		return this.sell;
	}
 	public int getServer_time(){
		return this.server_time;
	}
	public int getUpdated(){
		return this.updated;
	}
 	public double getVol(){
		return this.vol;
	}
	public double getVol_cur(){
		return this.vol_cur;
	}
 	public void setAvg(double avg){
		this.avg = avg;
	}
	public void setBuy(double buy){
		this.buy = buy;
	}
 	public void setHigh(double high){
		this.high = high;
	}
	public void setLast(double last){
		this.last = last;
	}
 	public void setLow(double low){
		this.low = low;
	}
	public void setSell(double sell){
		this.sell = sell;
	}
 	public void setServer_time(int server_time){
		this.server_time = server_time;
	}
	public void setUpdated(int updated){
		this.updated = updated;
	}
 	public void setVol(double vol){
		this.vol = vol;
	}
	public void setVol_cur(double vol_cur){
		this.vol_cur = vol_cur;
	}
}