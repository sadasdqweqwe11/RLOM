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
</head>

<body>
	<form id="form" method="post">
		<span class="STYLE7">${orderFile.filename} 上传时间
			${orderFile.postdatetime}</span> <input name="fid" type="text"
			value="${orderFile.id }" style="visibility: hidden;" />
		<c:if test="${orderFile.description == null}">
		<select id="logistics" name="logisticsid">  
            <c:forEach items="${logisticss}" var="logistics">
            <option value="${logistics.id}" >${logistics.name}</option>  
            </c:forEach>
        </select>  			
			<input type="submit" value="确认"
				onclick="document.getElementById('form').action='<%=path%>/generateExcel';document.getElementById('form').submit();" />
		</c:if>
		<input type="submit" value="删除"
			onclick="document.getElementById('form').action='<%=path%>/deleteExcel';document.getElementById('form').submit();" />

		<table width="752" height="600" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="90" align="left" valign="middle"><span
					class="STYLE7">RL ORDERNO</span>
				</td>
				<td height="90" align="left" valign="middle"><span
					class="STYLE7">ORDERNO</span>
				</td>
				<td height="90" align="left" valign="middle"><span
					class="STYLE7">VENDOR</span>
				</td>
				<td height="90" align="left" valign="middle"><span
					class="STYLE7">POSTAL CODE</span>
				</td>
			</tr>
			<c:forEach items="${rlOrders}" var="order" varStatus="sum">
				<tr>
					<td width="100px">${order.rlordernumber }</td>
					<td width="100px">${order.ordernumber}</td>
					<td width="100px">${order.vendor}</td>
					<td width="100px">${order.postalcode}</td>
				</tr>
			</c:forEach>

		</table>

	</form>

</body>
</html>
