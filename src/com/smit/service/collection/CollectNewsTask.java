package com.smit.service.collection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.smit.service.NewsService;
import com.smit.service.push.IPushDataService;
import com.smit.util.ApplicationCache;
import com.smit.util.Constants;
import com.smit.vo.News;

public class CollectNewsTask {

	private IPushDataService pushService;
	private NewsService newsService;
	private List<News> news;
	public void sina() throws Exception{
		System.out.println("Run sina news task~~!");
		//新闻中心   新闻要闻
		String url = "http://rss.sina.com.cn/news/marquee/ddt.xml";
		getSinaNews(url,316);
		
		//国内要闻
		url = "http://rss.sina.com.cn/news/china/focus15.xml";
		getSinaNews(url, 317);
		
		//国际要闻
		url = "http://rss.sina.com.cn/news/world/focus15.xml";
		getSinaNews(url, 318);
		
		//社会新闻
		url = "http://rss.sina.com.cn/news/society/focus15.xml";
		getSinaNews(url, 319);
		
		//时政要闻
		url = "http://rss.sina.com.cn/news/china/politics15.xml";
		getSinaNews(url, 320);
		
		//港澳台要闻
		url = "http://rss.sina.com.cn/news/china/hktaiwan15.xml";
		getSinaNews(url, 321);
		
		//社会与法
		url = "http://rss.sina.com.cn/news/society/law15.xml";
		getSinaNews(url, 322);
		
//		//社会万象
//		url = "http://rss.sina.com.cn/news/news/society/misc15.xml";
//		getSinaNews(url, 323);
		
		//真情时刻
		url = "http://rss.sina.com.cn/news/society/feeling15.xml";
		getSinaNews(url, 324);
		
		//奇闻轶事
		url = "http://rss.sina.com.cn/news/society/wonder15.xml";
		getSinaNews(url, 325);
		
		//体育新闻   要闻汇总
		url = "http://rss.sina.com.cn/roll/sports/hot_roll.xml";
		getSinaNews(url, 326);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/sports.xml";
		getSinaNews(url, 327);
		
		//国际足坛
		url = "http://rss.sina.com.cn/sports/global/focus.xml";
		getSinaNews(url, 328);
		
		//意甲
		url = "http://rss.sina.com.cn/sports/global/italy.xml";
		getSinaNews(url, 329);
		//英超
		url = "http://rss.sina.com.cn/sports/global/england.xml";
		getSinaNews(url, 330);
		//西甲
		url = "http://rss.sina.com.cn/sports/global/spain.xml";
		getSinaNews(url, 331);
		
		//德甲'法甲
		url = "http://rss.sina.com.cn/sports/global/germanyfrance.xml";
		getSinaNews(url, 332);
		
		//五洲热报
		url = "http://rss.sina.com.cn/sports/global/others.xml";
		getSinaNews(url, 333);
		
		//精彩图片
		url = "http://rss.sina.com.cn/sports/global/photo.xml";
		getSinaNews(url, 334);
		//国内足坛
		url = "http://rss.sina.com.cn/sports/china/focus.xml";
		getSinaNews(url, 335);
		
		//中国之队
		url = "http://rss.sina.com.cn/sports/china/team.xml";
		getSinaNews(url, 336);
		
		//篮球新闻
		url = "http://rss.sina.com.cn/sports/basketball/focus.xml";
		getSinaNews(url, 337);
		
		//NBA新闻
		url = "http://rss.sina.com.cn/sports/basketball/nba.xml";
		getSinaNews(url, 338);
		
		//CBA新闻
		url = "http://rss.sina.com.cn/sports/basketball/cba.xml";
		getSinaNews(url, 339);
		
		//综合体育
		url = "http://rss.sina.com.cn/sports/others/focus.xml"; 
		getSinaNews(url, 340);
		
		//彩票新闻
		url = "http://rss.sina.com.cn/sports/global/lottery.xml";
		getSinaNews(url, 341);
		
		//高尔夫新闻
		url = "http://rss.sina.com.cn/sports/global/golf.xml";
		getSinaNews(url, 342);
		//F1赛车新闻
		url = "http://rss.sina.com.cn/sports/global/f1.xml";
		getSinaNews(url, 343);
		//棋牌新闻
		url = "http://rss.sina.com.cn/sports/global/chess.xml";
		getSinaNews(url, 344);
		
		//博客频道    文化
		url = "http://rss.sina.com.cn/blog/index/cul.xml";
		getSinaNews( url, 345);
		
		//独家
		url = "http://rss.sina.com.cn/blog/index/exc.xml";
		getSinaNews(url, 346);
		
		//情感
		url = "http://rss.sina.com.cn/blog/index/feel.xml";
		getSinaNews(url, 347);
		
		//八卦
		url = "http://rss.sina.com.cn/blog/index/ent.xml";
		getSinaNews(url, 348);
		
		//生活记录
		url = "http://rss.sina.com.cn/blog/index/enjoy.xml";
		getSinaNews(url, 349);
		
		//观点
		url = "http://rss.sina.com.cn/blog/index/other.xml";
		getSinaNews(url, 350);
		
		//财经
		url = "http://rss.sina.com.cn/blog/index/finance.xml"; 
		getSinaNews(url, 351);
		
		//股票
		url = "http://rss.sina.com.cn/blog/index/stocks.xml";
		getSinaNews(url, 352);
		
		//娱乐
		url = "http://rss.sina.com.cn/blog/ent/yl.xml";
		getSinaNews(url, 353);
		
		//女性
		url = "http://rss.sina.com.cn/blog/eladies/nx.xml";
		getSinaNews(url, 354);
		
		//IT
		url = "http://rss.sina.com.cn/blog/tech/kj.xml";
		getSinaNews(url, 355);
		
		//房产
		url = "http://rss.sina.com.cn/blog/house/fc.xml";
		getSinaNews(url, 356);
		
		//教育
		url = "http://rss.sina.com.cn/blog/edu/jy.xml";
		getSinaNews(url, 357);
		
		//星座
		url = "http://rss.sina.com.cn/blog/astro/xz.xml";
		getSinaNews(url, 358);
		
		//汽车
		url = "http://rss.sina.com.cn/blog/auto/qc.xml";
		getSinaNews(url, 359);
		
		//游戏
		url = "http://rss.sina.com.cn/blog/games/yx.xml";
		getSinaNews(url, 360);
		
		//体育
		url = "http://rss.sina.com.cn/blog/sports/ty.xml";
		getSinaNews(url, 361);
		
		//美食
		url = "http://rss.sina.com.cn/blog/food/ms.xml";
		getSinaNews(url, 362);
		
		//家居
		url = "http://rss.sina.com.cn/blog/decor/jiaju.xml";
		getSinaNews(url, 363);
		
		//育儿
		url = "http://rss.sina.com.cn/blog/baby/ye.xml";
		getSinaNews(url, 364);
		
		//健康
		url = "http://rss.sina.com.cn/blog/news/jk.xml";
		getSinaNews(url, 365);
		
		//军事
		url = "http://rss.sina.com.cn/blog/mini/js.xml";
		getSinaNews(url, 366);
		
		//科技新闻    科技要闻汇总
		url = "http://rss.sina.com.cn/tech/rollnews.xml";
		getSinaNews(url, 367);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/tech.xml";
		getSinaNews(url, 368);
		
		//互联网新闻
		url = "http://rss.sina.com.cn/tech/internet/home28.xml";
		getSinaNews(url, 369);
		
		//手机资讯
		url = "http://rss.sina.com.cn/tech/mobile/mobile_6.xml";
		getSinaNews(url, 370);
		
		//3G新闻
		url = "http://rss.sina.com.cn/tech/3G/guonei.xml";
		getSinaNews(url, 371);
		
		//笔记本新闻
		url = "http://rss.sina.com.cn/tech/notebook/193_1.xml";
		getSinaNews(url, 372);
		
		//电信新闻
		url = "http://rss.sina.com.cn/tech/tele/gn37.xml";
		getSinaNews(url, 373);
		
		//业界新闻
		url = "http://rss.sina.com.cn/tech/it/gn37.xml";
		getSinaNews(url, 374);
		
		//科技下载
		url = "http://rss.sina.com.cn/tech/down/down20.xml";
		getSinaNews(url, 375);
		
		//科普要闻
		url = "http://rss.sina.com.cn/tech/discovery/discovery.xml";
		getSinaNews(url, 376);
		
		//数码资讯
		url = "http://rss.sina.com.cn/tech/number/new_camera.xml";
		getSinaNews(url, 377);
		
		//家电资讯
		url = "http://rss.sina.com.cn/tech/elec/buy_elec.xml";
		getSinaNews(url, 378);
		
		//财经新闻  财经要闻汇总
		url = "http://rss.sina.com.cn/roll/finance/hot_roll.xml";
		getSinaNews(url, 379);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/finance.xml";
		getSinaNews(url, 380);
		
		//股市及时雨
		url = "http://rss.sina.com.cn/finance/jsy.xml";
		getSinaNews(url, 381);
		
		//股票要闻汇总
		url = "http://rss.sina.com.cn/roll/stock/hot_roll.xml";
		getSinaNews(url, 382);
		
		//基金要闻
		url = "http://rss.sina.com.cn/finance/fund.xml";
		getSinaNews(url, 383);
		
		//理财要闻
		url = "http://rss.sina.com.cn/finance/financial.xml";
		getSinaNews(url, 384);
		
		//美股快报
		url = "http://rss.sina.com.cn/finance/usstock.xml";
		getSinaNews(url, 385);
		
		//港股快讯
		url = "http://rss.sina.com.cn/finance/hkstock.xml";
		getSinaNews(url, 386);
		
		//期货要闻
		url = "http://rss.sina.com.cn/finance/future.xml";
		getSinaNews(url, 387);
		
		//军事新闻    军事要闻汇总
		url = "http://rss.sina.com.cn/roll/mil/hot_roll.xml";
		getSinaNews(url, 388);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/jczs/focus.xml";
		getSinaNews(url, 389);
		
		//国际军情
		url = "http://rss.sina.com.cn/jczs/taiwan20.xml";
		getSinaNews(url, 390);
		
		//中国军情
		url = "http://rss.sina.com.cn/jczs/china15.xml";
		getSinaNews(url, 391);
		
		//女性新闻   女性要闻汇总
		url = "http://rss.sina.com.cn/news/allnews/eladies.xml";
		getSinaNews(url, 392);
		
		//美容美发
		url = "http://rss.sina.com.cn/eladies/gnspxw.xml";
		getSinaNews(url, 393);
		
		//服饰潮流
		url = "http://rss.sina.com.cn/eladies/fashion.xml";
		getSinaNews(url, 394);
		
		//美体瘦身
		url = "http://rss.sina.com.cn/eladies/body.xml";
		getSinaNews(url, 395);
		
		//情感婚姻
		url = "http://rss.sina.com.cn/eladies/marry.xml";
		getSinaNews(url, 396);
		
		//八卦
		url = "http://rss.sina.com.cn/eladies/gossip.xml";
		getSinaNews(url, 397);
		
		//亲子
		url = "http://rss.sina.com.cn/eladies/son.xml";
		getSinaNews(url, 398);
		
		//汽车新闻  焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/auto.xml";
		getSinaNews(url, 399);
		
		//车事评论
		url = "http://rss.sina.com.cn/auto/z/focus/2.xml";
		getSinaNews(url, 400);
		
		//降价信息
		url = "http://rss.sina.com.cn/auto/z/jiangjiachao/3.xml";
		getSinaNews(url, 401);
		
		//国外新车
		url = "http://rss.sina.com.cn/auto/z/2002-10newcar/6.xml";
		getSinaNews(url, 402);
		
		//国产新车-业界动态
		url = "http://rss.sina.com.cn/auto/z/guochanche/1.xml";
		getSinaNews(url, 403);
		
		//国产40万以上
		url = "http://rss.sina.com.cn/auto/z/guochanche/8.xml";
		getSinaNews(url, 404);
		
		//国产25-40万
		url = "http://rss.sina.com.cn/auto/z/guochanche/7.xml";
		getSinaNews(url, 405);
		
		//20-25
		url = "http://rss.sina.com.cn/auto/z/guochanche/6.xml";
		getSinaNews(url, 406);
		
		//15-20
		url = "http://rss.sina.com.cn/auto/z/guochanche/5.xml";
		getSinaNews(url, 407);
		
		//10-15
		url = "http://rss.sina.com.cn/auto/z/guochanche/4.xml";
		getSinaNews(url, 408);
		
		//10万以内
		url = "http://rss.sina.com.cn/auto/z/guochanche/3.xml";
		getSinaNews(url, 409);
		
		//娱乐新闻   娱乐要闻汇总
		url = "http://rss.sina.com.cn/ent/hot_roll.xml";
		getSinaNews(url, 410);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/ent.xml";
		getSinaNews(url, 411);
		
		//音乐
		url = "http://rss.sina.com.cn/ent/music/focus12.xml";
		getSinaNews(url, 412);
		
		//电视前沿
		url = "http://rss.sina.com.cn/ent/tv/focus7.xml";
		getSinaNews(url, 413);
		
		//电影宝库
		url = "http://rss.sina.com.cn/ent/film/focus7.xml";
		getSinaNews(url, 414);
		
		//明星全接触
		url = "http://rss.sina.com.cn/ent/star/focus7.xml";
		getSinaNews(url, 415);
		
		//内地明星
		url = "http://rss.sina.com.cn/ent/inner.xml";
		getSinaNews(url, 416);
		
		//港台明星
		url = "http://rss.sina.com.cn/ent/hongkong.xml";
		getSinaNews(url, 417);
		
		//日韩明星
		url = "http://rss.sina.com.cn/ent/star/japan.xml";
		getSinaNews(url, 418);
		
		//欧美明星
		url = "http://rss.sina.com.cn/ent/star/outer.xml";
		getSinaNews(url, 419);
		
		//读书新闻   读书要闻汇总
		url = "http://rss.sina.com.cn/book/info.xml";
		getSinaNews(url, 420);
		
		//最新连载
		url = "http://rss.sina.com.cn/book/new_book.xml";
		getSinaNews(url, 421);
		
		//图书总排行
		url = "http://rss.sina.com.cn/book/total_rank.xml";
		getSinaNews(url, 422);
		
		//小说排行榜
		url = "http://rss.sina.com.cn/book/story_rank.xml";
		getSinaNews(url, 423);
		
		//人文历史排行榜
		url = "http://rss.sina.com.cn/book/history_rank.xml";
		getSinaNews(url, 424);
		
		//青春校园排行榜
		url = "http://rss.sina.com.cn/book/school_rank.xml";
		getSinaNews(url, 425);
		
		//生活娱乐
		url = "http://rss.sina.com.cn/book/life_rank.xml";
		getSinaNews(url, 426);
		
		//读书财经
		url = "http://rss.sina.com.cn/book/finance_rank.xml";
		getSinaNews(url, 427);
		
		//读书军事
		url = "http://rss.sina.com.cn/book/military_rank.xml";
		getSinaNews(url, 428);
		
		//文化教育  教育要闻汇总
		url = "http://rss.sina.com.cn/roll/edu/hot_roll.xml";
		getSinaNews(url, 429);
		
		//焦点新闻
		url = "http://rss.sina.com.cn/edu/focus19.xml";
		getSinaNews(url, 430);
		
		//考试频道
		url = "http://rss.sina.com.cn/edu/exam.xml";
		getSinaNews(url, 431);
		
		//高考
		url = "http://rss.sina.com.cn/edu/gaokao.xml";
		getSinaNews(url, 432);
		
		//外语频道
		url = "http://rss.sina.com.cn/edu/en.xml";
		getSinaNews(url, 433);
		
		//考研
		url = "http://rss.sina.com.cn/edu/kaoyan.xml";
		getSinaNews(url, 434);
		
		//自考
		url = "http://rss.sina.com.cn/edu/zikao.xml";
		getSinaNews(url, 435);
		
		//成考
		url = "http://rss.sina.com.cn/edu/chengkao.xml";
		getSinaNews(url, 436);
		
		//中考
		url = "http://rss.sina.com.cn/edu/zhongkao.xml";
		getSinaNews(url, 437);
		
		//英语考试
		url = "http://rss.sina.com.cn/edu/yyks.xml";
		getSinaNews(url, 438);
		
		//司法考试
		url = "http://rss.sina.com.cn/edu/sifa.xml";
		getSinaNews(url, 439);
		
		//会计考试
		url = "http://rss.sina.com.cn/edu/cpa.xml";
		getSinaNews(url, 440);
		
		//出国
		url = "http://rss.sina.com.cn/edu/abroad5.xml";
		getSinaNews(url, 441);
		
		//游戏新闻     焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/games.xml";
		getSinaNews(url, 442);
		
		//电子竞技
		url = "http://rss.sina.com.cn/games/dzjj.xml";
		getSinaNews(url, 443);
		
		//网络游戏
		url = "http://rss.sina.com.cn/games/wlyx.xml";
		getSinaNews(url, 444);
		
		//单机游戏
		url = "http://rss.sina.com.cn/games/djyx.xml";
		getSinaNews(url, 445);
		
		//游戏产业新闻
		url = "http://rss.sina.com.cn/games/cyxw.xml";
		getSinaNews(url, 446);
		
		//电视游戏新闻
		url = "http://rss.sina.com.cn/games/dsyx.xml";
		getSinaNews(url, 447);
		
		//手机游戏新闻
		url = "http://rss.sina.com.cn/games/sjyx.xml";
		getSinaNews(url, 448);
		
		//迷你游戏
		url = "http://rss.sina.com.cn/games/littlegame.xml";
		getSinaNews(url, 449);
		
		//星座新闻     焦点新闻
		url = "http://rss.sina.com.cn/news/allnews/astro.xml";
		getSinaNews(url, 450);
		
		//星座时尚热点
		url = "http://rss.sina.com.cn/astro/fashion.xml";
		getSinaNews(url, 451);
		
		//星座热文总排行
		url = "http://rss.sina.com.cn/astro/hot.xml";
		getSinaNews(url, 452);
		
		//视频新闻   新闻视频排行
		url = "http://rss.sina.com.cn/bn/hot_bn.xml";
		getSinaNews(url, 453);
		
		//新闻
		url = "http://rss.sina.com.cn/bn/news.xml";
		getSinaNews(url, 454);
		
		//科技
		url = "http://rss.sina.com.cn/bn/tech.xml";
		getSinaNews(url, 455);
		
		//财经
		url = "http://rss.sina.com.cn/bn/finance.xml";
		getSinaNews(url, 456);
		
		//体育
		url = "http://rss.sina.com.cn/bn/sports.xml";
		getSinaNews(url, 457);
		
		//娱乐
		url = "http://rss.sina.com.cn/bn/ent.xml";
		getSinaNews(url, 458);
		
		//女性
		url = "http://rss.sina.com.cn/bn/eladies.xml";
		getSinaNews(url, 459);
		
		//影视MV热播排行视频
		url = "http://movie.video.sina.com.cn/rss/TOP.xml";
		getSinaNews(url, 460);
		
		//影视MV热评视频
		url = "http://movie.video.sina.com.cn/rss/COMMENT.xml";
		getSinaNews(url, 461);
		
		//影视MV最新视频
		url = "http://movie.video.sina.com.cn/rss/NEW.xml";
		getSinaNews(url, 462);
		
		//send msg to openfire
		ApplicationCache app = ApplicationCache.getInstance();
		String server = (String) app.getAttribute(Constants.SERVER_NAME);
		IPushDataService pushDataService = (IPushDataService) app.getAttribute(Constants.PUSH_CONNECTION);
		//不存在就是session过期，需要重新登录sever用户
		if(null==pushDataService || pushDataService.isConnected()==false){
			if(pushService.login(Constants.PUSH_HOST, Constants.PUSH_SERVERNAME, Constants.PUSH_SERVERPASSWORD)){
				app.setAttribute(Constants.PUSH_CONNECTION, pushService);
				pushDataService = pushService;
			}
		}
		pushDataService.sendPushDataFromDevToAll("I59ma75nmV67rWdD275jC0SQ2bJDBW5W", false, RandomStringUtils.randomNumeric(5), "update", "资讯有更新了", "http://"+getHostAddr()+":8080/pring/latestNews.do", "something update!");
	}
	public String getHostAddr() throws UnknownHostException{
		return InetAddress.getLocalHost().getHostAddress();
	}
	private void getSinaNews(String url, int partId) throws Exception {
		try{
			FeedParser fp = new FeedParser(url);
			List<News> sinaNews = fp.parseNews();
			List<News> listNews = new ArrayList<News>();
			if(null == news){
				news = newsService.getNews();
			}
			if(sinaNews.size()>0){
				for(News n:sinaNews){
					int count = 0;
					for(News ns: news){
						if(n.getGuid().equals(ns.getGuid())){
							count ++;
						}
					}
					if(count<=0){
						Date date = new Date();
						SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						n.setPartId(partId);
						n.setCreate_time(formater.format(date));
						listNews.add(n);
					}
				}
				newsService.insert(listNews);
				news.addAll(listNews);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public IPushDataService getPushService() {
		return pushService;
	}

	public void setPushService(IPushDataService pushService) {
		this.pushService = pushService;
	}
	
	
}
