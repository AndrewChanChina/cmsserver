package com.smit.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TestOption implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String test_id;
	private String create_time;
	private Set<DetailLog> detailLogs = new  HashSet<DetailLog>();//多对1
	
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
	public String getTest_id() {
		return test_id;
	}
	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set<DetailLog> getDetailLogs() {
		return detailLogs;
	}
	public void setDetailLogs(Set<DetailLog> detailLogs) {
		this.detailLogs = detailLogs;
	}
	
}
