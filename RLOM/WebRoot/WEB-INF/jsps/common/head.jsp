<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
oku = function(obj){
    obj.setAttribute('flag',0);
}

ob = function(obj){
    if(obj.getAttribute('flag')=="1")
        obj.value='邮箱 ';
}

of = function(obj){
    if(obj.getAttribute('flag')=="1")
        obj.value='';
}
</script>
		


<div class="global-header">
	<div style="width:960px; margin:0 auto; position:relative">
		<div style="width:180px;">
			<a href="<%=path%>/main" target="_top" title="返回首页" ><div class="logo"><img src="<%=path%>/images/beta.png"  style="margin-left:125px"/></div></a>
		</div>
		<ul class="menu clearfix">
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section1.sid}" class="head"><div class="headli">${section1.name}</div></a>
				<div class="item">
				<c:forEach items="${section1.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
					<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section1.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
					</c:if>
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section2.sid}" class="head"><div class="headli">${section2.name}</div></a>
				<div class="item">
				<c:forEach items="${section2.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
				<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section2.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section3.sid}" class="head"><div class="headli">${section3.name}</div></a>
				<div class="item" >
				<c:forEach items="${section3.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
					<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section3.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
					</c:if>
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section4.sid}" class="head"><div class="headli">${section4.name}</div></a>
				<div class="item">
				<c:forEach items="${section4.boards}" var="board" varStatus="sum">
				<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
				</c:if>
				<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section4.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>				
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section5.sid}" class="head"><div class="headli">${section5.name}</div></a>
				<div class="item">
				<c:forEach items="${section5.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
					<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section5.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section6.sid}" class="head"><div class="headli">${section6.name}</div></a>
				<div class="item">
				<c:forEach items="${section6.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
									<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section6.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			<!-- 
			 <li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section7.sid}" class="head"><div class="headli">${section7.name}</div></a>
				<div class="item">
				<c:forEach items="${section7.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
					<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section7.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			 -->
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section8.sid}" class="head"><div class="headli">${section8.name}</div></a>
				<div class="item">
				<c:forEach items="${section8.boards}" var="board" varStatus="sum">
				<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
				</c:if>
				<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section8.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown'" onmouseout="this.className='tuckUp'"><a href="<%=path %>/section/${section9.sid}" class="head"><div class="headli">${section9.name}</div></a>
				<div class="item">
				<c:forEach items="${section9.boards}" var="board" varStatus="sum">
					<c:if test="${sum.count<11}"> 
					<a href="<%=path%>${board.url}" class="hpmember"><img height="16px" width="16px" src="<%=path%>/${board.avatar}" style="margin-right:8px; ">${board.name }</a><br/>
					</c:if>
				<c:if test="${sum.count==11}"> 
					 <a href="<%=path %>/section/${section9.sid}" style="color:#009900; text-decoration:underline; margin-left:70px">更多</a>
				</c:if>
				</c:forEach>      
				</div>
			</li>
			<c:if test="${null!=user}">
			<li style="margin-left:200px; width:60px" class="overflowproof"><span class="head">${user.nickname }</span></li>
			<li class="tuckUp" onmouseover="this.className='pullDown';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display='none'</c:if>" onmouseout="this.className='tuckUp';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display=''</c:if>"><a href="<%=path %>" class="head"><div class="headli">消息</div></a>
				<div class="itemmessage">
				<c:if test="${user.newpmcount > 0}">
					<a href="<%=path%>/user/replyMyArticle" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/replylogo.png" style="margin-right:8px; ">${user.newpmcount}条新回复</a><br/>
				</c:if>
				<c:if test="${user.newpmcount == 0}">
					<a href="<%=path%>/user/replyMyArticle" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/replylogo.png" style="margin-right:8px; ">查看回复</a><br/>
				</c:if>
				<c:if test="${user.newpm > 0}">
					<a href="<%=path%>/user/atMy" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/atlogo.png" style="margin-right:8px; ">${user.newpm}条新@提到我</a><br/>
				</c:if>
				<c:if test="${user.newpm == 0}">
					<a href="<%=path%>/user/atMy" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/atlogo.png" style="margin-right:8px; ">查看@我</a><br/>
				</c:if>
					<a href="<%=path%>/user/mail" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/mailboxlogo.png"style="margin-right:8px; ">查看私信</a><br/>
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display='none'</c:if>" onmouseout="this.className='tuckUp';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display=''</c:if>"><a href="<%=path%>/user/infosetting" class="head"><div class="headli">帐号</div></a>
				<div class="item">
					<a href="<%=path%>/user/infosetting" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/basicinfologo.png" style="margin-right:8px; ">基本资料设置</a><br/>
					<a href="<%=path%>/user/infosetting" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/avatarlogo.png" style="margin-right:8px; ">头像设置</a><br/>
					<a href="<%=path%>/user/infosetting" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/passwordinfologo.png" style="margin-right:8px; ">密码设置</a><br/>
					<a href="<%=path%>/logout" class="hpmember"><img height="16px" width="16px" src="<%=path %>/images/exitlogo.png" style="margin-right:8px; ">退出登录</a>
				</div>
			</li>
			<li class="tuckUp" onmouseover="this.className='pullDown';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display='none'</c:if>" onmouseout="this.className='tuckUp';<c:if test="${user.newpmcount > 0|| user.newpm > 0}">document.getElementById('tip-message').style.display=''</c:if>"><a href="<%=path%>/admin/adminLogin" class="head"><div class="headli">admin</div></a>
			</li>
			</c:if>
			<c:if test="${null==user}">
			<li style="margin-left:300px; width:40px" class="overflowproof"><a class="head"  href="#none" onclick="testMessageBox(event);"><div class="headli">登录</div></a></li>
			<li><a href="<%=path %>/register" class="head"><div class="headli">注册</div></a></li>
			<script>
			var isIe=(document.all)?true:false;
			//设置select的可见状态
			function setSelectState(state)
			{
			var objl=document.getElementsByTagName('select');
			for(var i=0;i<objl.length;i++)
			{
			objl[i].style.visibility=state;
			}
			}
			function mousePosition(ev)
			{
			if(ev.pageX || ev.pageY)
			{
			return {x:ev.pageX, y:ev.pageY};
			}
			return {
			x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop
			};
			}
			//弹出方法
			function showMessageBox(wTitle,content,pos,wWidth)
			{
			closeWindow();
			var bWidth=parseInt(document.documentElement.scrollWidth);
			var bHeight=parseInt(document.documentElement.scrollHeight);
			if(isIe){
			setSelectState('hidden');}
			var back=document.createElement("div");
			back.id="back";
			var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
			styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
			back.style.cssText=styleStr;
			document.body.appendChild(back);
			showBackground(back,50);
			var mesW=document.createElement("div");
			mesW.id="mesWindow";
			mesW.className="mesWindow";
			mesW.innerHTML="<div class='mesWindowTop'></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'><table width='99%' height='100%'><tr><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭' class='close' value='关闭' /></td></tr></table></div>";
			styleStr="left:"+(((pos.x-wWidth)>0)?(pos.x-wWidth):pos.x)+"px;top:"+(pos.y)+"px;position:absolute;width:"+wWidth+"px;";
			mesW.style.cssText=styleStr;
			document.body.appendChild(mesW);
			}
			//让背景渐渐变暗
			function showBackground(obj,endInt)
			{
			if(isIe)
			{
			obj.filters.alpha.opacity+=1;
			if(obj.filters.alpha.opacity<endInt)
			{
			setTimeout(function(){showBackground(obj,endInt)},5);
			}
			}else{
			var al=parseFloat(obj.style.opacity);al+=0.01;
			obj.style.opacity=al;
			if(al<(endInt/100))
			{setTimeout(function(){showBackground(obj,endInt)},5);}
			}
			}
			//关闭窗口
			function closeWindow()
			{
			if(document.getElementById('back')!=null)
			{
			document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
			}
			if(document.getElementById('mesWindow')!=null)
			{
			document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
			}
			if(isIe){
			setSelectState('');}
			}
			//测试弹出
			function testMessageBox(ev)
			{
			var objPos = mousePosition(ev);
			messContent="<form action='<%=path %>/login' method='post' id='login_form'><div style='padding:20px;text-align:center;height:155px;width:2520px'><table cellpadding='0' cellpadding='0' bordercolor='#fff' bgcolor='#fff' width='250px'><tr class='tr1'><td colspan='2'><span class='font-tr1'>登录校区网</span></td></tr><tr height='40px'><td class='number'>邮&nbsp;&nbsp;箱:</td><td><input type='text' style='border:1px solid #ccc; border-radius:5px 5px 5px 5px; width:160px; height:23px' name='email'/></td></tr><tr height='40px'><td class='number'>密&nbsp;&nbsp;码:</td><td><input type='password' style='border:1px solid #ccc; border-radius:5px 5px 5px 5px; width:160px; height:23px' name='password'/></td></tr><tr height='40px'><td colspan='2'><a href='javascript:void(0)' onclick=\"document.getElementById('login_form').submit()\" class='hpcontent' style='margin-right:20px' />登录</a><a href='<%=path%>/enterFindPasswd' class='hpcontent'>忘记密码</a></td></tr></table></div></form>";
			showMessageBox('',messContent,objPos,250);
			}
			</script>
			</c:if>
			
		</ul>
		<c:if test="${user.newpmcount > 0|| user.newpm > 0}">
		<div id="tip-message">
			<a href=""  class="delete-tip"></a>
			<ul>
			<c:if test="${user.newpmcount > 0}">
			<li>${user.newpmcount}条新评论，<a href="<%=path%>/user/replyMyArticle" class="intro">查看评论</a></li>
			</c:if>
			<c:if test="${user.newpm > 0}">
			<li>${user.newpm}条新@提到我，<a href="<%=path%>/user/atMy" class="intro">查看@我</a></li>
			</c:if>
			</ul>
		</div>
		</c:if>
		
	</div>
</div>