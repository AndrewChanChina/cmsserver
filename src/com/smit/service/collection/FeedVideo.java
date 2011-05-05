package com.smit.service.collection;

public class FeedVideo {
	
	String title;
	String description;
    String link; 
    String author;
    String guid;
    String itunes_duration;
    String itunes_keywords;
    String size;
    String comments;
    String pubDate;
    String enclosure_url;
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
	public String getItunes_duration() {
		return itunes_duration;
	}
	public void setItunes_duration(String itunes_duration) {
		this.itunes_duration = itunes_duration;
	}
	public String getItunes_keywords() {
		return itunes_keywords;
	}
	public void setItunes_keywords(String itunes_keywords) {
		this.itunes_keywords = itunes_keywords;
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
	
	public String toString(){
		String s = "title:" + title + "description:" + description + "link:" + link
		+ "author:" + author + "guid:" + guid + "duration:" + itunes_duration
		+ "keywords:" + itunes_keywords + "size:" + size
		+ "comments:" + comments + "pubDate:" + pubDate + "url:" + enclosure_url;
		return s;
	}
    
    

}
