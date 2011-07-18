package com.smit.productcontrol.testServcie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DesTest {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String s = "com.google.android.providers.gmail.permission.WRITE_GMAIL,com.google.android.providers.gmail.permission.READ_GMAIL,android.permission.WRITE_SETTINGS,android.permission.WRITE_SECURE_SETTINGS,android.permission.DEVICE_POWER,android.permission.CHANGE_CONFIGURATION,android.permission.MOUNT_UNMOUNT_FILESYSTEMS,android.permission.VIBRATE,android.permission.BLUETOOTH,android.permission.BLUETOOTH_ADMIN,android.permission.HARDWARE_TEST,android.permission.CALL_PHONE,android.permission.MODIFY_AUDIO_SETTINGS,android.permission.MASTER_CLEAR,android.permission.USE_CREDENTIALS,com.google.android.googleapps.permission.GOOGLE_AUTH,android.permission.ACCESS_DOWNLOAD_MANAGER,android.permission.READ_CONTACTS,android.permission.WRITE_CONTACTS,android.permission.ACCESS_WIFI_STATE,android.permission.CHANGE_WIFI_STATE,android.permission.INTERNET,android.permission.CLEAR_APP_USER_DATA,android.permission.READ_PHONE_STATE,android.permission.MODIFY_PHONE_STATE,android.permission.ACCESS_COARSE_LOCATION,android.permission.WRITE_APN_SETTINGS,android.permission.ACCESS_CHECKIN_PROPERTIES,android.permission.READ_USER_DICTIONARY,android.permission.WRITE_USER_DICTIONARY,android.permission.RESTART_PACKAGES,android.permission.PACKAGE_USAGE_STATS,android.permission.BATTERY_STATS,com.android.launcher.permission.READ_SETTINGS,com.android.launcher.permission.WRITE_SETTINGS,android.permission.ACCESS_NETWORK_STATE,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.RECORD_AUDIO,android.permission.CAMERA,android.permission.ACCESS_FINE_LOCATION";
		System.out.println(s.length());
		String s2 = "宸ュ巶娴嬭瘯";
		String s3 = URLDecoder.decode(s2, "utf-8");
		System.out.println(new String(s2.getBytes("gbk"),"utf-8"));
	}

}
  