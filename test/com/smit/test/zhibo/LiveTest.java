package com.smit.test.zhibo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.taskdefs.condition.Http;

public class LiveTest {
	public static void main (String[] args) throws Exception{
		// testPost();
//		 BufferedReader buffer = new BufferedReader(new InputStreamReader(in,"gbk"));
//		 String line = "";
//		 StringBuffer result = new StringBuffer();
//		 while((line=buffer.readLine())!=null){
//			result.append(line);
//		 }
	//	 System.out.println(result.toString());
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpGet get = new HttpGet("http://localhost:8080/pring/live.do?type=地方频道&channel=BTV卫视");
//		HttpResponse response = httpclient.execute(get);
//		System.out.println(response.getStatusLine().getStatusCode());
//		String s = "BTV卫视";
//		System.out.println(URLEncoder.encode(s, "utf-8"));
		URL url = new URL("http://localhost:8080/pring/live.do");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		 DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		 String content = "type="+URLEncoder.encode("地方频道","utf-8")+"&channel="+URLEncoder.encode("BTV卫视","utf-8");
		 out.writeBytes(content);
		 out.flush();
		 out.close();
		System.out.println(conn.getResponseCode());
	}

	private static void testPost() throws UnsupportedEncodingException,
			IOException, ClientProtocolException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// HttpHost proxy = new HttpHost("192.9.50.173",808);
		// httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		 HttpPost post = new HttpPost("http://localhost:8080/pring/live.do");
//		 post.addHeader("type", "地方频道");
//		 post.addHeader("channel","BTV卫视");
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("type", "地方频道"));
		 params.add(new BasicNameValuePair("channel", "BTV卫视"));
		 post.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
		 HttpResponse  response = httpclient.execute(post);
		 System.out.println(response.getStatusLine().getStatusCode());
		 InputStream in = response.getEntity().getContent();
		 StringBuffer line = new StringBuffer();
		 byte[] b = new byte[1024];
		 int n = 0;
		 while((n=in.read(b))!=-1){
			 line.append(new String(b,0,n));
		 }
		 System.out.println(new String(line.toString().getBytes(),"utf-8"));
	}
}
