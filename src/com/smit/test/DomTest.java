package com.smit.test;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();    
        Document document = null; 
        String xml = "<?xml version='1.0' encoding='UTF-8'?><devices>" +
        		"<device><name>camera</name><status>suc</status><note>tell me something more</note>" +
        		"</device><device><name>wifi</name><status>fail</status><note>tell me something more</note>" +
        		"</device></devices>";
//        try   
//        {    
//            document = saxReader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));  
//           Element root = document.getRootElement();
//        	//Element root = DocumentHelper.parseText(xml).getRootElement();
//            System.out.println(root.getName());
//        } catch (DocumentException e)    
//        {    
//            e.printStackTrace();    
//               
//        } catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}    
//        String str = "56789";
//        int length = str.length();
//        if(str.length()<16){
//        	for(int i =0;i<16-length;i++){
//        		str += '0';
//        	}
//        }
//        System.out.println(str);
        
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());
		int t = c.get(Calendar.MONTH);
		System.out.println(t);
		
	}

}
