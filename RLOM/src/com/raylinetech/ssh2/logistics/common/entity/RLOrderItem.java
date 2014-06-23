package com.raylinetech.ssh2.logistics.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_rlorder_item")
public class RLOrderItem implements Cloneable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="skuid")
	private Sku sku;
	@Column
	private String quantity;
	@Column
	private String description;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public RLOrderItem() {
		super();
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public RLOrderItem(long id, Sku sku, String quantity, String description) {
		super();
		this.id = id;
		this.sku = sku;
		this.quantity = quantity;
		this.description = description;
	}

	public static void main(String[] args) {
		RLOrderItem it = new RLOrderItem();
		it.setDescription("sfsfsf");
		try {
			RLOrderItem it2 = (RLOrderItem)it.clone();
			System.out.println(it2.description);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(123123123);
	}
}
