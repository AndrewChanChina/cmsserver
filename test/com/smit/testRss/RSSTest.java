package com.smit.testRss;

import java.util.List;

import junit.framework.TestCase;

import com.smit.service.collection.FeedVideo;
import com.smit.service.collection.TudouFeedParser;

public class RSSTest extends TestCase {
	
	public void testRead() throws Exception{
		String url = "http://www.youku.com/index/rss_category_videos/cateid/86";
		TudouFeedParser p = new TudouFeedParser(url);
		List<FeedVideo> list = p.readFeed();
		int n = 0;
		for(FeedVideo v : list){
			System.out.println(v.toString());
			n++;
		}
		System.out.print(n);
		
	}


}
