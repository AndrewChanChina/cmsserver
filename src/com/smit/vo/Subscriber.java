package com.smit.vo;

public class Subscriber {
	private int id;
	private String feed_url;//要订阅的rss地址
	private String callback_url;//hub要求的回调地址
	private int active_time;//订阅有效期
	private String create_time;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeed_url() {
		return feed_url;
	}
	public void setFeed_url(String feed_url) {
		this.feed_url = feed_url;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public int getActive_time() {
		return active_time;
	}
	public void setActive_time(int active_time) {
		this.active_time = active_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
