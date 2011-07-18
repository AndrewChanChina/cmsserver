package com.smit.service.collection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.smit.vo.News;

public class FeedParser {

	private URL url;
	
	public  FeedParser(String feedurl){
		try {
			this.url = new URL(feedurl);
		} catch (MalformedURLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<FeedVideo> parse() throws Exception{
		List<FeedVideo> list = new ArrayList<FeedVideo>();
		List<Element> elements = getItemList();
		
		if(elements.size()>0){
			for(Element e : elements){
				FeedVideo fv = new FeedVideo();
				fv.setTitle(e.elementText("title"));
				fv.setLink(e.elementText("link"));
				String desc = e.elementText("description");
				fv.setDescription(desc);
				fv.setEnclosure_url(e.element("enclosure").attributeValue("url"));
				fv.setPubDate(e.elementText("pubDate"));
				fv.setGuid(e.elementText("guid"));
				if(!"".equals(desc)&&null!=desc){
					Document doc = DocumentHelper.parseText("<root>"+desc+"</root>");
					fv.setImg(doc.getRootElement().element("a").element("img").attributeValue("src"));
				}
				list.add(fv);
			}
		}
		return list;
	}
	
	public List<News> parseNews() throws Exception{
		List<News> list = new ArrayList<News>();
		List<Element> elements  = getItemList();
		if(elements.size()>0){
			for(Element e : elements){
				News news = new News();
				news.setTitle(e.elementText("title").trim());
				news.setLink(e.elementText("link"));
				news.setAuthor(e.elementText("author"));
				news.setGuid(e.elementText("guid"));
				news.setCategory(e.elementText("category").trim());
				news.setPubDate(e.elementText("pubDate"));
				news.setComments(e.elementText("comments").trim());
				news.setDescription(e.elementText("description").trim());
				list.add(news);
			}
		}
		return list;
	}
	private List<Element> getItemList() throws DocumentException, IOException {
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(url.openStream());
		Element channel = doc.getRootElement().element("channel");
		List<Element> elements = channel.elements("item");
		return elements;
	}
}
