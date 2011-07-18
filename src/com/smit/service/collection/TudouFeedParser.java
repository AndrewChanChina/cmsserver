package com.smit.service.collection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import javax.xml.stream.events.Attribute;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.smit.vo.Video;

public class TudouFeedParser {

	static final String TITLE = "title";
	static final String LINK = "link";
	static final String DESCRIPTION = "description";
	static final String DURATION = "duration";
	static final String KEYWORDS = "keywords";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String SIZE = "size";
	static final String COMMENTS = "comments";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	static final String ENCLOSURE = "enclosure";
	
	final URL url;

	//modify by luocheng 2011-06-20
	
	public TudouFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
			
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<FeedVideo> readFeed() throws Exception {
		List<FeedVideo> list = new ArrayList<FeedVideo>();
		try {
		
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String title = "";
			String description = "";
		    String link = ""; 
		    String author = "";
		    String guid = "";
		    String itunes_duration = "";
		    String itunes_keywords = "";
		    String size = "";
		    String comments = "";
		    String pubDate = "";
		    String enclosure_url = "";
		    
		    String img = "";
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			while (eventReader.hasNext()) {

				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					if (event.asStartElement().getName().getLocalPart() == (ITEM)) {
						if (isFeedHeader) {
							isFeedHeader = false;							
						}
						event = eventReader.nextEvent();
						continue;
					}

					if (event.asStartElement().getName().getLocalPart() == (TITLE)) {
						event = eventReader.nextEvent();
						title = event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (DESCRIPTION)) {
						event = eventReader.nextEvent();
						description= event.asCharacters().getData(); 
						//modify by luocheng 2011-06-22
						if(!("".equals(description))&&null!=description){
							Document doc = getDoc(description);
							List<Element> elements = doc.getRootElement().elements("a");
							if(elements.size()>0){
								img = elements.get(0).element("img").attributeValue("src");
							}	
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart() == (LINK)) {
						event = eventReader.nextEvent();
						link= event.asCharacters().getData(); 
						continue;
					}

					if (event.asStartElement().getName().getLocalPart() == (GUID)) {
						event = eventReader.nextEvent();
						guid= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (DURATION)) {
						event = eventReader.nextEvent();
						itunes_duration= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (KEYWORDS)) {
						event = eventReader.nextEvent();
						if(event.isCharacters()){
							itunes_keywords= event.asCharacters().getData();
						}
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (AUTHOR)) {
						event = eventReader.nextEvent();
						//System.out.println("author event is "+event);
						author= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (PUB_DATE)) {
						event = eventReader.nextEvent();
						pubDate= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (SIZE)) {
						event = eventReader.nextEvent();
						size= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (COMMENTS)) {
						event = eventReader.nextEvent();
						comments= event.asCharacters().getData(); 
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == (ENCLOSURE)) {
												
						StartElement s = event.asStartElement();
						Attribute a = s.getAttributeByName(new QName("url"));
						enclosure_url = a.getValue();
						continue;
					}
					
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						FeedVideo video = new FeedVideo();
						video.setAuthor(author);
						video.setDescription(description);
						video.setGuid(guid);
						video.setLink(link);
						video.setTitle(title);
						video.setComments(comments);
						video.setEnclosure_url(enclosure_url);
						video.setItunes_duration(itunes_duration);
						video.setItunes_keywords(itunes_keywords);
						video.setPubDate(pubDate);
						video.setSize(size);
						video.setImg(img);
						list.add(video);						
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return list;

	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private Document getDoc(String text) throws Exception{
		Document doc = DocumentHelper.parseText("<root>"+text+"</root>");
		return doc;
	}


	
}
