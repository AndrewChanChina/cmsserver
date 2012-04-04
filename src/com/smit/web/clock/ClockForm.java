package com.smit.web.clock;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ClockForm extends ActionForm {
	
	private Integer id;
	
	private String name;
	private Integer hour;
	private Integer minutes;
	private Integer week_sun;
	private Integer week_mon;
	private Integer week_tus;
	private Integer week_wed;
	private Integer week_thurs;
	private Integer week_fri;
	private Integer week_satur;
	private String music;           // 默认的，还是服务器的
	private String server_music;
	private Integer vibrate;
	private Integer enable;
	private String status;
	private String roomnum;
	
	private Integer last_time;
	private Integer repeat_time;
	private Integer next_time;
	
	private String type;
	private String groupName;
	

	
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public boolean isGroup(){
		if("group".equals(type)){
			return true;
		}else{
			return false;
		}
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLast_time() {
		return last_time;
	}

	public void setLast_time(Integer last_time) {
		this.last_time = last_time;
	}

	public Integer getRepeat_time() {
		return repeat_time;
	}

	public void setRepeat_time(Integer repeat_time) {
		this.repeat_time = repeat_time;
	}

	public Integer getNext_time() {
		return next_time;
	}

	public void setNext_time(Integer next_time) {
		this.next_time = next_time;
	}

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

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getWeek_sun() {
		return week_sun;
	}

	public void setWeek_sun(Integer week_sun) {
		this.week_sun = week_sun;
	}

	public Integer getWeek_mon() {
		return week_mon;
	}

	public void setWeek_mon(Integer week_mon) {
		this.week_mon = week_mon;
	}

	public Integer getWeek_tus() {
		return week_tus;
	}

	public void setWeek_tus(Integer week_tus) {
		this.week_tus = week_tus;
	}

	public Integer getWeek_wed() {
		return week_wed;
	}

	public void setWeek_wed(Integer week_wed) {
		this.week_wed = week_wed;
	}

	public Integer getWeek_thurs() {
		return week_thurs;
	}

	public void setWeek_thurs(Integer week_thurs) {
		this.week_thurs = week_thurs;
	}

	public Integer getWeek_fri() {
		return week_fri;
	}

	public void setWeek_fri(Integer week_fri) {
		this.week_fri = week_fri;
	}

	public Integer getWeek_satur() {
		return week_satur;
	}

	public void setWeek_satur(Integer week_satur) {
		this.week_satur = week_satur;
	}

	public Integer getDayofWeek(){
		Alarm.DaysOfWeek dw = new Alarm.DaysOfWeek(0);
		if(week_mon != null){
			dw.set(0, true);
		}
		if(week_tus != null){
			dw.set(1, true);
		}
		if(week_wed != null){
			dw.set(2, true);
		}
		if(week_thurs != null){
			dw.set(3, true);
		}
		if(week_fri != null){
			dw.set(4, true);
		}
		if(week_satur != null){
			dw.set(5, true);
		}
		if(week_sun != null){
			dw.set(6, true);
		}
		return dw.getCoded();
	}
	
	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getServer_music() {
		return server_music;
	}

	public void setServer_music(String server_music) {
		this.server_music = server_music;
	}

	public Integer getVibrate() {
		if(vibrate == null){
			return 0;
		}else{
			return 1;
		}	
	}

	public void setVibrate(Integer vibrate) {
		this.vibrate = vibrate;
	}

	public Integer getEnable() {
		if(enable == null){
			return 0;
		}else{
			return 1;
		}		
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
		id  = null;
		
		name = null;
		hour = null;
		minutes = null;
		week_sun = null;
		week_mon = null;
		week_tus = null;
		week_wed = null;
		week_thurs = null;
		week_fri = null;
		week_satur = null;
		music = null;
		server_music = null;
		vibrate = null;
		enable = null;
		status = null;
		roomnum = null;
	}
	
	
	
	
}
