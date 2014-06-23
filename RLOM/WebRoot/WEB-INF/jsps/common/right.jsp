<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="./common.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="right" class="clearfix">
	<c:if test="${null==user}">
			<div id="login">
		<form action="<%=path %>/login" method="post" id="u_login_form">
				<a href="<%=path %>/register"><img src="<%=path %>/images/login.png" style="margin-bottom:10px"></a>
				<div style="width:219px; margin-left:-40px">
				<ul>
				    <li><input type="text" id="u_login_id" name="email" onkeyup="oku(this)" flag="1" onblur="ob(this)" onfocus="of(this)" value="邮箱" style="width:150px; height:20px; margin-bottom:10px;color:#999"/></li>
					<li><input type="text" name="password" value="请输入密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password'}" onblur="if(!value) {value=defaultValue; this.type='text';}" style="color:#999; height:20px;" /> </li>

				</ul>
				</div>
				<!-- 这里是为了防止单个checkbox提交导致的选中状态无法取消的问题 start-->
				<input type="checkbox" id="u_login_cookie" name="autoLogin"
					value="1" checked="checked" style="visibility:hidden;margin-right:-20px"/>
				<!-- 这里是为了防止单个checkbox提交导致的选中状态无法取消的问题 end -->
				<input type="checkbox" id="u_login_cookie"  name="autoLogin" value="2"/>
					<label for="c_auto" style="font-size:12px; color:#666">下次自动登录</label>
				  <a href="<%=path%>/enterFindPasswd" class="intro">忘记密码</a>
					<input id="submit" name="submit" type="image"src="<%=path %>/images/loginxiaoqu.png" style="margin-top:10px"/>

				</form>
			</div>
	</c:if>

	<c:if test="${null!=user}">
		<div id="user-info">
			<div>
				<dl class="userinfodl">
					<dt class="userinfodt">
						<a title="" href="<%=path%>/user/infosetting" target="_blank"><img
								class="avator" width="50" height="50" alt="${user.nickname }"
								src="<%=path%>/${user.userField.avatar }"
								style="border-radius: 4px 4px 4px 4px; padding: 2px"> </a>
					</dt>
					<dd class="userinfodd">
						<a action-type="usercard" action-data="fuid=2723187233" title="${user.nickname }"
							href="<%=path%>/user/infosetting" target="_blank" class="intro">${user.nickname }</a>
						</br>
						<a href="<%=path%>/user/infosetting" class="hpmember">${user.userField.signature }</a>
					</dd>
				</dl>
			</div>
			<ul class="userinfoul">
				<li class="clearfix">
					<img src="<%=path%>/images/mailboxlogo.png">
					<a href="<%=path%>/user/mail" class="intro">站内信</a>
				</li>
				<li>
					<img src="<%=path%>/images/atlogo.png">
					<a href="<%=path%>/user/atMy" class="intro">@我的文章</a>
				</li>
				<li>
					<img src="<%=path%>/images/replylogo.png">
					<a href="<%=path%>/user/replyMyArticle" class="intro">
						回复我的文章</a>
				</li>
				<li class="clearfix">
					<img src="<%=path%>/images/setting.png">
					<a href="<%=path%>/user/infosetting" class="intro">个人信息设置</a>
				</li>
				<li>
					<img src="<%=path%>/images/exitlogo.png">
					<a href="<%=path%>/logout" class="intro">退出登录</a>
				</li>
			</ul>
		</div>
	</c:if>
	<div id="bulletin">
		<a href="<%=path%>/board/notice" class="bulltitle"><img src="<%=path%>/images/bulletinlogo.png" style="height:14px; width:14px; margin-right:5px" />公告栏</a>
		<div class="bullcontent"><a  href="<%=path%>/article/490" class="intro">山大校区网开始招募实习生了，敬请关注我们的主页</a><a href="" style=" color:#FF6600; text-decoration:none">www.uxiaoqu.com</a></div>
	</div>
	<div id="activity">
		<a href="<%=path%>/letterToYou" class="actitle" ><img src="<%=path%>/images/activitylogo.png" style="height:14px; width:14px; margin-right:5px" />活动·线上/下</a>
		<div class="accontent"><a href="<%=path%>/skipBoardRegister"><img src="<%=path%>/images/bankuaizhengji.png" style="width:199px; margin-left:-5px"></a></div>
	</div>
	<div id="advertisement">
		<div style="margin-bottom:15px"><a href="http://www.lenovo.com.cn/paike/?intcmp=H221" target="blank"><img src="<%=path%>/images/ad-thinkpad.png" style="width:200px"></a></div>
	</div>
</div>