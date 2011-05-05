package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Video implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7184230688376959652L;
	private Integer id;
	private String title;
	private String description;
	private String link; 
	private String author;
	private String guid;
	private String size;
	private String comments;
	private String pubDate;
	private String enclosure_url;
	private String flag1;
	private String flag2;
    private Timestamp createtime;
    private Integer partId; 	// 为了减少表的负担，不使用外键功能。手动进行数据的同步
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getEnclosure_url() {
		return enclosure_url;
	}
	public void setEnclosure_url(String enclosure_url) {
		this.enclosure_url = enclosure_url;
	}
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	public String getFlag2() {
		return flag2;
	}
	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}

}
