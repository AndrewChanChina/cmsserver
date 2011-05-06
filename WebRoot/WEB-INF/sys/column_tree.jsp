<%@page import="org.hibernate.impl.SessionFactoryImpl"%>

<%@ page contentType= "text/html;charset=UTF-8" language= "java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="com.smit.vo.Part"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>jeecms-left</title>
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
<script src="./js/admin.js" type="text/javascript"></script>

<link rel="StyleSheet" href="./css/dtree.css" type="text/css" />
<script type="text/javascript" src="./js/dtree.js"></script>

<script type="text/javascript">
$(function(){
	$("#browser").treeview({
		//url: "v_tree.do"
	});
	Cms.lmenu("lmenu");
});
</script>
</head>
<body class="lbody">


<div class="lttop">
	<a href="javascript:location.href=location.href">刷新</a>&nbsp;
	<a href="#" target="rightFrame">模型管理</a>
</div>
<hr/>
	
<ul id="browser" class="filetree">
</ul>
	<script type="text/javascript">
	var columnDtree = new dTree('columnDtree');
	//columnDtree.add(0,0,"系统栏目","content.do?op=list");
<%
	List<Part> allColumns = (List<Part>)request.getAttribute("allColumns");
	Part rootColumn = (Part)request.getAttribute("rootColumn");
	for(int i = 0; i<allColumns.size(); i++)
	{
		Part part = allColumns.get(i);
		Integer id = part.getId();
		Integer topid = part.getTopid();
		Integer parentid = part.getFather_id();
		String link = null;
		if(rootColumn.getId().equals(id))
		{
			link = "<a href='content.do?op=list></a>";
		}
		else
		{
			link = "<a href='content.do?op=list&pid=" + id + "></a>";
		}
		if(parentid == null)
		{
			parentid = 0;
		}
		String name = part.getTypename();	
%>
		columnDtree.add(<%=(id-1)%>,<%=(parentid-1)%>,"<%=name%>","<%=link%>");
<%
	}
%>
	document.write(columnDtree);
	
</script>
</body>
</html>