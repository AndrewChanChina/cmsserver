package com.smit.test.zhibo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class TestZip {
	
	public static void main(String[] args) throws Exception{
		ZipFile zipFile = new ZipFile("D:\\showtime.zip","GBK");
		for(Enumeration entries = zipFile.getEntries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry) entries.nextElement();
			//System.out.println(entries.);
			System.out.println(entry.getName());
			
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
	
}
