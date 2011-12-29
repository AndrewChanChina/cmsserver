package com.smit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
	public static String formatMachineId(String machineID,int len) {
		//String[] ids = machineID.split("_");
		machineID = machineID.replace("_", "");
		if(machineID.length()<len){
			int length = machineID.length();
			for(int i =0;i<len-length;i++){
				machineID = machineID+"0";
			}
		}else if(machineID.length()>=len){
			machineID = machineID.substring(machineID.length()-len);
		}
		return machineID;
	}
	
	public static String getCheckId(String machineID){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		String timeStr = time.substring(2,time.length());
		System.out.println("get time is :"+ timeStr);
		String romdanNum = RandomStringUtils.randomNumeric(4);
		return timeStr+romdanNum+machineID;
	}
}
