<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>〓酒店主界面〓</title>
	<link rel="stylesheet" type="text/css" href="../css/im.css">
</head>
  
 <body>
<div id="mainbox">
	<div id="left">
		<div id="menus">
			<ul id="menu">
				<li>
					<dl>
						<dt class="one"><a href="#" target="main">酒店介绍</a></dt>
					</dl>
				</li>
				<li>
					<dl>
						<dt class="one"><a href="#">餐饮服务</a></dt>
						<dd style="display: none;"><a href="breakfast.html" target="main">早餐菜单</a></dd>
						<dd style="display: none;"><a href="lunch.html" target="main">中餐菜单</a></dd>
						<dd class="last" style="display: none;"><a href="dinner.html" target="main">晚餐菜单</a></dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt class="one"><a href="#">客房服务</a></dt>
						<dd style="display: none;"><a href="washing.html" target="main">洗衣</a></dd>
						<dd style="display: none;"><a href="clear.html" target="main">打扫</a></dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt class="one"><a href="#">康乐服务</a></dt>
						<dd style="display: none;"><a href="#" target="main">棋牌</a></dd>
						<dd style="display: none;"><a href="yuzu.html" target="main">足浴</a></dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt class="one"><a href="#">快速结账</a></dt>
					</dl>
				</li>
				<li>
					<dl>
						<dt class="one"><a href="#" target="main">客户反馈</a></dt>
					</dl>
				</li>
			</ul>
		</div>
	</div>
   <div id="right">
       <div id="about1_main">
	<div style="width:220px;height:40px;"><a href="about.html"><img src="../images/im/about1_logo.jpg" width="220px" height="40px"></a></div>
	<div id="about1_bm"><img src="../images/im/about1_tu.png" width="240px" height="355px"></div>
	<div id="about1_nav">
		<ul>
			<li><a href="jiudianzs.html">酒店展示</a></li>
			<li><a href="about_mo.html">电话指南</a></li>
			<li><a href="kefangss.html">客房设施</a></li>
			<li><a href="canyinss.html">餐饮设施</a></li>
			<li><a href="huiyiss.html">会义设施</a></li>
			<li><a href="kehusz.html">客户须知</a></li>
		</ul>
	</div>
	<div id="about1_kj">
		<div style="width:auto;height:auto;margin:5px 10px 0px 10px;">
			<h2 style="font-size:14px;">　酒店客房推荐</h2>
		</div>
		<div id="tu_left">
			<li><img src="../images/im/jiu1.jpg" width="150px" height="90px"></li>
			<li><img src="../images/im/jiu3.jpg" width="150px" height="90px"></li>
			<li><img src="../images/im/jiu4.jpg" width="150px" height="90px"></li>
		</div>
	</div>
	<div id="about1_jj">
		<div style="width:290px;height:100%;float:left;">
			<h2 style="font-size:14px;">酒店简介</h2>
			<p>深圳某某大酒店是一家按国际四星级标准装修改造的商务酒店，坐落罗湖区红桂路，地理位置优越，毗邻荔枝公园，门前绿树成荫，交通便利畅通，深圳某某大酒店是一家按国际四星级标准装修改造的商务酒店，坐落罗湖区红桂路，地理位置优越，毗邻荔枝公园，门前绿树成荫，交通便利畅通，距火车站、罗湖口岸及高交会馆仅需10分钟车程，距机场亦不过35分钟车程。设施先进，环境温馨，我们秉承“宾客至上，服务第一”的宗旨，为您提供舒适、惬意、如家般的享受，是您首选的下榻场所及黄金办公宝地。</p>
		</div>
	</div>
</div>
    </div>
</div>


</body>
</html>
