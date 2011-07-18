package com.smit.test.rss;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.smit.service.collection.CollectVideoTask;
import com.smit.service.collection.FeedParser;
import com.smit.service.collection.FeedVideo;
import com.smit.vo.News;



public class TestRss {
	public static void main(String[] args) throws Exception{
		URL url = new URL("http://www.cnblogs.com/honeyxing/rss");
		InputStream in = url.openStream();
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(in);
		Element root = doc.getRootElement();
		System.out.println(root.getName());
		Element channel = root.element("channel");
		System.out.println(channel.getName());

		List<Element> list = channel.elements("item");
		System.out.println(list.size());
		String text = list.get(0).elementText("description");
		//System.out.println(text);
//		Document doc2 = DocumentHelper.parseText("<root>"+ text +"</root>");
//		FeedParser fp = new FeedParser("http://rss.tudou.com/feed?type=rating");
//		List<FeedVideo> videos = fp.parse();
//		System.out.println(videos.size());
//		System.out.println(videos.get(0).getDescription());
//		for(FeedVideo fv:videos){
//			System.out.println(fv.getDescription());
//		}
		//System.out.println(doc2.asXML());
//		for(Element iem: list){
//			Element des = iem.element("description");
//			//List img = des.elements();
//			System.out.println("@@@@@"+des.getText()+"@@@@@@@");
//			//System.out.println(img.size());
//		}
		//new CollectVideoTask().getYoukuVideo("http://rss.tudou.com/feed?type=recommend", 207);
	}
}
