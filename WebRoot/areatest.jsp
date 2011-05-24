<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib uri= "/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri= "/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri= "/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>multi file upload test</title>
<script src="./js/fckeditor.js" type="text/javascript"></script>
<script src="./js/WdatePicker.js" type="text/javascript"></script>
<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/pony.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
<script src="./js/dtree.js" type="text/javascript"></script>
<script type="text/javascript"> 
function getFieldSelection(id) 
{ 
var select_field = document.getElementById(id); 
     word=''; 
    if (document.selection) { 
         var sel = document.selection.createRange(); 
        if (sel.text.length > 0) { 
             word = sel.text; 
         } 
     }    /*ie浏览器*/ 
    else if (select_field.selectionStart || select_field.selectionStart == '0') { 
         var startP = select_field.selectionStart; 
         var endP = select_field.selectionEnd; 
        if (startP != endP) { 
             word = select_field.value.substring(startP, endP); 
         } 
     }   /*标准浏览器*/ 
    return word; 
} 
//alert(getFieldSelection('txtarea'))
	function getText(){
	document.getElementById("text").value=getFieldSelection("txtarea");
	}
</script> 
</head>
<body>
<script type="text/javascript">
        <!--

        d = new dTree('d');
        d.add('n',-1,'系统管理');
        d.add(1,'n','用户管理','#');
        d.add('a',1,'用户管理','','right_body');
        d.add('a1',1,'用户增加','','right_body');
        d.add('a2',1,'用户删除','','right_body');
        d.add('a3',1,'用户修改','','right_body');
        d.add('a3',1,'','','right_body');

        d.add(4,'n','客户管理','#');    
        d.add("c",4,'客户资料',"../Business Management/UserInfo.html","客户资料","right_body");
        d.add("c1",4,'个人收费',"../Business Management/PersonPay.html","个人收费","right_body");
        d.add("c2",4,'批量收费',"../Business Management/BatchPay.html","批量收费","right_body");
        d.add("c3",4,'用户黑名单',"../Business Management/Users_Debts_Query.html","用户黑名单","right_body");
        d.add("c4",4,'到期用户',"../Business Management/User_Query1.html","用户信息查询","right_body");
        d.add("c5",4,'上门收费录入',"../Business Management/Onsitecharges.html","模拟上门收费录入","right_body");
        d.add("c7",4,'批量档案录入',"../Business Management/Users_Batchfiles.html","模拟批量档案录入","right_body");
        d.add("c9",4,'用户批量修改',"../Business Management/User_Update.html","模拟用户批量修改","right_body");
        d.add("c10",4,'柜台收费清单',"../Business Management/PayList.html","柜台收费清单","right_body");
        document.write(d);

        //-->
    </script>


</body>
</html:html>