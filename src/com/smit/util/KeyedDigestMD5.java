package com.smit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyedDigestMD5 {
    
	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            return md5.digest(key);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
	
		
	public static String getKeyedDigest(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));
            
            String result="";
            byte[] temp;
            temp=md5.digest(key.getBytes("UTF8"));
    		for (int i=0; i<temp.length; i++){
    			result+=Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
    		}
    		
    		return result;
    		
        } catch (NoSuchAlgorithmException e) {
        	
        	e.printStackTrace();
        	
        }catch(Exception e)
        {
          e.printStackTrace();
        }
        return null;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mi;
		String mi1;
        String s = "agentid=646657&orderid=12321315&cardno=15165&cardpassword=dddd&productcode=12434&merchantKey=123456";
        String s1 = "sdklflasdjfljalsdfjlsdielj";
        
        //�ڶ�������������ַ�
		mi=KeyedDigestMD5.getKeyedDigest(s,"432");
		mi1=KeyedDigestMD5.getKeyedDigest(s1,"567");
		
		System.out.println("mi:"+mi+"  md5签名长度"+mi.length());
		System.out.println("mi1:"+mi1+"  md5签名长度"+mi1.length());
		
			
	}

}