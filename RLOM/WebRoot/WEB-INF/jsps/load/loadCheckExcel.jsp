<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%-- 	<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.8.3.min.js"></script> --%>

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
		</table>
	</form>
