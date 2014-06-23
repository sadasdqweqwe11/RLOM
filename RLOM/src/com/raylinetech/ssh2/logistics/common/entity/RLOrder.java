package com.raylinetech.ssh2.logistics.common.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_rlorder")
public class RLOrder implements Cloneable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String rlordernumber;
	@Column
	private String ordernumber;
	@Column
	private String vendor;
	@Column
	private String skuno;
	@Column
	private String itemname;
	@Column
	private String pinming;
	@Column
	private String quantity;
	@Column
	private String description;
	@Column
	private String buyername;
	@Column
	private String shipaddress1;
	@Column
	private String shipaddress2;
	@Column
	private String shipcity;
	@Column
	private String shipstate;
	@Column
	private String postalcode;
	@Column
	private String shipcountry;
	@Column
	private String buyerphonenumber;
	@Column
	private String date;
	@Column
	private String amount;
	@Column
	private String trackingno;
	@Column
	private String guojia;
	@Column
	private String marketplace;
	@Column
	private String account;
	@Column
	private String currency;
	@Column
	private long fileid;
	@OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	@JoinColumn(name = "rlorderid")
	private List<RLOrderItem> rlorderitems;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="logisticsid")
	private Logistics logistics;
    
	public Logistics getLogistics() {
		return logistics;
	}
	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}
	public String getRlordernumber() {
		return rlordernumber;
	}
	public void setRlordernumber(String rlordernumber) {
		this.rlordernumber = rlordernumber;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getSkuno() {
		return skuno;
	}
	public void setSkuno(String skuno) {
		this.skuno = skuno;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getPinming() {
		return pinming;
	}
	public void setPinming(String pinming) {
		this.pinming = pinming;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public String getShipaddress1() {
		return shipaddress1;
	}
	public void setShipaddress1(String shipaddress1) {
		this.shipaddress1 = shipaddress1;
	}
	public String getShipaddress2() {
		return shipaddress2;
	}
	public void setShipaddress2(String shipaddress2) {
		this.shipaddress2 = shipaddress2;
	}
	public String getShipcity() {
		return shipcity;
	}
	public void setShipcity(String shipcity) {
		this.shipcity = shipcity;
	}
	public String getShipstate() {
		return shipstate;
	}
	public void setShipstate(String shipstate) {
		this.shipstate = shipstate;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getShipcountry() {
		return shipcountry;
	}
	public void setShipcountry(String shipcountry) {
		this.shipcountry = shipcountry;
	}
	public String getBuyerphonenumber() {
		return buyerphonenumber;
	}
	public void setBuyerphonenumber(String buyerphonenumber) {
		this.buyerphonenumber = buyerphonenumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTrackingno() {
		return trackingno;
	}
	public void setTrackingno(String trackingno) {
		this.trackingno = trackingno;
	}
	public String getGuojia() {
		return guojia;
	}
	public void setGuojia(String guojia) {
		this.guojia = guojia;
	}
	public List<RLOrderItem> getRlorderitems() {
		return rlorderitems;
	}
	public void setRlorderitems(List<RLOrderItem> rlorderitems) {
		this.rlorderitems = rlorderitems;
	}

	public String getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public long getFileid() {
		return fileid;
	}
	public void setFileid(long fileid) {
		this.fileid = fileid;
	}
	public RLOrder() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public RLOrder(long id, String rlordernumber, String ordernumber,
			String vendor, String skuno, String itemname, String pinming,
			String quantity, String description, String buyername,
			String shipaddress1, String shipaddress2, String shipcity,
			String shipstate, String postalcode, String shipcountry,
			String buyerphonenumber, String date, String amount,
			String trackingno, String guojia, String marketplace,
			String account, String currency, long fileid,
			List<RLOrderItem> rlorderitems, Logistics logistics) {
		super();
		this.id = id;
		this.rlordernumber = rlordernumber;
		this.ordernumber = ordernumber;
		this.vendor = vendor;
		this.skuno = skuno;
		this.itemname = itemname;
		this.pinming = pinming;
		this.quantity = quantity;
		this.description = description;
		this.buyername = buyername;
		this.shipaddress1 = shipaddress1;
		this.shipaddress2 = shipaddress2;
		this.shipcity = shipcity;
		this.shipstate = shipstate;
		this.postalcode = postalcode;
		this.shipcountry = shipcountry;
		this.buyerphonenumber = buyerphonenumber;
		this.date = date;
		this.amount = amount;
		this.trackingno = trackingno;
		this.guojia = guojia;
		this.marketplace = marketplace;
		this.account = account;
		this.currency = currency;
		this.fileid = fileid;
		this.rlorderitems = rlorderitems;
		this.logistics = logistics;
	}
	
	@Override
	public RLOrder clone() throws CloneNotSupportedException {
		RLOrder order = new RLOrder();
		order.setAccount(account);
		order.setAmount(amount);
		order.setBuyername(buyername);
		order.setBuyerphonenumber(buyerphonenumber);
		order.setCurrency(currency);
		order.setDate(date);
		order.setDescription(description);
		order.setFileid(fileid);
		order.setGuojia(guojia);
		order.setId(id);
		order.setItemname(itemname);
		Logistics  log = null;
		if(this.logistics!=null){
			log = (Logistics)this.logistics.clone();
		}
		order.setLogistics(log);
		order.setMarketplace(marketplace);
		order.setOrdernumber(ordernumber);
		order.setPinming(pinming);
		order.setPostalcode(postalcode);
		order.setQuantity(quantity);
		List items = new ArrayList();
		for (RLOrderItem item : this.rlorderitems) {
			items.add((RLOrderItem)item.clone());
		}
		order.setRlorderitems(items);
		order.setRlordernumber(rlordernumber);
		order.setShipaddress1(shipaddress1);
		order.setShipaddress2(shipaddress2);
		order.setShipcity(shipcity);
		order.setShipcountry(shipcountry);
		order.setShipstate(shipstate);
		order.setSkuno(skuno);
		order.setTrackingno(trackingno);
		order.setVendor(vendor);
		return order;
	}

	

}
