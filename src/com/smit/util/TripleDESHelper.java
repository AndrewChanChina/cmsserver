package com.smit.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TripleDESHelper {
	private BASE64Encoder ebs64;
	private BASE64Decoder dbs64;
	private Cipher ci;
	private String key;
	private IvParameterSpec ips;
	public TripleDESHelper(String key){
		ebs64 = new BASE64Encoder();
		dbs64 = new BASE64Decoder();
		this.key = key;
		try {
			ips = new IvParameterSpec(key.getBytes("utf-8"));
			ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String encode(String whiteText){
		String str = null;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			ci.init(Cipher.ENCRYPT_MODE, keySpec,ips);
			byte[] b = ci.doFinal(whiteText.getBytes());
			str = ebs64.encode(b);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return str;
	}
	
	public String decode(String encodeText){
		String str = null;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),"AES");
			ci.init(Cipher.DECRYPT_MODE, keySpec,ips);
			byte[] d = dbs64.decodeBuffer(encodeText);
			byte[] dStr = ci.doFinal(d);
			str = new String(dStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return str;
	}
	
	
	public static void main(String[] args){
		String key = "123456789ABCDEFG";
		String orgStr = "this is a test of 3des!测试项";

		TripleDESHelper helper = new TripleDESHelper(key);
		String encodeStr = helper.encode(orgStr);
		System.out.println("加密后的字符串为："+encodeStr);
		String decodeStr = helper.decode(encodeStr);
		System.out.println("解密后字符串为："+decodeStr);
		
		String s4 = "<applications><application><appname>TestAppInfo</appname><pname>com.demo.android.testappinfo</pname>" +
				"<versionname>1.0</versionname><versioncode>1</versioncode><machineid>xxxwww</machineid><permission>" +
				"android.permission.ACCESS_NETWORK_STATE,android.permission.INTERNET</permission><installtime>2011/06/28 04:57:31</installtime><runtime>0</runtime><uninstalltime></uninstalltime></application><application><appname>工厂测试</appname>" +
				"<pname>smit.com.factorytest</pname>" +
				"<versionname>1.0</versionname>" +
				"<versioncode>1</versioncode><machineid>xxxwww</machineid>" +
				"<permission>" +
				"com.google.FIGURATION,android.permission.MOUNT_UNMOUNT_FILESYSTEMS,android.permission.VIBRATE,android.permission.BLUETOOTH,android.permission.BLUETOOTH_ADMIN,android.permission.HARDWARE_TEST,android.permission.CALL_PHONE,android.permission.MODIFY_AUDIO_SETTINGS,android.permission.MASTER_CLEAR,android.permission.USE_CREDENTIALS,com.google.android.googleapps.permission.GOOGLE_AUTH,android.permission.ACCESS_DOWNLOAD_MANAGER,android.permission.READ_CONTACTS,android.permission.WRITE_CONTACTS,android.permission.ACCESS_WIFI_STATE,android.permission.CHANGE_WIFI_STATE,android.permission.INTERNET,android.permission.CLEAR_APP_USER_DATA,android.permission.READ_PHONE_STATE,android.permission.MODIFY_PHONE_STATE,android.permission.ACCESS_COARSE_LOCATION,android.permission.WRITE_APN_SETTINGS,android.permission.ACCESS_CHECKIN_PROPERTIES,android.permission.READ_USER_DICTIONARY,android.permission.WRITE_USER_DICTIONARY,android.permission.RESTART_PACKAGES,android.permission.PACKAGE_USAGE_STATS,android.permission.BATTERY_STATS,com.android.launcher.permission.READ_SETTINGS,com.android.launcher.permission.WRITE_SETTINGS,android.permission.ACCESS_NETWORK_STATE,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.RECORD_AUDIO,android.permission.CAMERA,android.permission.ACCESS_FINE_LOCATION</permission><installtime>2011/01/27 10:34:12</installtime><runtime>0</runtime><uninstalltime>" +
				"</uninstalltime></application>" +
				"</applications>";
		String s3 = helper.encode(s4);
		System.out.println("加密xmlis："+s3);
		System.out.println(helper.decode(s3));
		if(helper.decode(s3).equals(s4)){
			System.out.println("true");
		}
	}
}
