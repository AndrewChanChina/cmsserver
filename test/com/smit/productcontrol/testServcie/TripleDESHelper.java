package com.smit.productcontrol.testServcie;

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
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TripleDESHelper {
	private BASE64Encoder ebs64;
	private BASE64Decoder dbs64;
	private Cipher ci;
	private String key;
	
	public TripleDESHelper(String key){
		ebs64 = new BASE64Encoder();
		dbs64 = new BASE64Decoder();
		this.key = key;
		try {
			ci = Cipher.getInstance("TripleDES");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public String encode(String whiteText){
		String str = null;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "TripleDES");
			ci.init(Cipher.ENCRYPT_MODE, keySpec);
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
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),"TripleDES");
			ci.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] d = dbs64.decodeBuffer(encodeText);
			byte[] dStr = ci.doFinal(d);
			str = new String(dStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return str;
	}
	
	public static void main(String[] args){
		String key = "1234567890ABCDEFGHIJK45H";
		String orgStr = "this is a test of 3des!测试项";

		TripleDESHelper helper = new TripleDESHelper(key);
		String encodeStr = helper.encode(orgStr);
		System.out.println("加密后的字符串为："+encodeStr);
		String decodeStr = helper.decode(encodeStr);
		System.out.println("解密后字符串为："+decodeStr);
	}
}
