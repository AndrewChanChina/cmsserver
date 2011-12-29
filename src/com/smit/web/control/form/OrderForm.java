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
	//private String inf_code;
	private String[] options;
	private String mac;
	private String machine_id;
	
	private String selOption;
	
	//产量
	private String num;
	
	//add 3 attributes
	private String active_num;
	private String active_time;
	private String expire_time;
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
	
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public String getSelOption() {
		return selOption;
	}
	public void setSelOption(String selOption) {
		this.selOption = selOption;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getActive_num() {
		return active_num;
	}
	public void setActive_num(String active_num) {
		this.active_num = active_num;
	}
	public String getActive_time() {
		return active_time;
	}
	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	
	
}
