package com.smit.service.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.smit.vo.Content;
import com.smit.vo.Video;


public class CollectVideoTask {
	
	VideoService vs;
	
	public void youku(){		
		System.out.println("Run youku task ~");		
	}
	
	public void getLatest(){
		String url = "http://www.youku.com/index/rss_category_videos/cateid/86";
		TudouFeedParser p = new TudouFeedParser(url);
		List<FeedVideo> list = p.readFeed();
		List<Video> listVideo = new ArrayList<Video>();
		int n = 0;
		
		try {
			for(FeedVideo v : list){
				System.out.println(v.getEnclosure_url());
				n++;
				Video video = new Video();			
				BeanUtils.copyProperties(video,v);	
				listVideo.add(video);				
			} 
			vs.save(listVideo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
