<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" charset="utf-8">
	$(function(){
		$("#all").click(function(){
		if($("#all").attr("checked")){
			$("input[name='orders']").attr("checked",true);			
		}else{
			$("input[name='orders']").attr("checked",false);		
		}
		});
		
		$(".selVendor").click(function(){
			var vendor = $(this).text();
			$("input[name='orders']").attr("checked",false);		
			$("."+vendor).attr("checked",true);		
		});
		$(".scbd").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				$("#form").attr("action", "<%=path%>/downloadSCBD?"+new Date()).submit();
			}
		});
		$(".fjd").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				$("#form").attr("action", "<%=path%>/downloadFJD?"+new Date()).submit();
			}
		});
		$(".bq").click(function(){
			if($('input[name="orders"]:checked').length==0){
				alert("请选择至少一项");
				return false;
			}else{
				var flag = true;
				var codes = "";
				$('input[name="orders"]:checked').each(function(){
					var logiid = "logi" + $(this).val();
					var loginame = $("#"+logiid).text().trim();
					if(loginame == "未选择物流"){
						flag= false;
					}
				});
				if(flag){
					$("#form").attr("action", "<%=path%>/downloadBQ.action?"+new Date()).submit();
				}else{
					alert("选中的条目存在未选择物流项请重新选择");
				}
			}
		});
<%-- 		$(".generate").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				$("#form").attr("action", "<%=path%>/ajax/generateTrackingnoAjax.action?"+new Date()).submit();
			}
		}); --%>
		$(".del").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				$("#form").attr("action", "<%=path%>/deleteRLOrders.action?"+new Date()).submit();
			}
		});
		$(".tracking").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				$("#form").attr("action", "<%=path%>/downloadTable.action?"+new Date()).submit();
			}
		});
		
		$(".generate").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
						return false;
			}
			var idList = '';
            $('input[name="orders"]:checked').each(function () {
                idList += $(this).val() + ',';
            });
            idList= idList.substr(0, idList.length-1 );
			$.post("<%=path%>/ajax/generateTrackingnoAjax.action",{orders1:idList},function(result){
				var selDate = $("#date").val();
				var fun = $("#first").val();
				var number = $("#area").val();
			$("#tableDiv").load('<%=path%>/ajax/orderTableAjax.action', {date: selDate ,func:fun, code :number });	
			});			
		});
		
		var postal = ["GY","IV","HS","KA27","KA28","KW","PA20","PA21","PA22","PA23","PA24","PA25","PA26","PA27","PA28","PA29","PA30","PA31","PA32","PA33","PA34","PA35","PA36","PA37","PA38","PA39","PA60","PA61","PA62","PA63","PA64","PA65","PA66","PA67","PA68","PA69","PA70","PA71","PA72","PA73","PA74","PA75","PA76","PA77","PA78","PH17","PH18","PH19","PH20","PH21","PH22","PH23","PH24","PH25","PH26"," PH30","PH31","PH32","PH33","PH34","PH35","PH36","PH37","PH38"," PH39","PH40"," PH41","PH42"," PH43","PH 44","PH49","PH50","IM","BT","JE","2E","TR21","TR22","TR23","TR24","TR25"];
		$("#change").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				var flag = true;
				var codes = "";
				if($("#logi").val()==10 ||$("#logi").val()==11 ||$("#logi").val()==12){
				$('input[name="orders"]:checked').each(function(){
					var postid = "post" + $(this).val();
					$(postal).each(function(){
						var postalcode = $("#"+postid).text().toUpperCase().trim();
							if(postalcode.indexOf(this) >=0){
								if(codes == ""){
									codes = postalcode;
								}else{
								codes = codes+ " , " +postalcode;
								}
								flag= false;
							}
						});
					});
				}
				if(flag){
				$("#form").attr("action", "<%=path%>/<%=path%>/changeLogistics.action?"+new Date()).submit();
				}else{
								alert("该邮编"+ codes +"使用Equick方式不可送达，请重新选择");
				}
			}
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
		<form id="form" method="post">
			<table width="1000" border="1" align="center"
			cellpadding="0" cellspacing="0" id="prTable">
			<tr class="true">
				<td  class="true" valign="middle"><span
					class="STYLE7"></span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">IND</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">RLO</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">TRA</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">ORI</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">VENDOR</span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">LOGI</span>
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
				<c:forEach items="${rlOrders}" var="order" varStatus="sum">
					<tr class="">
						<td   class="true"><input type="checkbox" name="orders" class="${order.vendor }" value="${order.id}"></td>
						<td   class="true">${sum.index+1}</td>
						<td   class="true">${order.rlordernumber}</td>
						<td   class="true">${order.trackingno}</td>
						<td   class="true">${order.ordernumber}</td>
						<td   class="true"><span class="selVendor">${order.vendor}</span></td>
						<td   id="logi${order.id}" class="true">${order.logistics.name}</td>
						<td   class="true">${order.buyername}</td>
						<td   class="true">${order.shipaddress1}</td>
						<td   class="true">${order.shipcity}</td>
						<td  id="post${order.id}" class="true">${order.postalcode}</td>
						<td   class="true">${order.shipcountry}</td>
						<td   class="true">${order.date}</td>
						<td   class="true">${order.amount}</td>
						<td   class="true">${order.guojia}</td>
						<td   class="true">${order.marketplace}</td>
						<td   class="true">${order.account}</td>
						<td   class="true">${order.currency}</td>
					</tr>
				</c:forEach>
					<tr>
					<td width="20px" align="left" valign="middle"><input
						type="checkbox" id="all"/></td>
				<td ><span>全选</span></td>
				<c:if test="${logisticsid== 10||logisticsid== 11||logisticsid== 12||logisticsid== 13||logisticsid== 14}">
				<td ><span class="generate">生成运单号</span></td>
				</c:if>
				<c:if test="${func== 1||func== 2||func== 3}">				
				<td ><span class="scbd">下载上传表单</span></td>
				</c:if>
				<td ><span class="fjd">下载分拣单</span></td>
				<td >
 				<%--<c:if test="${logisticsid== 1||logisticsid== 3||logisticsid== 4||logisticsid== 5||logisticsid== 9||logisticsid== 10}">
				<c:if test="${func== 1}">
				</c:if>--%>
				<span class="bq">下载标签</span>
 				</td><td ><span class="tracking">下载运单号</span></td>
				<td width="100px">
						<select id="logi" name="logisticsid">  
            <c:forEach items="${logisticss}" var="logistics">
            <option value="${logistics.id}" >${logistics.name}</option>  
            </c:forEach>
        	</select>
				</td>
				
							<td width="100px"><input id="change" type="button" value="修改"/></td>
				<td width="100px"><span class="del">删除</span></td>
					</tr>
				</table>
		</form>
		<span id="logid" style="visibility: hidden;">${logisticsid}</span>