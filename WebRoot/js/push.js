$(document).ready(function(){
	$("#pb-submit").click(function(){
		var linkurl = $("#pb-link-url-input").val();
		var to = $("#post-select").val();
		if(linkurl!= null && linkurl.length>50){
			alert("链接地址长度不能超过50!");
			return;
		}
		if(to==0){
			alert("请选择要发送的对象!");
			return;
		}
		$("#content-form").submit();
	});
	
	$("#pb-cancel").click(function(){
		$("form")[0].reset();
		history.back();
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
	
});
	
	
