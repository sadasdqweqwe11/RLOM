package com.raylinetech.ssh2.logistics.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logistics_file")
public class OutputFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
	private String url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public OutputFile(int id, Date postdatetime, String filename, String description,
			String filetype, long filesize, String url) {
		super();
		this.id = id;
		this.postdatetime = postdatetime;
		this.filename = filename;
		this.description = description;
		this.filetype = filetype;
		this.filesize = filesize;
		this.url = url;
	}
	public OutputFile() {
		super();
	}

}
