package com.smit.productcontrol.testServcie;


import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


public class TripleDES {
    private static Logger logger = Logger.getLogger(TripleDES.class.getName());

    
    public static String getEncString(String key, String originStr) {
        String encStr = null;
        try {
           // SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "TripleDES");
            //Cipher nCipher = Cipher.getInstance("TripleDES");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "TripleDES");
            Cipher nCipher = Cipher.getInstance("TripleDES/ECB/NOPADDING");
             // TripleDES/ECB/NoPadding
            nCipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] v = nCipher.doFinal(originStr.getBytes());
            //encStr = bytesToHexString(v);
            encStr = new BASE64Encoder().encode(v);
            System.out.println(encStr.length());
            System.out.println(encStr);

        } catch (NoSuchAlgorithmException e) {
            logger.error("<getEncString>", e);
        } catch (NoSuchPaddingException e) {
            logger.error("<getEncString>", e);
        } catch (InvalidKeyException e) {
            logger.error("<getEncString>", e);
        } catch (IllegalBlockSizeException e) {
            logger.error("<getEncString>", e);
        } catch (BadPaddingException e) {
            logger.error("<getEncString>", e);
        }
        return encStr;

    }

    public static byte[] getDecriptStr(String key,String encodeStr){
    	byte[] b= null;
    	try {
    		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "TripleDES");
			Cipher cipher = Cipher.getInstance("TripleDES/ECB/NOPADDING");
			System.out.println(Security.getProviders().length);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			b = cipher.doFinal(new BASE64Decoder().decodeBuffer(encodeStr));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return b;
    }
    private static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString((0xFF & bArray[i]));
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

   /* ���Ե�main����
    * 
    *
    * */
    public static void main(String[] args) {
       String originStr= "123456789";
        //String originStr = "This is a 3DES test. 测试";
        String key = "COMPAY_100001_ymxKKK_PAY";
        //String key = "123456789ABCDE";
        String encodeStr = getEncString(key, originStr);
        System.out.println(encodeStr);
       // System.out.println(getDecriptStr(key, encodeStr));
        System.out.println("解密后的字符串为："+new String(getDecriptStr(key, encodeStr)));
    }

}

