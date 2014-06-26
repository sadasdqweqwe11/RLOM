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
<!-- saved from url=(0014)about:internet -->
<title>锐蓝标签生成确认录入</title>
	<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"> 
        $(function (){  
 			$("#logistics").click(function(){  
                   $("#logistics option").each(function(i,o){  
                   
                      /*   if($(this).attr("selected"))  
                        {  
                           ajaxSlOneList($(this).attr("value"),1);                      
                        } */  
                    });  
                });  
         });
                

</script>

		<style type="text/css">
<!--
.true {
	font-family: "楷体";
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}

.false {
	font-family: "楷体";
	background-color: #ffff33;
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}
-->
</style>
</head>

<body>
<a href="<%=path%>/main.action" style="color:#0000CC;">返回主页</a>||<a href="<%=path%>/listUploadFiles" style="color:#0000CC;">我的订单</a>
	<form id="form" method="post">
		<span class="STYLE7">${orderFile.filename} 上传时间
			${orderFile.postdatetime}</span> <input name="fid" type="text"
			value="${orderFile.id }" style="visibility: hidden;" />
		<c:if test="${flag == false}">
		<input type="submit" value="数据错误，请重新上传"
			onclick="document.getElementById('form').action='<%=path%>/deleteExcel';document.getElementById('form').submit();" />
		</c:if>
		
		<c:if test="${flag == true}">
			<input type="submit" value="确认上传"
				onclick="document.getElementById('form').action='<%=path%>/storeTrackingnoExcel';document.getElementById('form').submit();" />
		</c:if>
		<table width="1000" border="1" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="true">
				<td  class="true" valign="middle"><span
					class="STYLE7"></span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">SKU NO</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">VENDOR</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">AREA</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">WEIGHT</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">PRICE</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">NAME</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">PINMING</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">BAT</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">PRO</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">LIQ</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">OTHER</span>
				</td>
			</tr>
			<c:forEach items="${stringList}" var="list" varStatus="sum">
				<tr>
					<td   class="${checkList[sum.index][0]}">${sum.index+1}</td>
					<td   class="${checkList[sum.index][1]}">${list[0]}</td>
					<td   class="${checkList[sum.index][2]}">${list[1]}</td>
					<td   class="${checkList[sum.index][3]}">${list[2]}</td>
					<td   class="${checkList[sum.index][4]}">${list[3]}</td>
					<td   class="${checkList[sum.index][5]}">${list[4]}</td>
					<td   class="${checkList[sum.index][6]}">${list[5]}</td>
					<td   class="${checkList[sum.index][7]}">${list[6]}</td>
					<td   class="${checkList[sum.index][8]}">${list[7]}</td>
					<td   class="${checkList[sum.index][9]}">${list[8]}</td>
					<td   class="${checkList[sum.index][10]}">${list[9]}</td>
				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>
