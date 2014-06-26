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
				onclick="document.getElementById('form').action='<%=path%>/storeExcel';document.getElementById('form').submit();" />
		</c:if>
	
		<table width="1000" border="1" align="center"
			cellpadding="0" cellspacing="0">
				<c:if test="${fileType == 'xls'}">
			<tr class="true">
				<td  class="true" valign="middle"><span
					class="STYLE7"></span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ORI</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">VENDOR</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">SKU</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">QUA</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">NAME</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ADD1</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">CITY</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">POST</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">COUN</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">DATE</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">AMOU</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">GUO</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">MARK</span>
				</td>

				<td  class="true" valign="middle"><span
					class="STYLE7">ACCO</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">CURR</span>
				</td>
			</tr>

			<c:forEach items="${stringList}" var="list" varStatus="sum">
				<tr>
					<td   class="${checkList[sum.index][0]}">${sum.index+1}</td>
					<td   class="${checkList[sum.index][1]}">${list[1]}</td>
					<td   class="${checkList[sum.index][2]}">${list[2]}</td>
					<td   class="${checkList[sum.index][3]}">${list[3]}</td>
					<td   class="${checkList[sum.index][6]}">${list[6]}</td>
					<td   class="${checkList[sum.index][8]}">${list[8]}</td>
					<td   class="${checkList[sum.index][9]}">${list[9]}</td>
					<td   class="${checkList[sum.index][11]}">${list[11]}</td>
					<td   class="${checkList[sum.index][13]}">${list[13]}</td>
					<td   class="${checkList[sum.index][14]}">${list[14]}</td>
					<td   class="${checkList[sum.index][16]}">${list[16]}</td>
					<td   class="${checkList[sum.index][17]}">${list[17]}</td>
					<td   class="${checkList[sum.index][19]}">${list[19]}</td>
					<td   class="${checkList[sum.index][20]}">${list[20]}</td>
					<td   class="${checkList[sum.index][21]}">${list[21]}</td>
					<td   class="${checkList[sum.index][22]}">${list[22]}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${fileType == 'txt'}">
					<tr class="true">
				<td  class="true" valign="middle"><span
					class="STYLE7"></span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ORI</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">PHONE</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">SKU</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">QUA</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">NAME</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ADD1</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ADD2</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">CITY</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">STATE</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">POST</span>
				</td>

				<td  class="true" valign="middle"><span
					class="STYLE7">COUN</span>
				</td>
			</tr>
			<c:forEach items="${stringList}" var="list" varStatus="sum">
				<tr>
					<td   class="${checkList[sum.index][0]}">${sum.index+1}</td>
					<td   class="${checkList[sum.index][0]}">${list[0]}</td>
					<td   class="${checkList[sum.index][9]}">${list[9]}</td>
					<td   class="${checkList[sum.index][10]}">${list[10]}</td>
					<td   class="${checkList[sum.index][12]}">${list[12]}</td>
					<td   class="${checkList[sum.index][16]}">${list[16]}</td>
					<td   class="${checkList[sum.index][17]}">${list[17]}</td>
					<td   class="${checkList[sum.index][18]}">${list[18]}</td>
					<td   class="${checkList[sum.index][20]}">${list[20]}</td>
					<td   class="${checkList[sum.index][21]}">${list[21]}</td>
					<td   class="${checkList[sum.index][22]}">${list[22]}</td>
					<td   class="${checkList[sum.index][23]}">${list[23]}</td>
				</tr>
			</c:forEach>
		</c:if>
		</table>

	</form>


		<div style="display:none">
		<div id="inline_content" style="padding:10px; background:#fff;">
    	<p>
				<strong>
					This content comes from a hidden element on this page.
				</strong>
				</p>
				<p>
					The inline option preserves bound JavaScript events and changes, and it puts the content back where it came from when it is closed.
				</p>
				<p>
					<a id="click" href="#" style="padding:5px; background:#ccc;">
						Click me, it will be preserved!
					</a>
				</p>
				
				<p>
					<strong>
						If you try to open a new ColorBox while it is already open, it will update itself with the new content.
					</strong>
				</p>
				<p>
					Updating Content Example:
					<br>
					<a class="ajax cboxElement" href="http://static.oschina.net/uploads/space/2012/0607/142131_2PqS_28.jpg">
						Click here to load new content
					</a>
				</p>
    	</div>
	</div>
</body>
</html>
