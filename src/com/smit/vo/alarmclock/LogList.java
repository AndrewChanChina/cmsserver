package com.smit.vo.alarmclock;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class LogList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_CLOCK = "clock";
	public static final String TYPE_APK = "apk";
	public static final String OP_CREATE = "create";
	public static final String OP_UPDATE = "update";
	public static final String OP_DELETE = "delete";
	
	private int id;
	
	private String type;
	private String roomNum;
	private String operation;
	private String operator;
	private String keyData;
	private String originData;	
	private Date date;
	private Timestamp timestamp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getKeyData() {
		return keyData;
	}
	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}
	public String getOriginData() {
		return originData;
	}
	public void setOriginData(String originData) {
		this.originData = originData;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	public void initTime(){
		long t = System.currentTimeMillis();
		this.timestamp = new Timestamp(t);
		this.date = new Date(t);
	}
	
}

