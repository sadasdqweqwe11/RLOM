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
		<title>增加SKU</title>
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
		<form id="form" action="<%=path %>/login" method="post">
			<table width="752" height="600" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="90" colspan="5" align="left" valign="middle">
						<span class="STYLE7">邮箱</span>
						<label>
							<input type="text" name="email" class="left"/>
							&nbsp;
						</label>
						<label>
							<span class="STYLE7">密码</span>
							<input type="password" name="password" class="left"/>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="Submit" value="登录" class="left"/>
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
