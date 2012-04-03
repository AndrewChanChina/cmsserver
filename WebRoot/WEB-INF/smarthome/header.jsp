<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

 <div id="head_bg">
  	<div class='container'>
 		<div id='header'><a href="hotel.do">智能酒店管理系统</a></div>
 	</div>
</div>
<div class='container'>    	
 	<div id='side_left' class='hide_class0'>    		
 		<div id="main_menu">
 			<div class="menu_item  menu_item_sel"><a href="hotel.do?opt=main" target='mainframe'>首页</a></div>
 			<div class="menu_item"><a href="clock_room.do" target='mainframe'>用户团体</a></div>
 			<div class="menu_item"><a href="clock.do" target='mainframe'>叫醒服务</a></div>
 			<div class="menu_item"><a href="ring.do" target='mainframe'>声音管理</a></div> 			
 			<div class="menu_item"><a href="apk.do"  target='mainframe'>应用管理</a></div>
 		</div>
 		
 	</div>    	
</div>
     