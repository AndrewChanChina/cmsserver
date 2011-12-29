<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testcharts.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/highcharts.js"></script>
  </head>
  <script type="text/javascript">
  		var chart;
  		$(function(){
  			chart = new Highcharts.Chart({
  				chart : {
  					renderTo: 'container'
  				},
  				series: [{
  					data: [29,75,32,65,81]
  					//data: []
  				}]
  			}); 
  		});
  /*
  title: 最顶端的标题；
  subtitle：最顶端子标题；
  defaultSeriesType:图表类别：可取值有：line,spline,area,areaspline,bar,column等
  xAxis:  X轴，数据以数组形式组装；
  yAxis: Y轴，数据以数组形式组装；
  tooltip：提示信息；
  series：ajax获得数据放到数据里面；
  
  event：{load： getFrom //调用的js方法}
  //与后台交互数据
 
  */
   function getFrom(){
  		//$.get("http://localhost:8080/Treetest/jsontest.action",null,function(data){
  		//	var d = eval("("+data+")").jsonArray;
  		//	chart.series[0].setData(d);
  		//});
  		$.get("testCharts.do",null,function(data){
  			var d = eval(data);
  			chart.series[0].setData(d);
  		});
  }
  $(document).ready(function(){
  		window.setInterval(getFrom, 5*1000);
  });
  </script>
  <body>
     <div id="container"></div>
  </body>
</html>
