package com.raylinetech.ssh2.logistics.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_sku")
public class Sku {

	@Id
	private String skuno;
	@Column
	private String skucode;
	@Column
	private String des;
	@Column
	private String vendor;
	@Column
	private int area;
	@Column
	private double weight;
	@Column
	private double price;
	@Column
	private String name;
	@Column
	private String pinming;
	@Column
	private int battery;
	@Column
	private int promotion;
	@Column
	private int liquid;
	@Column
	private int other;
	public String getSkuno() {
		return skuno;
	}
	public void setSkuno(String skuno) {
		this.skuno = skuno;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public int getPromotion() {
		return promotion;
	}
	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
	public int getLiquid() {
		return liquid;
	}
	public void setLiquid(int liquid) {
		this.liquid = liquid;
	}
	public int getOther() {
		return other;
	}
	public void setOther(int other) {
		this.other = other;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkucode() {
		return skucode;
	}
	public void setSkucode(String skucode) {
		this.skucode = skucode;
	}
	public Sku(String skuno, String skucode, String des, String vendor,
			int area, double weight, double price, String name, String pinming,
			int battery, int promotion, int liquid, int other) {
		super();
		this.skuno = skuno;
		this.skucode = skucode;
		this.des = des;
		this.vendor = vendor;
		this.area = area;
		this.weight = weight;
		this.price = price;
		this.name = name;
		this.pinming = pinming;
		this.battery = battery;
		this.promotion = promotion;
		this.liquid = liquid;
		this.other = other;
	}
	public String getPinming() {
		return pinming;
	}
	public void setPinming(String pinming) {
		this.pinming = pinming;
	}
	
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Sku() {
		super();
	}
	public Sku(String skuno) {
		super();
		this.skuno = skuno;
	}
	
}
