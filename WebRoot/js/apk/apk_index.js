
$(document).ready(function() {
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
	
	
});
	