package com.smit.util.timer;

import java.util.Date;
import java.util.TimerTask;

import com.smit.service.clock.ClockService;
import com.smit.util.ApplicationCache;
import com.smit.util.ParamsString;
import com.smit.vo.alarmclock.Clock;

public class TimeOutTask extends TimerTask {

	private String Type;
	private int id;
	private String status;
	private String attributeName;
	private Clock backupClock = null;
	private ClockService clockService;
	
	public TimeOutTask(String type, int id, String attributname, 
			ClockService clockService, Clock backupClock){
		this.Type = type;
		this.id = id;
		this.attributeName = attributname;
		this.clockService = clockService;
		this.backupClock = backupClock;
	}
	
	@Override
	public void run() {
					
		if(clockService != null){
			Clock c = clockService.getById(id);
			if(c == null){
				return;
			}
			if(ParamsString.STATUS_SEND.equals(c.getStatus()) == false){
				System.out.println("TimeOutTask : good you success");
				return;
			}
			if(backupClock != null){
				c.copy(backupClock);
				clockService.deleteTrue(backupClock);
			}
			
			c.setStatus(ParamsString.STATUS_TIMEOUT);
			clockService.updateStatus(c);
			ApplicationCache.getInstance().setAttribute(attributeName, true);
		}
	}
	
	

}
