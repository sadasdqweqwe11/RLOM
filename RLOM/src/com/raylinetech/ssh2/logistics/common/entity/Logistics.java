package com.raylinetech.ssh2.logistics.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "logistics_logistics")
public class Logistics implements Serializable{

	private static final long serialVersionUID = 4395218248450599299L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Column
	private String engname;
	@Column
	private String logifunc;
	@Column
	private String account;
	@Column
	private String password;
	@Column
	private int trackingstore;
	@Column
	private String mailto;
	@Column
	private String address;
	@Column
	private String postalcode;
	@Column
	private String phonenumber1;
	@Column
	private String phonenumber2;
	@Column
	private String remarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngname() {
		return engname;
	}
	public void setEngname(String engname) {
		this.engname = engname;
	}
	public Logistics(int id, String name, String engname) {
		super();
		this.id = id;
		this.name = name;
		this.engname = engname;
	}
	public Logistics() {
		super();
	}
	
	public String getLogifunc() {
		return logifunc;
	}
	public void setLogifunc(String logifunc) {
		this.logifunc = logifunc;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailto() {
		return mailto;
	}
	public void setMailto(String mailto) {
		this.mailto = mailto;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getPhonenumber1() {
		return phonenumber1;
	}
	public void setPhonenumber1(String phonenumber1) {
		this.phonenumber1 = phonenumber1;
	}
	public String getPhonenumber2() {
		return phonenumber2;
	}
	public void setPhonenumber2(String phonenumber2) {
		this.phonenumber2 = phonenumber2;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getTrackingstore() {
		return trackingstore;
	}
	public void setTrackingstore(int trackingstore) {
		this.trackingstore = trackingstore;
	}
	
	public Logistics(int id) {
		super();
		this.id = id;
	}
	public Logistics(int id, String name, String engname, String logifunc,
			String account, String password, int trackingstore, String mailto,
			String address, String postalcode, String phonenumber1,
			String phonenumber2, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.engname = engname;
		this.logifunc = logifunc;
		this.account = account;
		this.password = password;
		this.trackingstore = trackingstore;
		this.mailto = mailto;
		this.address = address;
		this.postalcode = postalcode;
		this.phonenumber1 = phonenumber1;
		this.phonenumber2 = phonenumber2;
		this.remarks = remarks;
	}
	
}
