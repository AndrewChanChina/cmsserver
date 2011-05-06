package com.smit.web.control.form;

import org.apache.struts.action.ActionForm;

public class OrderForm extends ActionForm{

	private String name;
	private String order_code;
	private String start_time;
	private String end_time;
	private String device_type;			//设备类型
	private String manufacturer_code;//生产商代号
	private String production_code;//产品型号
	private String sn;             //序列号
	private String inf_code;
	//private String testO
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getManufacturer_code() {
		return manufacturer_code;
	}
	public void setManufacturer_code(String manufacturer_code) {
		this.manufacturer_code = manufacturer_code;
	}
	public String getProduction_code() {
		return production_code;
	}
	public void setProduction_code(String production_code) {
		this.production_code = production_code;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getInf_code() {
		return inf_code;
	}
	public void setInf_code(String inf_code) {
		this.inf_code = inf_code;
	}
	
	
}
