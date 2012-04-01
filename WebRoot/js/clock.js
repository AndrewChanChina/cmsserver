$(document).ready(function() {

	window.setTimeout(function getstatus(){
		$.ajax({
			url : "clock_getStatus.do",
			error : function() {
				$("#floatBox .content").html("error...");
			},
			success : function(html) {
				if(html == 'false'){
					//alert("555");
					
					console.log("false");
					window.setTimeout(getstatus,5000);
				}else{
					
					console.log("true");
					//window.href="clock.do";
				
					var notify = new Notify(); 
					if (!notify.isSupport()) {  
					    alert("Your browser don't support webkitNotifications!!");  
					    return;  
					} 
					notify.show("", "您的操作数据有更新", "赶紧到你的页面F5吧！");
				  
			   }
			}
		});
		
	},
	5000);

	$("#showaddclock").click(function(){
		$("#add_clock_form").toggle("normal");
	});	
	
	$("input[name='music']").click(function(){
		var src = $("input[name='music']:checked").val();
		if(src == "defult"){
			$("#show_waiting").hide();
		}else{
			$("#show_waiting").show();
			$.ajax({url:"ring.do?opt=list", success:function(result){
				$("#show_waiting").hide();
				dialog("请选择资源文件","text:"+result,"800px","auto","classd");
				listenOKbtn();
			  }});
			
			/*$("#show_music_for_sel").load("ring.do?opt=list",function(){				
			});*/
		}		
	});
	
	$("#find_roomnum").click(function(){
		var roomNum = $('input[name="find_room_num"]').val();
		if(roomNum == null || roomNum == ""){
			alert("房间号输入不能为空");
			return false;
		}
		var url = "clock_find.do?roomNum=" + roomNum;
		$('#list_data').load(url);
	});
	
	$("#find_time").click(function(){
		var startTime = $('input[name="find_start_time"]').val();
		var endTime = $('input[name="find_end_time"]').val();
		if(startTime == null || startTime == ""){
			alert("开始时间输入不能为空");
			return false;
		}else if(isGoodTimeFormat(startTime) == false){
			alert("开始时间的格式不对");
			return false;
		}
			
		if(endTime == null || endTime == ""){
			alert("结束时间输入不能为空");
			return false;
		}else if(isGoodTimeFormat(endTime) == false){
			alert("结束时间的格式不对");
			return false;
		}
		
		var startTimeArray = startTime.split(":");
		var st = startTimeArray[0]*60 + parseInt(startTimeArray[1]);
		
		var endTimeArray = endTime.split(":");
		var et = endTimeArray[0]*60 + parseInt(endTimeArray[1]);
		if(st > et){
			alert("开始时间比结束时间大，不能查询");
			return false;
		}
		var url = "clock_find.do?startTime=" + st + "&endTime=" + et;
		$('#list_data').load(url);
	});
		
});
	
function isGoodTimeFormat(data){
	var dataArray = data.split(":");
	if(dataArray.length != 2){
		return false;
	}
	if(dataArray[0] < 0 || dataArray[0] > 23){
		return false;
	}
	if(dataArray[1] < 0 || dataArray[1] > 59){
		return false;
	}
	return true;
}
function listenOKbtn(){
	$("#sel_rings_ok").click(function(){
		
		var ringsId = $("input[name='rings_sel']:checked").val();
		if(ringsId == null){
			alert("请选择一个资源");	
			return;				
		}
		$("#floatBox .title span").click();
		$('input[name="server_music"]').attr('value',ringsId);
		$('input[name="server_music"]').val(ringsId);
		var name = $("tr td #" + ringsId).text();
		$('#show_music_for_sel').text("选择了："+ name);
	});
	$("#sel_rings_cancel").click(function(){		
		$('input[name="music"]:first').attr('checked','true');
		$("#floatBox .title span").click();
	});	
}
function checkAdd(){
	var name = $("#f_name").val();
	if( name == null || name == ""){
		alert("名字不能为空");
		return false;
	}
	return true;						
}

