<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
    <head>
    	<meta http-equiv='Content-Type' content='text/html; charset=gb2312' />
        <title>ox travel</title>
        <!--meta name="viewport"  content="initial-scale=0.5; maximum-scale=10;  minimum-scale=0.5;"/-->
        <meta name="viewport"  content="target-densitydpi=high-dpi;maximum-scale=10;  minimum-scale=0.5;"/>
        <link type="text/css" rel="stylesheet" media="screen" href="./css/apple.css">   
        <link type="text/css" rel="stylesheet" media="screen" href="./css/text.css">  
        <link type="text/css" rel="stylesheet" media="screen" href="./css/dialog.css"> 
        <link type="text/css" rel="stylesheet" media="screen" href="./css/hotel_home.css">
        <script type="text/javascript" src="./js/jquery.js"></script>
    </head>
    <body>
    <tiles:insert attribute="header"></tiles:insert>
    
    <div class='container'>  
    	<div id='main'>
    		<p>当前酒店信息情况... 统计中....</p>
    		
    		<p>当前有140个房间开通本服务，有99个房间正在使用VOD点播</p>
    		
    		<p>近些年，深圳除原已引进的美丽华和香格里拉两个区域性酒店管理品牌之外，又先后迎来了洲际、万豪、雅高、喜来登、希尔顿、凯宾斯基等在国际上响当当的酒店管理公司，近期又和美国圣达特酒店集团牵手。同时，锦江、粤海等国内酒店管理公司也陆续进入深圳。但是，迄今为止，进入深圳的“洋酒管”所管理的最高级别酒店只有五星级，比如，被公认为目前深圳最高档的威尼斯酒店，使用的就是洲际酒店集团的“皇冠假日与度假村”品牌。然而，情况正在迅速发生变化。这些“洋酒管”的顶级品牌，将从明年开始陆续出现  华侨城洲际大酒店外景在深圳。</p>
    		<img src="./images/hotel/intro.jpg">
    	</div>   	
	</div>
	
	<tiles:insert attribute="footer"></tiles:insert>
    
    </body>
</html>