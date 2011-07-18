package com.smit.service.collection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class CollectLiveTask {

	public  void download() throws Exception{
		String target = "D:\\showtime.zip";
		String url = "http://bugu.cntv.cn/download/showtime.zip";
		HttpClient httpclient = new HttpClient();
		PostMethod postmethod = new PostMethod(url);
		postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		int code = httpclient.executeMethod(postmethod);
		if(code == HttpStatus.SC_OK){
			InputStream in = postmethod.getResponseBodyAsStream();
			//BufferedReader buffer = new BufferedReader(new InputStreamReader(in,"GBK"));
			FileOutputStream output = new FileOutputStream(target);
			
			byte[] b = new byte[1024];
			int temp = 0;
			while((temp=in.read(b))!=-1){
				output.write(b,0,temp);
			}
			in.close();
			output.close();
			System.out.println("download success");
			
			unZip(target);
			System.out.println("release zip success!");
		}
	}

	private void unZip(String target) throws IOException, ZipException,
			FileNotFoundException {
		ZipFile zipFile = new ZipFile(target,"GBK");
		for(Enumeration entries = zipFile.getEntries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry) entries.nextElement();
			//System.out.println(entries.);
			
			if(entry.isDirectory()){
				File file = new File("D:/"+entry.getName());
				file.mkdirs();
				continue;
			}
				InputStream inputStream = zipFile.getInputStream(entry);
				File file = new File("D:/"+entry.getName());
				File parent = file.getParentFile();
				if(null!=parent&&!parent.exists()){
					parent.mkdirs();
				}
				FileOutputStream output = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int n =0;
				while((n=inputStream.read(b))!=-1){
					output.write(b, 0, n);
				}
				output.close();
				inputStream.close();
			}
	} 
	
	public static void main(String[] args) throws Exception{
		CollectLiveTask ct = new CollectLiveTask();
		ct.download();
		
	}
}
