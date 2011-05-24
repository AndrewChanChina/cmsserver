<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = session.getAttribute("userName") == null?"":(String)session.getAttribute("userName");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>国微CMS管理系统</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>

<style type="text/css">
*{margin:0;padding:0}
html{height:100%;overflow:hidden;}
body{height:100%;}
#logout{color:#FFF;padding-left:5px; background:url(./images/admin/logout.jpg) no-repeat;}
#welcome{color:#FFF;padding:0 10px 0 5px; background:url(./images/admin/msg.jpg) no-repeat 3px;}
#view_index{color:#FFF;}

.menu{padding-left:1em;font-size:12px;font-weight:700;float:left;margin:4px 4px 0 0;list-style:none;}
.menu li{float:left;width:60px;}
.menu li.sep{float:left;height:35px;width:10px;background:url(./images/admin/sep.jpg) left 3px no-repeat;}
.menu li a{display:block;height:35px;float:left;line-height:35px;padding:0 14px;color:#000;outline:none;hide-focus:expression(this.hideFocus=true);}
.menu li.current{background:url(./images/admin/nav_current.jpg) left top no-repeat;}
.menu li.current a{color:#fff;}

.undis{display:none;}
.dis{display:block;}
</style>

<script type="text/javascript">
function g(o){
	return document.getElementById(o);
}
function HoverLi(m,n,counter){
	for(var i=1;i<=counter;i++){
		g('tb_'+m+i).className='';
	}
	g('tb_'+m+n).className='current';
}
function changeSite(siteId) {
	
}
</script>

</head>

<body>
<div id="top">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="223">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="./images/admin/logo.jpg"></td>
      </tr>
      <tr>
        <td height="33" align="center" background="./images/admin/time_bg.jpg">
		<img src="./images/admin/ico3.jpg">&nbsp;现在时间：
       <script language="javascript">
		var day="";
		var month="";
		var ampm="";
		var ampmhour="";
		var myweekday="";
		var year="";
		mydate=new Date();
		myweekday=mydate.getDay();
		mymonth=mydate.getMonth()+1;
		myday= mydate.getDate();
		year= mydate.getFullYear();
		if(myweekday == 0)
		weekday=" 星期日 ";
		else if(myweekday == 1)
		weekday=" 星期一 ";
		else if(myweekday == 2)
		weekday=" 星期二 ";
		else if(myweekday == 3)
		weekday=" 星期三 ";
		else if(myweekday == 4)
		weekday=" 星期四 ";
		else if(myweekday == 5)
		weekday=" 星期五 ";
		else if(myweekday == 6)
		weekday=" 星期六 ";
		document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday);
	   </script>
       </td>
      </tr>
    </table></td>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="54"><img src="./images/admin/top_bg.jpg"></td>
        <td background="./images/admin/top_bg.jpg"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="260" height="30">
			<img src="./images/admin/ico1.gif"><span id="welcome">你好,<%=userName %></span>
			<img src="./images/admin/ico2.jpg"><a href="login_adminj.do" target="_top" id="logout" onclick="">退出</a></td>
            <td align="right">
         
            </td>
           
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><img src="./images/admin/top_07.jpg"></td>
        <td background="./images/admin/nav_bg.jpg">
		   <ul class="menu">			
		<li class="current" id="tb_11" onclick="HoverLi(1,1,10);"><a href="showMainFrame.do?class=sysInfoAndLog" target="mainFrame">配置</a></li>
				
			<li class="sep"></li><li id="tb_12" onclick="HoverLi(1,2,10);"><a href="showMainFrame.do?class=user" target="mainFrame">用户</a></li>
		
			<li class="sep"></li><li id="tb_13" onclick="HoverLi(1,3,10);"><a href="showMainFrame.do?class=column" target="mainFrame">栏目</a></li>
		
			<li class="sep"></li><li id="tb_14" onclick="HoverLi(1,4,10);"><a href="showMainFrame.do?class=content" target="mainFrame">内容</a></li>
		

			<li class="sep"></li><li id="tb_15" onclick="HoverLi(1,5,10);"><a href="showMainFrame.do?class=column" target="mainFrame">上传</a></li>
			
			<li class="sep"></li><li id="tb_16" onclick="HoverLi(1,6,10);"><a href="showMainFrame.do?class=log" target="mainFrame">测试</a></li>
			
			<li class="sep"></li><li id="tb_17" onclick="HoverLi(1,7,10);"><a href="showMainFrame.do?class=auth" target="mainFrame">授权</a></li>

			</ul>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</body>
</html>