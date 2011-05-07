package com.smit.service.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.smit.vo.Content;
import com.smit.vo.Video;


public class CollectVideoTask {
	
	VideoService videoService;
	
	public void youku(){		
		System.out.println("Run youku task ~");	
		//推荐视频
		String url = "http://www.youku.com/index/rss_cool_v/";
		getYoukuVideo(url,1);
		//今日评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/1";
		getYoukuVideo(url,2);
		//今日浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/1";
		getYoukuVideo(url,3);
		//本周评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/2";
		getYoukuVideo(url,4);
		//本周浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/2";
		getYoukuVideo(url,5);
		//本月评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/3";
		getYoukuVideo(url,6);
		//本月浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/3";
		getYoukuVideo(url,7);
		
		//资讯
		url = "http://www.youku.com/index/rss_category_videos/cateid/91";
		getYoukuVideo(url,8);
		
		//原创
		url = "http://www.youku.com/index/rss_category_videos/cateid/92";
		getYoukuVideo(url,9);
		
		//电视剧
		url = "http://www.youku.com/index/rss_category_videos/cateid/97";
		getYoukuVideo(url,10);
		
		//娱乐
		url = "http://www.youku.com/index/rss_category_videos/cateid/86";
		getYoukuVideo(url,11);
		
		//电影
		url = "http://www.youku.com/index/rss_category_videos/cateid/96";
		getYoukuVideo(url,12);
		
		//体育
		url = "http://www.youku.com/index/rss_category_videos/cateid/98";
		getYoukuVideo(url,13);
		
		//音乐
		url = "http://www.youku.com/index/rss_category_videos/cateid/95";
		getYoukuVideo(url,14);
		
		//游戏
		url = "http://www.youku.com/index/rss_category_videos/cateid/99";
		getYoukuVideo(url,15);
		
		//动漫
		url = "http://www.youku.com/index/rss_category_videos/cateid/100";
		getYoukuVideo(url,16);
		
		//时尚
		url = "http://www.youku.com/index/rss_category_videos/cateid/89";
		getYoukuVideo(url,17);
		
		//母婴
		url = "http://www.youku.com/index/rss_category_videos/cateid/90";
		getYoukuVideo(url,18);
		
		//汽车
		url = "http://www.youku.com/index/rss_category_videos/cateid/104";
		getYoukuVideo(url,19);
		
		//旅游
		url = "http://www.youku.com/index/rss_category_videos/cateid/88";
		getYoukuVideo(url,20);
		
		//科技
		url = "http://www.youku.com/index/rss_category_videos/cateid/105";
		getYoukuVideo(url,21);
		
		//教育
		url = "http://www.youku.com/index/rss_category_videos/cateid/87";
		getYoukuVideo(url,22);
		
		//生活
		url = "http://www.youku.com/index/rss_category_videos/cateid/103";
		getYoukuVideo(url,23);
		
		//搞笑
		url = "http://www.youku.com/index/rss_category_videos/cateid/94";
		getYoukuVideo(url,24);
		
		//广告
		url = "http://www.youku.com/index/rss_category_videos/cateid/104";
		getYoukuVideo(url,25);
		
		//其他
		url = "http://www.youku.com/index/rss_category_videos/cateid/106";
		getYoukuVideo(url,26);
		
	}	
	
	
	
	public void tudou(){		
		System.out.println("Run tudou task ~");	
		//推荐视频
		String url = "http://rss.tudou.com/feed?type=date";
		getYoukuVideo(url,101);
		
		url = "http://rss.tudou.com/feed?type=recommend&p=9";
		getYoukuVideo(url,102);
		
		
	}
	
	private void getYoukuVideo(String url,int partId){
			
		try {
			
			TudouFeedParser p = new TudouFeedParser(url);	
			
			List<FeedVideo> list = p.readFeed();
			List<Video> listVideo = new ArrayList<Video>();
			int n = 0;
			for(FeedVideo v : list){
				System.out.println(v.getEnclosure_url());
				n++;
				Video video = new Video();
				video.setPartId(partId);
				video.setFlag1(v.getItunes_duration());
				video.setFlag2(v.getItunes_keywords());
				BeanUtils.copyProperties(video,v);	
				listVideo.add(video);				
			} 
			videoService.save(listVideo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}



}
