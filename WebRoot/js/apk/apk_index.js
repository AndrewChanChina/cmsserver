
$(document).ready(function() {
	// 上传apk
	$("#up_apk").bind("change",function(){    	
    	
    	$.ajaxFileUpload({
        url: "apk_upload.do",
        secureuri: false,
        fileElementId: "up_apk",
        dataType: "text",
        success: function(data ,status){
        	var p = $("#apk_name");
            p.val(data);
            $("#apk-pick-tip").append("上传 " + data + " 成功");
            //$("#photo-list").append("<li class='clearfix'><img src='./images/"+data+"'/></li>");
            
        },
        error: function(data,status,e){
            alert(e);
        }           
    	});
    });
	
	
	$("input[name='install']").click(function(){
		var src = $("input[name='install']:checked").val();
		if(src == "had_install"){
			$("#btn_install").hide();
			$("#btn_uninstall").show();
		}else{
			$("#btn_install").show();
			$("#btn_uninstall").hide();
		}		
	});
	
	// 全选 取消全选
	$("input[name='all_sel']").click(function(){	
		$("input[name='all_unsel']").attr('checked',false);
		$("input[name='sel']").each(function(){
			$(this).attr('checked',true);
		});
	});
	
	$("input[name='all_unsel']").click(function(){
		$("input[name='all_sel']").attr('checked',false);
		$("input[name='sel']").each(function(){
			$(this).attr('checked',false);
		});
	});
	
	// 跳转类型页面
	$("input[name='install']").click(function(){	
		var install = $("input[name='install']:checked").val();
		if(install== 'had_install'){
			window.location.href="apk.do?type=install";
		}else if(install== 'not_install'){
			window.location.href="apk.do?type=uninstall";
		}
	});
	
	
	
});
	
function check_add_apk_form(){
	
}

function check_install_sel(){
	
}