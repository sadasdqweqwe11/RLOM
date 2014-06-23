package com.raylinetech.ssh2.logistics.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "logistics_trackingno")
public class TrackingNo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String trackingno;
	@Column
	private long rlorderid;
	@Column
	private int logisticsid;
	@Column
	private int isused;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTrackingno() {
		return trackingno;
	}
	public void setTrackingno(String trackingno) {
		this.trackingno = trackingno;
	}

	public TrackingNo() {
		super();
	}
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}
	public long getRlorderid() {
		return rlorderid;
	}
	public void setRlorderid(long rlorderid) {
		this.rlorderid = rlorderid;
	}
	
	public int getLogisticsid() {
		return logisticsid;
	}
	public void setLogisticsid(int logisticsid) {
		this.logisticsid = logisticsid;
	}
	public TrackingNo(long id, String trackingno, long rlorderid,
			int logisticsid, int isused) {
		super();
		this.id = id;
		this.trackingno = trackingno;
		this.rlorderid = rlorderid;
		this.logisticsid = logisticsid;
		this.isused = isused;
	}
	
	
}
