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
	<script type="text/javascript" src="<%=path %>/js/jquery/dataTables/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" href="<%=path %>/js/jquery/dataTables/css/jquery.dataTables.css" type="text/css" />
<script type="text/javascript"> 
        $(function (){  
    		$.fn.squareSelection=function(){  
    	        var container = $(this);  
    	        var stop_pop = function(e) {  
    	            try {  
    	                e.stopPropagation();  
    	            } catch(err) {  
    	                window.event.cancelBubble = true;  
    	            }  
    	        };  
    	        var box = function(){  
    	            if($('#square_selection_box').length == 0){  
    	                $('<div></div>',{id:'square_selection_box', style: [  
    	                    'position:absolute; top:0; left:0;',  
    	                    'border:1px solid #072246; background: #6bb0c9;',  
    	                    'filter:Alpha(Opacity=15); opacity:0.15;',  
    	                    'overflow:hidden;display:none;z-index:99999;'  
    	                ].join('')}).appendTo(document.body);  
    	            }  
    	            return $('#square_selection_box');  
    	        };  
    	        var init = function(){  
    	            document.oncontextmenu=function(){ return false; };   
    	              
    	            $(container).find(':checkbox').mousedown(function(e){  
    	                stop_pop(e);  
    	            });  
    	              
    	            // mouse up  
    	            $(document).bind('mouseup', function(event){  
    	                  
    	                $(container)[0].setCapture && $(container)[0].releaseCapture();  
    	              
    	                if(box().css('display') != 'none') {  
    	                    var box_offset_top = box().offset().top;  
    	                    var box_offset_bottom = box().offset().top + box().height();  
    	                    $(container).find(':checkbox').each(function(i,item){  
    	                        var offset_top = $(item).offset().top;  
    	                        var offset_bottom = $(item).offset().top + $(item).height();  
    	                        if (offset_bottom >= box_offset_top && offset_top <= box_offset_bottom) {  
    	                            $(item).attr('checked',true);         
    	                        }  
    	                    });  
    	                }  
    	                  
    	                box().hide();  
    	                $(container).unbind('mousemove');     
    	            });       
    	            // mouse down  
    	            $(container).mousedown(function(event){  
    	                  
    	                event.preventDefault && event.preventDefault(); // 阻止默认行为发生  
    	                  
    	                //$(container).find(':checkbox').attr('checked',false);  
    	                  
    	                var startPos = {  
    	                    top: event.clientY + $(document).scrollTop(),   
    	                    left: event.clientX + $(document).scrollLeft()  
    	                };  
    	  
    	                this.setCapture && this.setCapture();     
    	                // mouse move  
    	                $(container).unbind('mousemove').mousemove(function(event){  
    	                      
    	                    document.selection && document.selection.empty(); // for ie 取消选择  
    	                    window.getSelection && window.getSelection().removeAllRanges();  // for non ie 取消选择  
    	  
    	                    var top = event.clientY + $(document).scrollTop();  
    	                    var left = event.clientX + $(document).scrollLeft();  
    	                    var endPos = {  
    	                        top:  Math.max(top,  container.offset().top),   
    	                        left: Math.max(left, container.offset().left)  
    	                    };  
    	  
    	                    var fixedPoint = { // 设置定点  
    	                        left: endPos.left > startPos.left ? startPos.left : endPos.left,  
    	                        top: endPos.top > startPos.top ? startPos.top : endPos.top  
    	                    };   
    	  
    	                    var w = Math.min(Math.abs(endPos.left - startPos.left), container.offset().left + container.width() - fixedPoint.left);  
    	                    var h = Math.min(Math.abs(endPos.top - startPos.top), container.offset().top + container.height() - fixedPoint.top);  
    	  
    	                    box().show().css({left: fixedPoint.left+'px', top: fixedPoint.top+'px', width: w+'px', height: h+'px'});              
    	                });       
    	            });  
    	        }  
    	  
    	        init();  
    	        return $(this);    
    	    };  
    	    $('#prTable').squareSelection();
        	
    		$('#prTable').dataTable( {
    	        "paging":   false
    	    } );
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
		
		var postal = ["GY","IV","HS","KA27","KA28","KW","PA20","PA21","PA22","PA23","PA24","PA25","PA26","PA27","PA28","PA29","PA30","PA31","PA32","PA33","PA34","PA35","PA36","PA37","PA38","PA39","PA60","PA61","PA62","PA63","PA64","PA65","PA66","PA67","PA68","PA69","PA70","PA71","PA72","PA73","PA74","PA75","PA76","PA77","PA78","PH17","PH18","PH19","PH20","PH21","PH22","PH23","PH24","PH25","PH26"," PH30","PH31","PH32","PH33","PH34","PH35","PH36","PH37","PH38"," PH39","PH40"," PH41","PH42"," PH43","PH 44","PH49","PH50","IM","BT","JE","ZE","TR21","TR22","TR23","TR24","TR25"];
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

		$(".copyOrder").click(function(){
			if($('input[name="orders"]:checked').length==0){
				alert("请选择至少一项");
				return false;
				}else{
			var flag = confirm('是否确定复制订单');
			if(flag){
				$("#form").attr("action", "<%=path%>/<%=path%>/copyOrder.action?"+new Date()).submit();
			}
				}
		});
		

		$(".splitOrder").click(function(){
			if($('input[name="orders"]:checked').length==0){
				alert("请选择至少一项");
				return false;
				}else{
			var flag = confirm('是否确定拆单');
			if(flag){
				$("#form").attr("action", "<%=path%>/<%=path%>/splitOrder.action?"+new Date()).submit();
			}
				}
			
		});
		

		$(".mergeOrder").click(function(){
			if($('input[name="orders"]:checked').length<2){
				alert("请选择至少两项");
				return false;
				}else{
					var flag = confirm('是否确定合单');
					if(flag){
						var nameFlag = true;
						var name = "";
						$('input[name="orders"]:checked').each(function(){
							var id = $(this).val();
							if(name==""){
								name = $("#name"+id).text();
							}else{
								nameFlag=nameFlag && (name ==$("#name"+id).text());
							}
						});
						if(nameFlag){
							$("#form").attr("action", "<%=path%>/<%=path%>/mergeOrder.action?"+new Date()).submit();
						}else{
							alert("收货人必须相同才可以合单");
						}
					}
				}
		});
		
		$(".del").click(function(){
			if($('input[name="orders"]:checked').length==0){
			alert("请选择至少一项");
			return false;
			}else{
				var flag = confirm("确定要删除吗？");
				if(flag){
				$("#form").attr("action", "<%=path%>/deleteRLOrders.action?"+new Date()).submit();
				}
			}
		});
		
		
         });
                

</script>

		<style type="text/css">
<!--
.split0 {
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

.split1 {
	font-family: "楷体";
	background-color: #0092c7;
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}

.split2 {
	font-family: "楷体";
	background-color: #f3e59a;
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}
.split3 {
	font-family: "楷体";
	background-color: #9fe0f6;
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}

.split4 {
	font-family: "楷体";
	background-color: #f3b59b;
	font-size: 12px;
	padding:2px 2px 2px 2px;
	text-align:center;
}
.split5 {
	font-family: "楷体";
	background-color: #f29c9c;
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
		
			<table width="1000" class="cell-border display" align="center"
			cellpadding="0" cellspacing="0" id="prTable">
			    <thead>
			<tr class="true">
				<th  valign="middle"><span
					class="STYLE7"></span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">IND</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">RLO</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">ORI</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">VENDOR</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">SKU</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">QUA</span>
				</th>
				
				<th   valign="middle"><span
					class="STYLE7">GUO</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">MARK</span>
				</th>

				<th   valign="middle"><span
					class="STYLE7">ACCO</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">CURR</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">LOGI</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">DATE</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">NAME</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">ADD1</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">CITY</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">POST</span>
				</th>
				<th   valign="middle"><span
					class="STYLE7">COUN</span>
				</th>

			</tr>
			</thead>
			<tfoot>
			<tr class="true">
				<th  valign="middle"><input
						type="checkbox" id="all"/>
				</th>
				<th  valign="middle"><span
					class="STYLE7">全选</span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span class="splitOrder">拆单</span>
				</th>
				<th  valign="middle"><span class="mergeOrder">合单</span>
				</th>
				<th  valign="middle"><span class="copyOrder" >复制</span>
				</th>
				
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
								<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle">
					<span class="del">删除</span>
				</th>


				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>

				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
				<th  valign="middle"><span
					class="STYLE7"> </span>
				</th>
			</tr>
			</tfoot>
			    <tbody>
			<c:forEach items="${orderMap}" var="entry" varStatus="all">
				<c:forEach items="${entry.value}" var="order" varStatus="sum">
				<tr>
					<td class="split${order.splitstatus}"><input type="checkbox" name="orders" class="${order.vendor}" value="${order.id}"></td>
					<td class="split${order.splitstatus}">${sum.index+1}</td>
					<td class="split${order.splitstatus}">${order.rlordernumber}</td>
					<td class="split${order.splitstatus}">${order.ordernumber}</td>
					<td class="split${order.splitstatus}"><span class="selVendor">${order.rlorderitems[0].sku.vendor}</span></td>
					<td class="split${order.splitstatus}">
					${order.rlorderitems[0].sku.skuno}
					<c:forEach items="${order.rlorderitems}" var="item"  begin="1" end="${fn:length(order.rlorderitems)}" varStatus="itsum">
						<br/>${item.sku.skuno}
					</c:forEach>
					</td>
					<td class="split${order.splitstatus}">
					${order.rlorderitems[0].quantity}
					<c:forEach items="${order.rlorderitems}" var="item"  begin="1" end="${fn:length(order.rlorderitems)}" varStatus="itsum">
						<br/>${item.quantity}
					</c:forEach>
					</td>
					
					<td class="split${order.splitstatus}">${order.guojia}</td>
					<td class="split${order.splitstatus}">${order.marketplace}</td>
					<td class="split${order.splitstatus}">${order.account}</td>
					<td class="split${order.splitstatus}">${order.currency}</td>
					<td class="split${order.splitstatus}">${order.logistics.name}</td>
					<td class="split${order.splitstatus}">${order.date}</td>
					<td class="split${order.splitstatus}" id="name${order.id}">${order.buyername}</td>
					<td class="split${order.splitstatus}">${order.shipaddress1}</td>
					<td class="split${order.splitstatus}">${order.shipcity}</td>
					<td id="post${order.id}" class="split${order.splitstatus}">${order.postalcode}</td>
					<td class="split${order.splitstatus}">${order.shipcountry}</td>

				</tr>

				</c:forEach>
			</c:forEach>
			    </tbody>
			</table>
	</form>

</body>
</html>
