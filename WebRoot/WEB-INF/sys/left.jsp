<%@ page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.Part"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>jeecms-left</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="./css/dtree.css" type="text/css" />


<script type="text/javascript" src="./js/dtree.js"></script>
<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	Cms.lmenu('lmenu');
});

</script>
	</head>
	<body class="lbody">


	<ul id="lmenu">
<% 
	String opCode= (String)request.getParameter("class");
	if(opCode.equalsIgnoreCase("sysInfoAndLog"))
	{
%>

		<li><a href="showSysInfo.do" target="rightFrame">基本信息维护</a></li>
	
<% 

	}else if(opCode.equalsIgnoreCase("user")){
%>
			<li>
				<a href="listuser.do" target="rightFrame">用户管理</a>
			</li>
			<li>
				<a href="grouplist.do" target="rightFrame">分组管理</a>
			</li>
			<li>
				<a href="listpurview.do" target="rightFrame">权限管理</a>
			</li>	

<%	
	}else if(opCode.equalsIgnoreCase("log")){
%>
		<li><a href="showOption.do?class=test" target="rightFrame">测试选项</a></li>
		<li><a href="showBaseLog.do?class=order" target="rightFrame">生产批次</a></li>
		<li><a href="showBaseLog.do?class=base" target="rightFrame">基本信息</a></li>
		<li><a href="showBaseLog.do?class=detail" target="rightFrame">细分状态</a></li>
		
<%
	}else if(opCode.equalsIgnoreCase("auth")){
 %>
 		<li><a href="showAuth.do?class=login" target="rightFrame">登录鉴权</a></li>
 		<li><a href="showAuth.do?class=reqAuth" target="rightFrame">请求授权</a></li>
 		<li><a href="showAuth.do?class=active" target="rightFrame">激活</a></li>
 		<li><a href="showAuth.do?class=product" target="rightFrame">正品查询</a></li>
 		<li><a href="showAuth.do?class=confirm" target="rightFrame">确认启用</a></li>
 		<li><a href="showAuth.do?class=device" target="rightFrame">查询设备</a></li>
 <%
 	}else if(opCode.equalsIgnoreCase("content")){
%>

  
			<li><a href="content.do?op=add" target="rightFrame">发布内容</a></li>
         	<li><a href="content.do?op=list" target="rightFrame">内容管理</a></li>
			<script type="text/javascript">
			var columnDtree = new dTree('columnDtree');
			<%
				List<com.smit.vo.Part> allColumns = request.getAttribute("allColumns") == null ?null:(List<com.smit.vo.Part>)request.getAttribute("allColumns");
	            if(allColumns != null){
				
				
					for(int i = 0; i<allColumns.size(); i++)
					{
						Part part = allColumns.get(i);
						Integer id = part.getId();
						Integer topid = part.getTopid();
						Integer parentid = part.getFather_id();
						if(parentid == null)
						{
							parentid = 0;
						}
						String name = part.getTypename();
						String link = "content.do?op=list&pid=" + id ;
				%>
					columnDtree.add(<%=(id-1)%>,<%=(parentid-1)%>,"<%=name%>","<%=link%>","","rightFrame");
				<%
					}
				}
				else if(allColumns == null || allColumns.size() == 0)
				{
			%>
					columnDtree.add(0, -1 ,"所有栏目", "content.do?op=list","","rightFrame");
			<%
				}
			
			%>
				document.write(columnDtree);
				
			</script>


      
<%
   }else if(opCode.equalsIgnoreCase("upload")){
%>
 		<li><a href="uploadfile.do" target="rightFrame">上传资源</a></li>
        <li><a href="upload.do?op=list" target="rightFrame">资源管理</a></li>
  <%
  }
 %>
</ul>
</body>
</html>