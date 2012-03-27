$(document).ready(function() {

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
		
});
	
function listenOKbtn(){
	$("#sel_rings_ok").click(function(){
		
		var ringsId = $("input[name='rings_sel']:checked").val();
		if(ringsId == null){
			alert("请选择一个资源");	
			return;				
		}
		$("#floatBox .title span").click();
		$('input[name="server_music"]').attr('value',ringsId);
	});
	$("#sel_rings_cancel").click(function(){		
		$('input:radio:first').attr('checked','true');
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
