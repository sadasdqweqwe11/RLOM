package com.raylinetech.ssh2.logistics.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_orderFile")
public class OrderFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private long uid;
	@Column
	private Date postdatetime;
	@Column
	private String filename;
	@Column
	private String description;
	@Column
	private String filetype;
	@Column
	private long filesize;	
	@Column
	private String originalfilename;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Date getPostdatetime() {
		return postdatetime;
	}
	public void setPostdatetime(Date postdatetime) {
		this.postdatetime = postdatetime;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	public OrderFile(long id, long uid, Date postdatetime, String filename,
			String description, String filetype, long filesize,
			String originalfilename) {
		super();
		this.id = id;
		this.uid = uid;
		this.postdatetime = postdatetime;
		this.filename = filename;
		this.description = description;
		this.filetype = filetype;
		this.filesize = filesize;
		this.originalfilename = originalfilename;
	}
	public OrderFile() {
		super();
	}

}
