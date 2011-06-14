package com.smit.productcontrol.testServcie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.directwebremoting.json.types.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GoogleTest {
	public static void main(String[] args) throws IOException{
		String url = "http://ajax.googleapis.com/ajax/services/search/web";
		String keyWord = URLEncoder.encode("Google搜索API","UTF-8");
		String command = "?v=1.0&q="+ keyWord;                //返回首页的四条记录
		 
		URL api = new URL(url + command);
		InputStream is = api.openStream();
		System.out.println(api.getHost()); 
		//读取返回结果(JSON数据格式)
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		while(true){
		    int byteReaded = is.read(buffer,0,bufferSize);
		    if(byteReaded != -1 && byteReaded >0){
		        os.write(buffer,0,byteReaded);
		    }else{
		        break;
		    }
		}
		is.close();
		String searchResults = new String(os.toByteArray(),"UTF-8");
		System.out.println(searchResults);
	}
	
	 
	//解析结果
//	JSONObject jsonObj = JSONObject.fromObject(searchResults);
//	JSONObject.
//	JSONObject responseData  = jsonObj.getJSONObject("responseData");
//	JSONArray results = responseData.getJSONArray("results");
//	 
//	for (int i = 0; i < results.size(); i++) {
//	    JSONObject item = (JSONObject)results.get(i);
//	    System.out.print(item.get("GsearchResultClass") + "\t");
//	    System.out.print(item.get("unescapedUrl") + "\t");
//	    System.out.print(item.get("url") + "\t");
//	    System.out.print(item.get("visibleUrl") + "\t");
//	    System.out.print(item.get("cacheUrl") + "\t");
//	    System.out.print(item.get("title") + "\t");
//	    System.out.print(item.get("titleNoFormatting") + "\t");
//	    System.out.println(item.get("content"));
//	}
	 
	

}
