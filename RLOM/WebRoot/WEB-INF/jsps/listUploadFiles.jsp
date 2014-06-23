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
		<link rel="shortcut icon" href="<%=path %>/images/favicon.ico" type="image/x-icon" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Keywords" content="" />
		<meta name="Description" content="" />
		<link href="<%=path %>/css/meiyoubbs3.css" rel="stylesheet" type="text/css" />
		<!-- saved from url=(0014)about:internet -->
		<title>锐蓝标签生成--登录</title>
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
		<form id="form" action="checkExcelAction" method="post">
			<table width="752" height="600" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="90" align="left" valign="middle">
						<span class="STYLE7">文件名</span>
					</td>
					<td height="90" align="left" valign="middle">
							<span class="STYLE7">上传时间</span>
					</td>
					<td height="90" align="left" valign="middle">
							<span class="STYLE7">下载</span>
					</td>
				</tr>
				<c:forEach items="${files}" var="file" varStatus="sum">
				<tr>
					<%-- <td width="30px"><input type="checkbox" name="listCheck" value="${file.id}"/></td> --%>
					<td width="300px"><a href="<%=path%>/orderFile/${file.id}" style="color:#0000CC;" >${file.originalfilename}</a></td>
					<td width="100px">${file.postdatetime}</td>
					<c:if test="${file.description != null}">
					<td width="100px"><a href="<%=path%>${file.description}">下载</a></td>
					</c:if>
					<c:if test="${file.description == null}">
					<td width="100px"><a href="<%=path%>/orderFile/${file.id}" style="color:#0000CC;" >未确认</a></td>
					</c:if>
				</tr>
			</c:forEach>
					<%-- <input type="submit" value="删除" onclick="document.getElementById('form').action='<%=path%>/deleteExcel';document.getElementById('form').submit();"/> --%>
			</table>
						<input type="submit" value="下载" onclick="document.getElementById('form').action='<%=path%>/downloadExcel';document.getElementById('form').submit();"/>
	
		</form>
	</body>
</html>
