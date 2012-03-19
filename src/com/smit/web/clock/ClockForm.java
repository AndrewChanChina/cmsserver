package com.smit.web.clock;

import org.apache.struts.action.ActionForm;

public class ClockForm extends ActionForm {
	
	private Integer id;
	private String name;
	private Integer hour;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
