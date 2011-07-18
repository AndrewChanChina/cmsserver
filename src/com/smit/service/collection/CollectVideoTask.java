package com.smit.service.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.smit.dao.VideoDao;
import com.smit.vo.Content;
import com.smit.vo.Video;


public class CollectVideoTask {
	
	VideoService videoService;
	private List<Video> videos;
	public void youku() throws Exception{		
		System.out.println("Run youku task ~"+"time is:"+new Date());
		//get all videos save in database;
		//videos = videoService.getVideos();
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
		
		//run tudou task
		//tudou();
		url = "http://rss.tudou.com/feed?type=recommend";
		getYoukuVideo(url,207);
		
		//run tudou
		tudou();
	}	
	
	
	
	public void tudou() throws Exception{
		//TODO 因为土豆的描述字段没有使用CDATA，所有暂时无法解析
		System.out.println("Run tudou task ~");	
		
		//最新视频
		String url = "http://rss.tudou.com/feed?type=date";
		getTudouVideo(url,201);
		
		//高清视频
		url = "http://rss.tudou.com/feed?type=h264";
		getTudouVideo(url,202);
		
		//今日人气最旺视频
		url = "http://rss.tudou.com/feed?type=hot";
		getTudouVideo(url,203);
		
		//今日打分最高的视频
		url = "http://rss.tudou.com/feed?type=rating";
		getTudouVideo(url,204);
		
		//今日评论最狠视频
		url = "http://rss.tudou.com/feed?type=comment";
		getTudouVideo(url,205);
		
		//今日收藏最多视频
		url = "http://rss.tudou.com/feed?type=favorite";
		getTudouVideo(url,206);
		
		//所有推荐视频
		url = "http://rss.tudou.com/feed?type=recommend";
		getTudouVideo(url,207);
		
		//所有人气最旺视频
		url = "http://rss.tudou.com/feed?type=hot&p=all";
		getTudouVideo(url,208);
		
		//所有打分最高的视频
		url = "http://rss.tudou.com/feed?type=rating&p=all";
		getTudouVideo(url,209);
		
		//所有评论最狠的视频
		url = "http://rss.tudou.com/feed?type=comment&p=all";
		getTudouVideo(url,210);
		
		//所有收藏最多的视频
		url = "http://rss.tudou.com/feed?type=favorite&p=all";
		getTudouVideo(url,211);
		
		//娱乐
		url = "http://rss.tudou.com/feed?type=recommend&p=1";
		getTudouVideo(url,212);
		
		//乐活
		url = "http://rss.tudou.com/feed?type=recommend&p=3";
		getTudouVideo(url,213);
		
		//动画
		url = "http://rss.tudou.com/feed?type=recommend&p=9";
		getTudouVideo(url,214);
		
		//游戏
		url = "http://rss.tudou.com/feed?type=recommend&p=10";
		getTudouVideo(url,215);
		
		//音乐
		url = "http://rss.tudou.com/feed?type=recommend&p=14";
		getTudouVideo(url,216);
		
		//体育
		url = "http://rss.tudou.com/feed?type=recommend&p=15";
		getTudouVideo(url,217);
		
		//科技
		url = "http://rss.tudou.com/feed?type=recommend&p=21";
		getTudouVideo(url,218);
		
		//影视
		url = "http://rss.tudou.com/feed?type=recommend&p=22";
		getTudouVideo(url,219);
		
		
		//财富
		url = "http://rss.tudou.com/feed?type=recommend&p=24";
		getTudouVideo(url,220);
		
		//教育
		url = "http://rss.tudou.com/feed?type=recommend&p=25";
		getTudouVideo(url,221);
		
		
		//汽车
		url = "http://rss.tudou.com/feed?type=recommend&p=26";
		getTudouVideo(url,222);
		
		//女性
		url = "http://rss.tudou.com/feed?type=recommend&p=27";
		getTudouVideo(url,223);
		
		//热点
		url = "http://rss.tudou.com/feed?type=recommend&p=29";
		getTudouVideo(url,224);
		
		//搞笑
		url = "http://rss.tudou.com/feed?type=recommend&p=5";
		getTudouVideo(url,225);
		
	}
	public void other(){
		System.out.println("Run other task~");
		//6间房本日推荐
		String url = "http://6.cn/rss.php";
		getYoukuVideo(url, 0);
		
		//6间房最新节目
		url = "http://6.cn/rss.php?action=new";
		getYoukuVideo(url, 0);
	}
	public void getYoukuVideo(String url,int partId){
			
		try {
			
			TudouFeedParser p = new TudouFeedParser(url);	
			
			List<FeedVideo> list = p.readFeed();
			List<Video> listVideo = new ArrayList<Video>();
			int n = 0;
			int count = 0;
			if(null == videos){
				videos = videoService.getVideos();
			}
			
			for(FeedVideo v : list){
				System.out.println(v.getEnclosure_url());
				for(Video vs:videos){
					if(v.getGuid().equals(vs.getGuid())){
						count++;
					}
				}
				n++;
				Video video = new Video();
				video.setPartId(partId);
				video.setFlag1(v.getItunes_duration());
				video.setFlag2(v.getItunes_keywords());
				BeanUtils.copyProperties(video,v);	
				if(count<=0){
					listVideo.add(video);
				}
			} 
			videoService.save(listVideo);
			videos.addAll(listVideo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTudouVideo(String url,int partid) throws Exception{
		try{
			FeedParser fp = new FeedParser(url);
			List<FeedVideo> vs = fp.parse();
			List<Video> listVideo = new ArrayList<Video>();
			if(null==videos){
				videos = videoService.getVideos();
			}
			int count = 0;
			for(FeedVideo v:vs){
				for(Video d:videos){
					if(v.getGuid().equals(d.getGuid())){
						count++;
					}
				}
				if(count<=0){
					Video video = new Video();
					video.setPartId(partid);
					BeanUtils.copyProperties(video, v);
					listVideo.add(video);
				}
			}
			videoService.save(listVideo);
			videos.addAll(listVideo);
		}catch (Exception e){
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
