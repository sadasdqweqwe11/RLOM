<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link rel="shortcut icon" href="<%=path %>/images/favicon.ico" type="image/x-icon" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<link href="<%=path %>/css/meiyoubbs3.css" rel="stylesheet" type="text/css" />
		<!-- saved from url=(0014)about:internet -->
		<title>锐蓝标签生成--下载</title>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
<!--
body {
	margin-left: 200px;
	margin-top: 0px;
	margin-right: 200px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}

.STYLE3 {
	font-family: "楷体";
	color: #FF9933;
}

.STYLE4 {
	font-family: "楷体";
	color: #FF9933;
	font-size: 24px;
}

.STYLE5 {
	font-size: 14px;
	font-weight: bold;
}

.STYLE6 {
	font-size: 24px;
	font-weight: bold;
	color: #FFFF60;
	font-family: "Blackadder ITC";
}

.STYLE7 {
	font-family: "幼圆"
}

.STYLE8 {
	font-size: 18px
}

.STYLE11 {
	font-size: 12px
}

.STYLE13 {
	font-size: 12px;
	font-family: "Times New Roman", Times, serif;
}
-->
</style>
	</head>

	<body>
		<a href="<%=path%>${pdfurl}" style="color:#0000CC;" target="_black" >订单pdf下载</a>
		<a href="<%=path%>${fenurl}" style="color:#0000CC;" target="_black" >燕文excel下载</a>
	</body>
</html>
