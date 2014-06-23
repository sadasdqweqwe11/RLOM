package com.raylinetech.ssh2.logistics.common.entity;

import java.util.LinkedList;
import java.util.List;

public class PrintLable {
//
//	private int id;
//	private String rlOrderNumber;
//	private String orderNumber;
//	private String vendor;
//	private List<List<String>> products;
//	private String buyerName;
//	private String shipAdress1;
//	private String shipAdress2;
//	private String shipCity;
//	private String shipState;
//	private String postalCode;
//	private String shipCountry;
//	private String buyerPhoneNumber;
//	private String date;
//	private String amount;
//	private String trackingno;
//	private String guojia;
//	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	
//	public String getBuyerName() {
//		return buyerName;
//	}
//	public void setBuyerName(String buyerName) {
//		this.buyerName = buyerName;
//	}
//	public String getRlOrderNumber() {
//		return rlOrderNumber;
//	}
//	public void setRlOrderNumber(String rlOrderNumber) {
//		this.rlOrderNumber = rlOrderNumber;
//	}
//	public String getOrderNumber() {
//		return orderNumber;
//	}
//	public void setOrderNumber(String orderNumber) {
//		this.orderNumber = orderNumber;
//	}
//	public String getVendor() {
//		return vendor;
//	}
//	public void setVendor(String vendor) {
//		this.vendor = vendor;
//	}
//	public List<List<String>> getProducts() {
//		return products;
//	}
//	public void setProducts(List<List<String>> products) {
//		this.products = products;
//	}
//	public String getShipAdress1() {
//		return shipAdress1;
//	}
//	public void setShipAdress1(String shipAdress1) {
//		this.shipAdress1 = shipAdress1;
//	}
//	public String getShipAdress2() {
//		return shipAdress2;
//	}
//	public void setShipAdress2(String shipAdress2) {
//		this.shipAdress2 = shipAdress2;
//	}
//	public String getShipCity() {
//		return shipCity;
//	}
//	public void setShipCity(String shipCity) {
//		this.shipCity = shipCity;
//	}
//	public String getShipState() {
//		return shipState;
//	}
//	public void setShipState(String shipState) {
//		this.shipState = shipState;
//	}
//	public String getPostalCode() {
//		return postalCode;
//	}
//	public void setPostalCode(String postalCode) {
//		this.postalCode = postalCode;
//	}
//	public String getShipCountry() {
//		return shipCountry;
//	}
//	public void setShipCountry(String shipCountry) {
//		this.shipCountry = shipCountry;
//	}
//	public String getBuyerPhoneNumber() {
//		return buyerPhoneNumber;
//	}
//	public void setBuyerPhoneNumber(String buyerPhoneNumber) {
//		this.buyerPhoneNumber = buyerPhoneNumber;
//	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//	
//	public String getAmount() {
//		return amount;
//	}
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//	public String getTrackingno() {
//		return trackingno;
//	}
//	public void setTrackingno(String trackingno) {
//		this.trackingno = trackingno;
//	}
//	
//	public String getGuojia() {
//		return guojia;
//	}
//	public void setGuojia(String guojia) {
//		this.guojia = guojia;
//	}
//	public PrintLable() {
//		super();
//	}
//	public PrintLable(int id, String rlOrderNumber, String orderNumber,
//			String vendor, List<List<String>> products, String buyerName,
//			String shipAdress1, String shipAdress2, String shipCity,
//			String shipState, String postalCode, String shipCountry,
//			String buyerPhoneNumber, String date, String amount,
//			String trackingno, String guojia) {
//		super();
//		this.id = id;
//		this.rlOrderNumber = rlOrderNumber;
//		this.orderNumber = orderNumber;
//		this.vendor = vendor;
//		this.products = products;
//		this.buyerName = buyerName;
//		this.shipAdress1 = shipAdress1;
//		this.shipAdress2 = shipAdress2;
//		this.shipCity = shipCity;
//		this.shipState = shipState;
//		this.postalCode = postalCode;
//		this.shipCountry = shipCountry;
//		this.buyerPhoneNumber = buyerPhoneNumber;
//		this.date = date;
//		this.amount = amount;
//		this.trackingno = trackingno;
//		this.guojia = guojia;
//	}
//
//	public static List<RLOrder> printLablesToRLOrder(List<PrintLable> printLables){
//		List<RLOrder> os= new LinkedList<RLOrder>();
//		for (PrintLable p : printLables) {
//			RLOrder o = new RLOrder();
//			o.setAmount(p.getAmount());
//			o.setBuyername(p.getBuyerName());
//			o.setBuyerphonenumber(p.getBuyerPhoneNumber());
//			o.setDate(p.getDate());
//			o.setDescription(p.getProducts().get(0).get(4));
//			o.setGuojia(p.getGuojia());
//			o.setItemname(p.getProducts().get(0).get(1));
//			o.setPinming(p.getProducts().get(0).get(2));
//			o.setQuantity(p.getProducts().get(0).get(3));
//			o.setSkuno(p.getProducts().get(0).get(0));
//			o.setOrdernumber(p.getOrderNumber());
//			o.setPostalcode(p.getPostalCode());
//			o.setRlordernumber(p.getRlOrderNumber());
//			o.setShipaddress1(p.getShipAdress1());
//			o.setShipaddress2(p.getShipAdress2());
//			o.setShipcity(p.getShipCity());
//			o.setShipcountry(p.getShipCountry());
//			o.setShipstate(p.getShipState());
//			o.setVendor(p.getVendor());
//			o.setTrackingno(p.getTrackingno());
//			List<RLOrderItem> items = new LinkedList<RLOrderItem>();
//			List<List<String>>  products = p.getProducts();
//			for (List<String> list : products) {
//				RLOrderItem item = new RLOrderItem();
//				item.setSkuno(list.get(0));
//				item.setItemname(list.get(1));
//				item.setPinming(list.get(2));
//				item.setQuantity(list.get(3));
//				item.setDescription(list.get(4));
//				items.add(item);
//				System.out.println("list 222"+ list.get(1));
//			}
//			o.setRlorderitems(items);
//			os.add(o);
//		}
//		return os;
//	}
}
