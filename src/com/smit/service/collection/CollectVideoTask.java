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
		getYoukuVideo(url,101);
		//今日评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/1";
		getYoukuVideo(url,102);
		//今日浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/1";
		getYoukuVideo(url,103);
		//本周评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/2";
		getYoukuVideo(url,104);
		//本周浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/2";
		getYoukuVideo(url,105);
		//本月评论最多视频
		url = "http://www.youku.com/index/rss_comment_day_videos/duration/3";
		getYoukuVideo(url,106);
		//本月浏览最多视频
		url = "http://www.youku.com/index/rss_hot_day_videos/duration/3";
		getYoukuVideo(url,107);
		
		//资讯
		url = "http://www.youku.com/index/rss_category_videos/cateid/91";
		getYoukuVideo(url,108);
		
		//原创
		url = "http://www.youku.com/index/rss_category_videos/cateid/92";
		getYoukuVideo(url,109);
		
		//电视剧
		url = "http://www.youku.com/index/rss_category_videos/cateid/97";
		getYoukuVideo(url,110);
		
		//娱乐
		url = "http://www.youku.com/index/rss_category_videos/cateid/86";
		getYoukuVideo(url,111);
		
		//电影
		url = "http://www.youku.com/index/rss_category_videos/cateid/96";
		getYoukuVideo(url,112);
		
		//体育
		url = "http://www.youku.com/index/rss_category_videos/cateid/98";
		getYoukuVideo(url,113);
		
		//音乐
		url = "http://www.youku.com/index/rss_category_videos/cateid/95";
		getYoukuVideo(url,114);
		
		//游戏
		url = "http://www.youku.com/index/rss_category_videos/cateid/99";
		getYoukuVideo(url,115);
		
		//动漫
		url = "http://www.youku.com/index/rss_category_videos/cateid/100";
		getYoukuVideo(url,116);
		
		//时尚
		url = "http://www.youku.com/index/rss_category_videos/cateid/89";
		getYoukuVideo(url,117);
		
		//母婴
		url = "http://www.youku.com/index/rss_category_videos/cateid/90";
		getYoukuVideo(url,118);
		
		//汽车
		url = "http://www.youku.com/index/rss_category_videos/cateid/104";
		getYoukuVideo(url,19);
		
		//旅游
		url = "http://www.youku.com/index/rss_category_videos/cateid/88";
		getYoukuVideo(url,120);
		
		//科技
		url = "http://www.youku.com/index/rss_category_videos/cateid/105";
		getYoukuVideo(url,121);
		
		//教育
		url = "http://www.youku.com/index/rss_category_videos/cateid/87";
		getYoukuVideo(url,122);
		
		//生活
		url = "http://www.youku.com/index/rss_category_videos/cateid/103";
		getYoukuVideo(url,123);
		
		//搞笑
		url = "http://www.youku.com/index/rss_category_videos/cateid/94";
		getYoukuVideo(url,124);
		
		//广告
		url = "http://www.youku.com/index/rss_category_videos/cateid/104";
		getYoukuVideo(url,125);
		
		//其他
		url = "http://www.youku.com/index/rss_category_videos/cateid/106";
		getYoukuVideo(url,126);
		
	}	
	
	
	
	public void tudou(){
		//TODO 因为土豆的描述字段没有使用CDATA，所有暂时无法解析
		System.out.println("Run tudou task ~");	
		
		//最新视频
		String url = "http://rss.tudou.com/feed?type=date";
		getYoukuVideo(url,201);
		
		//高清视频
		url = "http://rss.tudou.com/feed?type=h264";
		getYoukuVideo(url,202);
		
		//今日人气最旺视频
		url = "http://rss.tudou.com/feed?type=hot";
		getYoukuVideo(url,203);
		
		//今日打分最高的视频
		url = "http://rss.tudou.com/feed?type=rating";
		getYoukuVideo(url,204);
		
		//今日评论最狠视频
		url = "http://rss.tudou.com/feed?type=comment";
		getYoukuVideo(url,205);
		
		//今日收藏最多视频
		url = "http://rss.tudou.com/feed?type=favorite";
		getYoukuVideo(url,206);
		
		//所有推荐视频
		url = "http://rss.tudou.com/feed?type=recommend";
		getYoukuVideo(url,207);
		
		//所有人气最旺视频
		url = "http://rss.tudou.com/feed?type=hot&p=all";
		getYoukuVideo(url,208);
		
		//所有打分最高的视频
		url = "http://rss.tudou.com/feed?type=rating&p=all";
		getYoukuVideo(url,209);
		
		//所有评论最狠的视频
		url = "http://rss.tudou.com/feed?type=comment&p=all";
		getYoukuVideo(url,210);
		
		//所有收藏最多的视频
		url = "http://rss.tudou.com/feed?type=favorite&p=all";
		getYoukuVideo(url,211);
		
		//娱乐
		url = "http://rss.tudou.com/feed?type=recommend&p=1";
		getYoukuVideo(url,212);
		
		//乐活
		url = "http://rss.tudou.com/feed?type=recommend&p=3";
		getYoukuVideo(url,213);
		
		//动画
		url = "http://rss.tudou.com/feed?type=recommend&p=9";
		getYoukuVideo(url,214);
		
		//游戏
		url = "http://rss.tudou.com/feed?type=recommend&p=10";
		getYoukuVideo(url,215);
		
		//音乐
		url = "http://rss.tudou.com/feed?type=recommend&p=14";
		getYoukuVideo(url,216);
		
		//体育
		url = "http://rss.tudou.com/feed?type=recommend&p=15";
		getYoukuVideo(url,217);
		
		//科技
		url = "http://rss.tudou.com/feed?type=recommend&p=21";
		getYoukuVideo(url,218);
		
		//影视
		url = "http://rss.tudou.com/feed?type=recommend&p=22";
		getYoukuVideo(url,219);
		
		
		//财富
		url = "http://rss.tudou.com/feed?type=recommend&p=24";
		getYoukuVideo(url,220);
		
		//教育
		url = "http://rss.tudou.com/feed?type=recommend&p=25";
		getYoukuVideo(url,221);
		
		
		//汽车
		url = "http://rss.tudou.com/feed?type=recommend&p=26";
		getYoukuVideo(url,222);
		
		//女性
		url = "http://rss.tudou.com/feed?type=recommend&p=27";
		getYoukuVideo(url,223);
		
		//热点
		url = "http://rss.tudou.com/feed?type=recommend&p=29";
		getYoukuVideo(url,224);
		
		//搞笑
		url = "http://rss.tudou.com/feed?type=recommend&p=5";
		getYoukuVideo(url,225);
		
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
