<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="./common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico"
	type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
	<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="<%=path%>/js/date/kalendae.css"
	type="text/css" charset="utf-8">
<script src="<%=path%>/js/date/kalendae.standalone.min.js"
	type="text/javascript" charset="utf-8"></script>
<style type="text/css" media="screen">
.kalendae span.closed {
	background: red;
}
</style>

<!-- saved from url=(0014)about:internet -->
<title>锐蓝标签查询</title>
<script type="text/javascript" charset="utf-8">

function showDate()
{
var today = new Date();
var day = today.getUTCDate();
if(day<10){
	day = "0" + day;
}
var month = today.getUTCMonth()+1;
if(month<10){
	month = "0" + month;
}
var year = today.getUTCFullYear();
var date = month + "/" + day + "/" + year;
document.getElementById("date").value = date;
}

	$(function(){
		showDate();
		$("#ajaxButton").click(function(){
			if($("#date").attr("value")==""){
				alert("请选择日期");
				return false;			
			}
			var selDate = $("#date").val();
			var fun = $("#first").val();
			var number = $("#area").val();
			$("#tableDiv").load('<%=path%>/ajax/orderTableAjax.action', {date: selDate ,func:fun, code :number });				
		});
		
		
		$("#second").hide(); //初始化的时候第二个下拉列表隐藏
		$("#first").change(function(){ //当第一个下拉列表变动内容时第二个下拉列表将会显示
		var parentId=$("#first").val();
		var dateValue = $("#date").val();
		if(null!= parentId && ""!=parentId){
		$.getJSON("<%=path%>/ajax/getSearchMap.action",{func:parentId,date:dateValue},function(myJSON){
		var options="";
		if(myJSON.length>0){
		//options+="<option value=''>==请选择类型==</option>";
		for(var i=0;i<myJSON.length;i++){
		options+="<option value=\""+myJSON[i].id+"\">"+myJSON[i].name+"</option>";
		}
		$("#area").html(options);
		$("#second").show();
		}
		else if(myJSON.length<=0){
		$("#second").hide();
		}
		});
		}
		else{
		$("#second").hide();
		}
		}); 
		
		
		
	});
</script>
</head>

<body>
<a href="<%=path%>/main.action" style="color:#0000CC;">返回主页</a>||<a href="<%=path%>/listUploadFiles" style="color:#0000CC;">我的订单</a>
	<div id="top" align="center">

		请选择日期: <input type="text" value="" id="date">
		<script type="text/javascript" charset="utf-8">
			new Kalendae.Input('date', {
				months : 2,
				mode : 'range',
			});
		</script>
		<%--<select id="first" name="func">
				<option value="">选择查询条件</option>
				<option value="">按物流方式查询</option>
				<option value="">按物流帐号查询</option>
				<option value="">按集货中心查询</option>
				<option value="">按供应商查询</option>
		</select> 
		<select id="second" name="number">
			<c:forEach items="${logisticss}" var="logistics">
				<option value="${logistics.id}">${logistics.name}</option>
			</c:forEach>

		<select id="second" name="code">
			<c:forEach items="${maps}" var="map">
				<option value="${map.key}">${map.value}</option>
			</c:forEach>
		</select>
		 
		--%>
		
		<select id="first" name="func">
				<option value="-1">按日期查询</option>
				<option value="1">按物流方式查询</option>
				<option value="2">按物流帐号查询</option>
				<option value="3">按集货中心查询</option>
				<option value="4">按供货商查询</option>
				<option value="5">按帐号查询</option>
		</select> 
		
		<span id="second">
		<select id="area" name="code"><%--
			<c:forEach items="${maps}" var="map">
				<option value="${map.key}">${map.value}</option>
			</c:forEach>
		--%></select>
				</span> 
		
		<input id="ajaxButton" type="button" value="查询" />
	</div>		
	<div id="tableDiv"></div>
</body>
</html>
