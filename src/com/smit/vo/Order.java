package com.smit.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String order_code;
	private String start_time;
	private String end_time;
	private String device_type;			//设备类型(1)
	private String manufacturer_code;//生产商代号(3)
	private String production_code;//产品型号(4)
	private String sn;             //产品序列号(8)
	private String mac;            //mac地址(12),一个范围值，这里是起始值。
	private String inf_code;     //其他信息
	private String machine_id;   //机器号
	
	//增加一个生产批次的数量
	private int mac_num;
	
	// add 3 attributes
	private String active_num;
	private String active_time;
	private String expire_time;
	private Set<Device> devices = new HashSet<Device>();
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set<Device> getDevices() {
		return devices;
	}
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
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
	public int getMac_num() {
		return mac_num;
	}
	public void setMac_num(int mac_num) {
		this.mac_num = mac_num;
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
