<%@page import="org.hibernate.impl.SessionFactoryImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page contentType= "text/html;charset=GB2312" language= "java"%>
<%@ page import="com.smit.dao.*"%>
<%@ page import="com.smit.vo.*"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>

    <base href="<%=basePath%>">
    
    <!--  <title>My JSP 'SysInfo.jsp' starting page</title> -->
    <title><bean:message key="SysInfo.title"/></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
     <form action="login.do" method="post">
                  用户名：<input type="text" name="userName"/><br/>
                       密码：<input type="password" name="passwd"/><br/>
              <input type="submit" value="提交"/>
     </form>
  
    <form action="addSysInfo.do" method="post">
    	Add System Config Info Key:<input type="text" name="sysInfoKey"/><br/>
    	Add System Config Info Value:<input type="text" name="sysInfoValue"/><br/>
    	<input type="submit" value="Add Key-Value pair to system info config"/>
    </form>

    <table>
    <%
    //process "Edit Sys Info"
    String opCode = request.getParameter("opcode");
    String key = request.getParameter("key");
    String value = request.getParameter("value");
    if(key != null && value != null && opCode != null)
    {
    	System.out.println(opCode);
    	System.out.println(key);
    	System.out.println(value);
    	if(opCode.equalsIgnoreCase("edit"))
    	{
   	%>
		
	<%
    	}
    	else if(opCode.equalsIgnoreCase("delete"))
    	{
    		AddSysInfoDao sysInfoDao = new AddSysInfoDaoImpl();
    		sysInfoDao.deleteSysInfo(key);
    	}
    }
    %>
    	<%
    	 //<!-- <logic:forward name="success"/> -->
    	 AddSysInfoDao sysInfoDao = new AddSysInfoDaoImpl();
    	 List<SysInfo> list = sysInfoDao.queryAllSysInfo();
    	 if(list == null)
    	 {
    	 	return;
    	 }
    	 int length = list.size();
    	%>
    	 <tr>
    	 	<th>Key<th>
    	 	<th>Value<th>
    	 	<th>Op1<th>
    	 	<th>Op2<th>
    	 	<th>Op3<th>
    	 </tr>
    	 <%
    	 for(int i=0; i<length; i++)
    	 {//for begin
    	 	SysInfo info = (SysInfo)list.get(i);

    	 %>
	    	<tr>
	    	 	<td><%=info.getInfoKey()%><td>&nbsp;
	    	 	<td><%=info.getInfoValue()%><td>
	    	 	<% 
	    	 	//HashMap<String, String> keyAndValueMap = new HashMap<String, String>();
	    	 	//keyAndValueMap.put("key", info.getInfoKey());  
	    	 	//keyAndValueMap.put("value", info.getInfoValue());
	    	 	%>
	    	 	<td>
	    	 		<a href="SysInfo.jsp?opcode=eidt&key=<%=info.getInfoKey()%>&value=<%=info.getInfoValue()%>">
	    	 			Edit
	    	 		</a>
	    	 	</td>
	    	 	<td>
	    	 		<a href="SysInfo.jsp?opcode=delete&key=<%=info.getInfoKey()%>&value=<%=info.getInfoValue()%>">
	    	 			Delete
	    	 		</a>
	    	 	</td>
	    	 	<td>
	    	 		<a href="Delete.do?opcode=delete&key=<%=info.getInfoKey()%>&value=<%=info.getInfoValue()%>">
	    	 			Delete
	    	 		</a>
	    	 	</td>
	    	 </tr>
	    	 
	    <%
    	 }//for end
    	%>
    </table>
  </body>
</html>
