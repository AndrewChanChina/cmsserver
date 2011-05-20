package com.smit.productcontrol.testServcie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.URLDecoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class XDEncodeHelper {
	public static final String TYPE = "DESede/CBC/PKCS5Padding";
	public static final String KEYTYPE = "DESede";

	IvParameterSpec ips = null;
	BASE64Encoder ebs64 = null;
	BASE64Decoder dbs64 = null;
	Cipher c1 = null;

	String strKey = null;

	public XDEncodeHelper(String sKey) {
		try {
			strKey = sKey;
			ips = new IvParameterSpec("12345678".getBytes("utf-8"));
			ebs64 = new BASE64Encoder();
			dbs64 = new BASE64Decoder();
			//c1 = Cipher.getInstance(TYPE);
			c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encode(String strWhiteText) // Ѷ����뺯��
	{
		byte[] byteUtf8 = null;

		try {
			byteUtf8 = strWhiteText.getBytes("utf-8");

			SecretKey deskey = new SecretKeySpec(strKey.getBytes(), KEYTYPE);
			c1.init(Cipher.ENCRYPT_MODE, deskey, ips);

			byte[] byteTemp = c1.doFinal(byteUtf8); // 0001 DES����
			String strBase64 = ebs64.encode(byteTemp); // 0002
														// �Լ��ܺ��ֽ�������BASE64����

			strBase64 = URLEncoder.encode(strBase64, "utf-8"); // 0003
																// ����Ϊutf-8��ʽ��UrlEncode

			return strBase64;
		} catch (Exception e) {
			e.printStackTrace();

			return "Encode_ERROR";
		}
	}

	/**
	 * Ĭ��ΪWeb������ʽ��
	 * 
	 * @param strBlackText
	 * @return
	 */
	public String decode(String strBlackText) // Ѷ����뺯��
	{
		return XDDecode(strBlackText, false);
	}

	/**
	 * ��CP��ʹ��Web������������������Ĭ�ϻ���URLDecode�������ʵ��ø÷���ʱ���������붯��
	 * ��isNeedDecodeӦ����false��
	 * 
	 * @param strBlackText
	 * @param isNeedDecode
	 * @return
	 */
	public String XDDecode(String strBlackText, boolean isNeedDecode) // Ѷ����뺯��
	{
		byte[] byteUtf8 = null;

		try {
			if (isNeedDecode) {
				strBlackText = URLDecoder.decode(strBlackText, "utf-8"); // 0001
																			// ����utf-8��ʽ����URLDecoder
			}

			byteUtf8 = strBlackText.getBytes("utf-8");
			byteUtf8 = dbs64.decodeBuffer(new String(byteUtf8)); // 0002
																	// ���ֽ�������BASE64����
			SecretKey deskey = new SecretKeySpec(strKey.getBytes(), KEYTYPE);
			c1.init(Cipher.DECRYPT_MODE, deskey, ips);

			byte[] byteEncrypted = c1.doFinal(byteUtf8); // 0003 DES����
			String strUtf8 = new String(byteEncrypted, "utf-8");
			return strUtf8;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	public static String getLoalIP() {
		String result = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			result = addr.getHostAddress().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
