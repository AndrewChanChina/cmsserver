package com.smit.vo.alarmclock;

import java.io.Serializable;

import com.smit.web.clock.Alarm;
import com.smit.web.clock.Alarm.DaysOfWeek;

public class Clock_Rings implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_ADMIN = "admin";
	public static final String TYPE_USER = "user";
	
	private int id;
	private String name;
	
	private Integer id_local;
	private Integer hour;
	private Integer minutes;
	private Integer dayofweek;
	private String alarmtime;
	private Integer enable;
	private Integer vibrate;
	private String  label;			// 有管理员的，admin 也要 用户自己设置的 user
	public  Integer silent;
	private String alert;
	private String music;	  
	private String operation;
	private String status;
	private String roomnum;
	private Integer lastLong;    // 持续时间长度
	private Integer nextTime;    // 时间间隔	
	private Integer repeatTime;  // 重复次数
	
	private String dayofWeekString;
	
	private boolean[] dayofWeekArray;
	
	
	
	
	public Integer getRepeatTime() {
		return repeatTime;
	}
	public void setRepeatTime(Integer repeatTime) {
		this.repeatTime = repeatTime;
	}
	public Integer getLastLong() {
		return lastLong;
	}
	public void setLastLong(Integer lastLong) {
		this.lastLong = lastLong;
	}
	public Integer getNextTime() {
		return nextTime;
	}
	public void setNextTime(Integer nextTime) {
		this.nextTime = nextTime;
	}
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
	public Integer getId_local() {
		return id_local;
	}
	public void setId_local(Integer id_local) {
		this.id_local = id_local;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public Integer getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(Integer dayofweek) {
		this.dayofweek = dayofweek;		
	}
	public String getAlarmtime() {
		return alarmtime;
	}
	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getVibrate() {
		return vibrate;
	}
	public void setVibrate(Integer vibrate) {
		this.vibrate = vibrate;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getSilent() {
		return silent;
	}
	public void setSilent(Integer silent) {
		this.silent = silent;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDayofWeekString() {
		return dayofWeekString;
	}
	public void setDayofWeekString(String dayofWeekString) {
		this.dayofWeekString = dayofWeekString;
	}  
	
	public boolean[] getWeekofDayBooleanArray(){
		if(dayofweek == null){
			dayofweek = 0;
		}
		Alarm.DaysOfWeek dw = new Alarm.DaysOfWeek(dayofweek);
		dayofWeekArray = dw.getBooleanArray();
		return dayofWeekArray;
	}
	public boolean[] getDayofWeekArray() {
		return dayofWeekArray;
	}
	public void setDayofWeekArray(boolean[] dayofWeekArray) {
		this.dayofWeekArray = dayofWeekArray;
	}
}

