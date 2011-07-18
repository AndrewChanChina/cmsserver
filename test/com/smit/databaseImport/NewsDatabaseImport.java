package com.smit.databaseImport;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.ColumnService;
import com.smit.service.collection.VideoService;
import com.smit.service.webService.ColumnImport;

import junit.framework.TestCase;

public class NewsDatabaseImport extends TestCase{

	BeanFactory beanFactory;
	VideoService vs;
	@Override
	protected void setUp() throws Exception {
		beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		vs = (VideoService)beanFactory.getBean("videoService");
		super.setUp();
		
	}

	public void testImportSina() throws Exception{
		ColumnService cs = (ColumnService)beanFactory.getBean("columnService");
		ColumnImport ci = new ColumnImport();
		ci.setTopColumn("sina", 300, cs);
		
		ci.addFirstColumn("新闻中心","301");
		ci.addFirstColumn("体育新闻", "302");
		ci.addFirstColumn("博客频道", "303");
		ci.addFirstColumn("科技新闻", "304");
		ci.addFirstColumn("财经新闻", "305");
		ci.addFirstColumn("军事新闻", "306");
		ci.addFirstColumn("女性新闻", "307");
		ci.addFirstColumn("汽车新闻", "308");
		ci.addFirstColumn("娱乐新闻", "309");
		ci.addFirstColumn("读书新闻", "310");
		ci.addFirstColumn("文化教育", "311");
		ci.addFirstColumn("房产新闻", "312");
		ci.addFirstColumn("游戏新闻", "313");
		ci.addFirstColumn("星座新闻", "314");
		ci.addFirstColumn("视频新闻", "315");
		
		//新闻中心
		ci.addSecondColumn("新闻要闻", 316,301);
		ci.addSecondColumn("国内要闻", 317,301);
		ci.addSecondColumn("国际要闻", 318,301);
		ci.addSecondColumn("社会要闻", 319,301);
		ci.addSecondColumn("时政要闻", 320,301);
		ci.addSecondColumn("港澳台新要闻", 321,301);
		ci.addSecondColumn("社会与法", 322,301);
		ci.addSecondColumn("社会万象", 323,301);
		ci.addSecondColumn("真情时刻", 324,301);
		ci.addSecondColumn("奇闻轶事", 325,301);
		
		//体育新闻
		ci.addSecondColumn("体育要闻汇总", 326,302);
		ci.addSecondColumn("焦点新闻", 327,302);
		ci.addSecondColumn("国际足坛", 328,302);
		ci.addSecondColumn("意甲", 329,302);
		ci.addSecondColumn("英超", 330,302);
		ci.addSecondColumn("西甲", 331,302);
		ci.addSecondColumn("德甲‘法甲", 332,302);
		ci.addSecondColumn("五洲热报", 333,302);
		ci.addSecondColumn("精彩图片", 334,302);
		ci.addSecondColumn("国内足坛", 335,302);
		ci.addSecondColumn("中国之队", 336,302);
		ci.addSecondColumn("篮球新闻", 337,302);
		ci.addSecondColumn("NBA新闻", 338,302);
		ci.addSecondColumn("CBA新闻", 339,302);
		ci.addSecondColumn("综合体育", 340,302);
		ci.addSecondColumn("彩票新闻", 341,302);
		ci.addSecondColumn("高尔夫新闻", 342,302);
		ci.addSecondColumn("F1赛车新闻", 343,302);
		ci.addSecondColumn("棋牌新闻", 344,302);
		
		//博客频道
		ci.addSecondColumn("文化", 345,303);
		ci.addSecondColumn("独家", 346,303);
		ci.addSecondColumn("情感", 347,303);
		ci.addSecondColumn("八卦", 348,303);
		ci.addSecondColumn("生活记录", 349,303);
		ci.addSecondColumn("观点", 350,303);
		ci.addSecondColumn("财经", 351,303);
		ci.addSecondColumn("股票", 352,303);
		ci.addSecondColumn("娱乐", 353,303);
		ci.addSecondColumn("女性", 354,303);
		ci.addSecondColumn("IT", 355,303);
		ci.addSecondColumn("房产", 356,303);
		ci.addSecondColumn("教育", 357,303);
		ci.addSecondColumn("星座", 358,303);
		ci.addSecondColumn("汽车", 359,303);
		ci.addSecondColumn("游戏", 360,303);
		ci.addSecondColumn("体育", 361,303);
		ci.addSecondColumn("美食", 362,303);
		ci.addSecondColumn("家居", 363,303);
		ci.addSecondColumn("育儿", 364,303);
		ci.addSecondColumn("健康", 365,303);
		ci.addSecondColumn("军事", 366,303);
		
		//科技新闻
		ci.addSecondColumn("科技要闻汇总", 367,304);
		ci.addSecondColumn("焦点新闻", 368,304);
		ci.addSecondColumn("互联网新闻", 369,304);
		ci.addSecondColumn("手机资讯", 370,304);
		ci.addSecondColumn("3G新闻", 371,304);
		ci.addSecondColumn("笔记本新闻", 372,304);
		ci.addSecondColumn("电信新闻", 373,304);
		ci.addSecondColumn("业界新闻", 374,304);
		ci.addSecondColumn("科技下载", 375,304);
		ci.addSecondColumn("科普要闻", 376,304);
		ci.addSecondColumn("数码资讯", 377,304);
		ci.addSecondColumn("家电资讯", 378,304);
		
		//财经新闻
		ci.addSecondColumn("财经要闻汇总 ", 379,305);
		ci.addSecondColumn("焦点新闻", 380,305);
		ci.addSecondColumn("股市及时雨", 381,305);
		ci.addSecondColumn("股票要闻汇总", 382,305);
		ci.addSecondColumn("基金要闻", 383,305);
		ci.addSecondColumn("理财要闻", 384,305);
		ci.addSecondColumn("美股快报", 385,305);
		ci.addSecondColumn("港股快讯", 386,305);
		ci.addSecondColumn("期货要闻", 387,305);
		
		//军事新闻
		ci.addSecondColumn("军事要闻汇总", 388,306);
		ci.addSecondColumn("焦点新闻", 389,306);
		ci.addSecondColumn("国际军情", 390,306);
		ci.addSecondColumn("中国军情", 391,306);
		
		//女性新闻
		ci.addSecondColumn("女性要闻汇总", 392,307);
		ci.addSecondColumn("美容美发", 393,307);
		ci.addSecondColumn("服饰潮流", 394,307);
		ci.addSecondColumn("美体瘦身", 395,307);
		ci.addSecondColumn("情感婚姻", 396,307);
		ci.addSecondColumn("八卦", 397,307);
		ci.addSecondColumn("亲子", 398,307);
		
		//汽车新闻
		ci.addSecondColumn("焦点新闻", 399,308);
		ci.addSecondColumn("车事评论", 400,308);
		ci.addSecondColumn("降价信息", 401,308);
		ci.addSecondColumn("国外新车", 402,308);
		ci.addSecondColumn("国产新车-业界动态", 403,308);
		ci.addSecondColumn("国产新车-40万以上新车预览", 404,308);
		ci.addSecondColumn("国产新车-25-40万新车一览", 405,308);
		ci.addSecondColumn("国产新车-20-25万新车一览", 406,308);
		ci.addSecondColumn("国产新车-15-20万新车一览", 407,308);
		ci.addSecondColumn("国产新车-10-15万新车一览", 408,308);
		ci.addSecondColumn("国产新车-10万以内新车一览", 409,308);
		
		//娱乐新闻
		ci.addSecondColumn("娱乐要闻汇总", 410,309);
		ci.addSecondColumn("焦点新闻", 411,309);
		ci.addSecondColumn("音乐", 412,309);
		ci.addSecondColumn("电视前沿", 413,309);
		ci.addSecondColumn("电影宝库", 414,309);
		ci.addSecondColumn("明星全接触", 415,309);
		ci.addSecondColumn("内地明星", 416,309);
		ci.addSecondColumn("港台明星", 417,309);
		ci.addSecondColumn("日韩明星", 418,309);
		ci.addSecondColumn("欧美明星", 419,309);
		
		//读书新闻
		ci.addSecondColumn("读书要闻汇总", 420,310);
		ci.addSecondColumn("最新连载", 421,310);
		ci.addSecondColumn("图书总排行", 422,310);
		ci.addSecondColumn("小说排行榜", 423,310);
		ci.addSecondColumn("人文历史排行榜", 424,310);
		ci.addSecondColumn("青春校园排行榜", 425,310);
		ci.addSecondColumn("生活娱乐排行榜", 426,310);
		ci.addSecondColumn("读书财经排行榜", 427,310);
		ci.addSecondColumn("读书军事排行榜", 428,310);
		
		//文化新闻
		ci.addSecondColumn("教育要闻汇总", 429,311);
		ci.addSecondColumn("焦点新闻", 430,311);
		ci.addSecondColumn("考试频道", 431,311);
		ci.addSecondColumn("高考", 432,311);
		ci.addSecondColumn("外语频道", 433,311);
		ci.addSecondColumn("考研", 434,311);
		ci.addSecondColumn("自考", 435,311);
		ci.addSecondColumn("成考", 436,311);
		ci.addSecondColumn("中考", 437,311);
		ci.addSecondColumn("英语考试", 438,311);
		ci.addSecondColumn("司法考试", 439,311);
		ci.addSecondColumn("会计考试", 440,311);
		ci.addSecondColumn("出国", 441,311);
		
		//游戏新闻
		ci.addSecondColumn("焦点新闻", 442,313);
		ci.addSecondColumn("电子竞技", 443,313);
		ci.addSecondColumn("网络游戏新闻", 444,313);
		ci.addSecondColumn("单机游戏新闻", 445,313);
		ci.addSecondColumn("游戏产业新闻", 446,313);
		ci.addSecondColumn("电视游戏新闻", 447,313);
		ci.addSecondColumn("手机游戏新闻", 448,313);
		ci.addSecondColumn("迷你游戏", 449,313);
		
		//星座新闻
		ci.addSecondColumn("焦点新闻", 450,314);
		ci.addSecondColumn("星座时尚热点", 451,314);
		ci.addSecondColumn("星座热文总排行", 452,314);
		
		//视频新闻
		ci.addSecondColumn("新闻视频排行", 453,315);
		ci.addSecondColumn("新闻", 454,315);
		ci.addSecondColumn("科技", 455,315);
		ci.addSecondColumn("财经", 456,315);
		ci.addSecondColumn("体育", 457,315);
		ci.addSecondColumn("娱乐", 458,315);
		ci.addSecondColumn("女性", 459,315);
		ci.addSecondColumn("影视MV热播排行视频", 460,315);
		ci.addSecondColumn("影视MV热评排行视频", 461,315);
		ci.addSecondColumn("影视MV最新视频", 462,315);
		
		ci.importColumn();
		ci.savesecond();
	}
}
