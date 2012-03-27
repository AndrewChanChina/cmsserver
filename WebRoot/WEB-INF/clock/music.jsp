<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="smitpage" uri="/WEB-INF/smitpagetag.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=gb2312' />
		<title>ox travel</title>
		<!--meta name="viewport"  content="initial-scale=0.5; maximum-scale=10;  minimum-scale=0.5;"/-->
		<meta name="viewport"
			content="target-densitydpi=high-dpi;maximum-scale=10;  minimum-scale=0.5;" />
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/apple.css">
		<link type="text/css" rel="stylesheet" media="screen"
			href="./css/text.css">
		<script type="text/javascript" src="./js/jquery.js"></script>
		<script type="text/javascript" src="./js/ajaxfileupload.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
						
			$("#up-photo").bind("change",function(){
			
        	var p = $("#photo-names");
        	p.val(p.val()+$("#up-photo").val()+";");
        	$.ajaxFileUpload({
            url: "uploadMusicFile.do",
            secureuri: false,
            fileElementId: "up-photo",
            dataType: "text",
            success: function(data ,status){
                //alert(data);
                p.val(data);
                $("#photo-pick-tip").append("上传 " + data + " 成功");
                //$("#photo-list").append("<li class='clearfix'><img src='./images/"+data+"'/></li>");
                
            },
            error: function(data,status,e){
                alert(e);
            }           
        	});
        });
		});
		function checkAndUploadFile(){
			var name = $("#f_name").val();
			if( name == null || name == ""){
				alert("名字不能为空");
				return false;
			}
			var ring = $("#photo-names").val();
			if(ring  == "" || ring == null){
				alert("请选择一个声音文件");
				return false;
			}
			
			return true;						
		}
	</script>

	</head>
	<body>
		<div id="head_bg">
			<div class='container'>
				<div id='header'>
					酒店叫醒系统
				</div>
			</div>
		</div>
		<div class='container'>
			
			<div id='main'>

				<div id="addclock">
					<div>
						<h3 id='showaddclock'>
							添加铃声资源
						</h3>
						<form action='ring_add.do' method='post' id="add_ring_form"
							onsubmit="return checkAndUploadFile()">
							<div>
								名称：
								<input name='name' id="f_name"></input>
							</div>
							<div id="photo-pick-holder" class="post-section">
								<div id="photo-flash-holder">
									<input type="file" name="file" id="up-photo" />
									<input type="hidden" name="photos" id="photo-names">
								</div>
								<div id="photo-pick-tip"></div>
								<div id="pb-photo-upload-file-status"></div>
								<div id="photo-file-tip">
									mp3、ogg、wav等音频文件
								</div>
								<div id="pb-photo-upload-total-status"></div>
							</div>
							<input type='submit'>
						</form>
					</div>
				</div>
				<br>
				<div class='dotline'></div>
				<div id="show_clock_list">	

					<h3>
						所有铃声资源
					</h3>
					<div class='hide_class'>
						全部选择:
						<input type='checkbox'></input>
					</div>
					<table class="pn-ltable" style="" width="100%" cellspacing="1"
						cellpadding="0" border="0">
						<thead class="pn-lthead">
							<tr>
								<th width="20">

								</th>
								<th>
									名称
								</th>
								<th>
									文件名
								</th>
								<!-- th>
									时间长度
								</th>
								<th>
									服务器路径
								</th-->
								<th>
									操作
								</th>
							</tr>
						</thead>

						<tbody class="pn-ltbody">
							<c:forEach items="${listRings}" var="ring">
								<tr>
									<td>
										<input type='checkbox' name='id' value='${ring.id}' />
									</td>
									<td>
										${ring.name}
									</td>
									<td>
										${ring.fileName}
									</td>	
									<td align="center">										
										<a href="ring_delete.do?id=${ring.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
				    </table>

				</div>
			</div>
		</div>
		<div id="footer_bg">
			<div class='container'>
				<div
					style="text-align: center; vertical-align: middle; line-height: 50px; margin-bottom: 10px;">
					版权所有
				</div>
			</div>
		</div>
	</body>
</html>