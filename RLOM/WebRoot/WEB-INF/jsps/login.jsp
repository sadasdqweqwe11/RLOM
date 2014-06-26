<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>锐蓝订单处理系统-登录</title>

	<!--- CSS --->
	<link rel="stylesheet" href="<%=path %>/login/css/style.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.8.3.min.js"></script>
	<!--[if (gte IE 6)&(lte IE 8)]>
		<script type="text/javascript" src="<%=path %>/login/js/selectivizr.js"></script>
		<noscript><link rel="stylesheet" href="<%=path %>/login/css/fallback.css" /></noscript>
	<![endif]-->
<script>
	$(function(){
		$(".click").focus(function(){
		var value = $(this).val();
			if($.trim(value)=="请输入邮箱"){
				$(this).val("");
			}
			if($.trim(value)=="*****"){
				$(this).val("");
			}
		});
		
		$("#email").focus();
	});

</script>


	</head>

	<body>
		<div id="container">
			<form id="form" action="<%=path %>/login.action" method="post">
				<div class="login">用户登录</div>
				<div class="username-text">邮箱:</div>
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input id="email" class="click" type="text" name="email" value="请输入邮箱" />
				</div>
				<div class="password-field">
					<input class="click" type="password" name="password" value="*****" />
				</div>
				<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">下次自动登录<br/>（没做,点也没用）</label>
				<div class="forgot-usr-pwd">忘记 <a href="#">邮箱</a> 或 <a href="#">密码</a>?<br/>（一样，别浪费时间了）</div>
				<input type="submit" name="submit" value="登录" />
			</form>
		</div>
		<div id="footer">
			Copyright &copy; 2014.Company name All rights reserved. 锐蓝科技
		</div>
</body>
</html>