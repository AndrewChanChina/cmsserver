$(document).ready(function(){
	$("#pb-submit").click(function(){
		alert("hello");
		$("#content-form").submit();
	});
	
	$("#up-photo").bind("change",function(){
		var p = $("#photo-names");
		p.val(p.val()+$("#up-photo").val()+";");
		$.ajaxFileUpload({
			url: "push.do?op=uploadPhoto&fresh="+Math.random(),
			secureuri: false,
			fileElementId: "up-photo",
			dataType: "text",
			success: function(data ,status){
				//alert(data);
				$("#photo-list").append("<li class='clearfix'><img src='./images/"+data+"'/></li>");
				
			},
			error: function(data,status,e){
				alert(e);
			}
			
		});
		});
	
	$("#nextpage").click(function(){
		$.get("push.do?op=content",function(data){
			
			
		},"json");
	});
});
	
	
