<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page import="com.smit.web.form.GroupForm"%>
<%@ page import="java.util.*"%>
<%@ page import="com.smit.vo.Purview"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="tiles" %>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="./css/admin.css" rel="stylesheet" type="text/css" />
	<link href="./css/theme.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
	<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<logic:empty name='groupForm' property='id'>
		<div> 
			&quot;创建分组： 
		</div>
	</logic:empty>
	<logic:notEmpty name='groupForm' property='id'>
		<div>
			修改分组：
		</div>
	</logic:notEmpty>
	
	<form method="post" action="saveupdategroup.do" id="jvForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>分组名:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">
						<input type="text" maxlength="100" name="groupName"
							vld="{required:true,username:true,remote:'v_check_username.do',messages:{remote:'用户名已被使用'}}"
							maxlength="100" value="${groupForm.groupName}"/>
					</td>					
				</tr>
				<tr>
					<td width="12%" class="pn-flabel pn-flabel-h">
						描述:
					</td>
					<td colspan="1" width="38%" class="pn-fcontent">					
						<%							
							List purviewlist = (List)request.getAttribute("purviewlist");
							GroupForm g = (GroupForm)request.getAttribute("groupForm");
							String[] purviewids = g.getPurviewids();
							
							for(Object item : purviewlist){
								Purview p = (Purview)item;
								StringBuilder sb = new StringBuilder();
								
								sb.append("<input type='checkbox' name='purviewids'	value ='" + 
										p.getId() + "' ");
								int len = 0;
								if(purviewids!=null)
								 len = purviewids.length;
								for(int i=0; i< len; i++){
									if(purviewids[i].equals(String.valueOf(p.getId())))
									{
										sb.append("checked ");
										break;
									}
								}
								
								sb.append(" />" + p.getPurviewName());
								out.print(sb.toString());
							}
							
						 %> 					
						
					</td>
				</tr>				
				<tr>
					<td>&nbsp;
					<td colspan="4" class="pn-fbutton">
						<input type="submit" value="提交" />
						&nbsp;
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="hideId"
						value="${groupForm.hideId}"/>
			<input type="hidden" name="id"
						value="${groupForm.id}"/>
		</form>
</body>
</html:html>