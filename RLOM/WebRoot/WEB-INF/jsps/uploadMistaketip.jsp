 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="./common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="<%=path %>/images/favicon.ico" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>错误提示</title>

<style>

.mistake-bottom{height:30px; width:600px; border-top:1px solid #cccccc;color:#999999; font-size:12px; padding-top:5px; text-align:left}
.mistake{ position:absolute; margin-top:190px; margin-left:75px;width:600px; height:300px; border-top:2px dashed #0066CC}
.mistake-content{width:600px; height:270px; color:#666666; font-size:14px; text-align:left; line-height:30px}
</style>
</head>

<body bgcolor="#A0DCF5">
<center>
<div style="width:750px; height:563px; background-image:url(<%=path%>/images/mistake.png)">
	<div class="mistake">
		<div class="mistake-content">
			<ul >
				<li style="list-style:outside">上传文件错误，请检查,错误原因可能为</li>
				<li style="list-style:outside">1 文件格式错误，请另存为excel</li>
				<li style="list-style:outside">2 文件内部错误，请检查</li>
			</ul>
		</div>
		
		<div class="mistake-bottom"><span>提示：以上错误可能属于系统错误，您可以继续其他操作，<a href="javascript:void(0);" style="color:#0000CC; text-decoration:none" onclick="history.back(-1)" >点击这里</a>返回上一步操作！</span>
		</div>
	</div>
</div>
</center>
</body>
</html>