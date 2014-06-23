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
		
		var postal = ["GY","IV","HS","KA27","KA28","KW","PA20","PA21","PA22","PA23","PA24","PA25","PA26","PA27","PA28","PA29","PA30","PA31","PA32","PA33","PA34","PA35","PA36","PA37","PA38","PA39","PA60","PA61","PA62","PA63","PA64","PA65","PA66","PA67","PA68","PA69","PA70","PA71","PA72","PA73","PA74","PA75","PA76","PA77","PA78","PH17","PH18","PH19","PH20","PH21","PH22","PH23","PH24","PH25","PH26"," PH30","PH31","PH32","PH33","PH34","PH35","PH36","PH37","PH38"," PH39","PH40"," PH41","PH42"," PH43","PH 44","PH49","PH50","IM","BT","JE","2E","TR21","TR22","TR23","TR24","TR25"];
		$("#change").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				var flag = true;
				var codes = "";
				if($("#logistics").val()==10 ||$("#logistics").val()==11 ||$("#logistics").val()==12){
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
</head>

<body>
	<form id="form" method="post"><a href="<%=path%>/main.action" style="color:#0000CC;">返回主页</a>||<a href="<%=path%>/listUploadFiles" style="color:#0000CC;">我的订单</a>
		<span class="STYLE7">${orderFile.filename} 上传时间
			${orderFile.postdatetime}</span> <input name="fid" type="text"
			value="${orderFile.id }" style="visibility: hidden;" />
		<c:if test="${orderFile.description == null}">
		<select id="logistics" name="logisticsid">  
            <c:forEach items="${logisticss}" var="logistics">
            <option value="${logistics.id}" >${logistics.name}</option>  
            </c:forEach>
        </select>  			
			<input id="change" type="button" value="修改"/>
		</c:if>
		<%-- <input type="submit" value="删除"
			onclick="document.getElementById('form').action='<%=path%>/deleteExcel';document.getElementById('form').submit();" /> --%>
		
			<table width="1000" border="1" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="true">
				<td  class="true" valign="middle"><span
					class="STYLE7"></span>
				</td>
				<td  class="true" valign="middle"><span
					class="STYLE7">IND</span>
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
				<td  class="true" valign="middle"><span
					class="STYLE7">LOGI</span>
				</td>
			</tr>
			<c:forEach items="${orderMap}" var="entry" varStatus="all">
				<c:forEach items="${entry.value}" var="order" varStatus="sum">
					<tr class="${entry.key }">
						<td   class="true"><input type="checkbox" name="orders" class="${order.vendor }" value="${order.id}"></td>
						<td   class="true">${sum.index+1}</td>
						<td   class="true">${order.rlordernumber}</td>
						<td   class="true"><span class="selVendor">${order.vendor}</span></td>
						<td   class="true">${order.skuno}</td>
						<td   class="true">${order.quantity}</td>
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
						<td   class="true">${order.logistics.name}</td>
					</tr>
				</c:forEach>
			</c:forEach>
			<tr>
					<td width="20px" align="left" valign="middle"><input
						type="checkbox" id="all"/></td>
				<td ><span>全选</span></td>
					</tr>
			</table>
	</form>

</body>
</html>
