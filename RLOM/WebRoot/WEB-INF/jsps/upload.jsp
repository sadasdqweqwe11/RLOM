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
<link rel="shortcut icon" href="<%=path%>/image/favicon.ico"
	type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<!-- saved from url=(0014)about:internet -->
<title>锐蓝标签生成--登录</title>
	<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.8.3.min.js"></script>
<script src="<%=path%>/amcharts/amcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/amcharts/serial.js"></script>
<script type="text/javascript" src="<%=path%>/amcharts/themes/light.js"></script>
<link rel="stylesheet" href="<%=path%>/js/date/kalendae.css"
	type="text/css" charset="utf-8">
<script src="<%=path%>/js/date/kalendae.standalone.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/jquery/jQuery-File-Upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery/jQuery-File-Upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery/jQuery-File-Upload/js/jquery.fileupload.js" type="text/javascript"></script>
<style type="text/css" media="screen">
.kalendae span.closed {
	background: red;
}

</style>
<style type="text/css">
.color a {
	border-radius: 3px 3px 3px 3px;
	font-size: 20px;
	line-height: 28px;
	width: 45px;
}
.color .c1 {
    color: #FF0000;
}
.color .c2 {
    color: #FFA60B;
}
.color .c3 {
    color: #36CF6B;
}
.color .c4 {
    color: #3066D6;
}
.color .c5 {
    color: #000000;
}
.color .c6 {
    color: #B459D4;
}
.color .c7 {
    color: #A87242;
}
.color .c1:hover {
    background: none repeat scroll 0 0 #CF2E2E;
    border: 1px solid #BF2C2C;
}
.color .c2:hover {
    background: none repeat scroll 0 0 #D68A39;
    border: 1px solid #C57F34;
}
.color .c3:hover {
    background: none repeat scroll 0 0 #35A45B;
    border: 1px solid #2B9851;
}
.color .c4:hover {
    background: none repeat scroll 0 0 #3066D6;
    border: 1px solid #ffffff;
}
.color .c5:hover {
    background: none repeat scroll 0 0 #000000;
    border: 1px solid #ffffff;
}
.color .c6:hover {
    background: none repeat scroll 0 0 #B459D4;
    border: 1px solid #A14AC0;
}
.color .c7:hover {
    background: none repeat scroll 0 0 #74480D;
    border: 1px solid #663F0A;
}
.color .c8:hover {
    background: none repeat scroll 0 0 #7B76B7;
    border: 1px solid #716CAF;
}
</style>

<script type="text/javascript">
	var chart;
	var chartData
AmCharts.ready(function() {
chart = new AmCharts.AmSerialChart();
chartData = loadStringData("<%=path%>/ajax/getChartsAjax.action?vendor=all");
chart.dataProvider = eval('(' + chartData + ')');//如果ajax获取得来的数据含有引号，需要eval()函数处理一下  
chart.categoryField = "logistics";
//描绘每一列
var graph = new AmCharts.AmGraph();
graph.valueField = "count";
graph.type = "column";
chart.addGraph(graph);

//设置将每一个列的标题都显示出来，如china等
var categoryAxis = chart.categoryAxis;
categoryAxis.autoGridCount  = false;
categoryAxis.gridCount = chartData.length;
categoryAxis.gridPosition = "start";
//纵向显示列标题，有时候列标题很长的时候会重叠，纵向则不会
categoryAxis.labelRotation = 90;

//为柱状图填充颜色饱和度以及颜色
graph.fillAlphas = 0.8;
graph.lineColor = "#33cc99";
//为柱状图增加3d效果
chart.angle = 30;
chart.depth3D = 15;

//var chartCursor = new AmCharts.ChartCursor();
//chart.addChartCursor(chartCursor);
//chartCursor.oneBalloonOnly = true;
//graph.showBalloon = false;
//graph.balloonText = "graph1:[[value1]]<br>graph2:[[value2]]<br>graph3:[[value3]]"; 


chart.startDuration = 1;
//鼠标划过柱状图后显示的文字，默认为数字，现在为列标题:加粗化数字
graph.balloonText = "[[category]]: <b>[[value]]</b>";
chart.write('chartdiv12');
});


            //刷新事件  

            
            function reloadData(url) {  
                var dynamicData = loadStringData(url);  
                chart.dataProvider = eval('(' + dynamicData + ')');//如果ajax获取得来的数据含有引号，需要eval()函数处理一下  
                chart.validateNow();  
                chart.validateData();  
            }  
            //ajax请求  
            function loadStringData(link) {  
               var request = new XMLHttpRequest();  
               request.open('GET', link, false);  
               request.send();  
               return request.responseText;  
            }  
            
           	$(function(){
            	$(".vendor").click(function(){
            		var ven = $(this).text();
            		$("#vendor").val(ven);
            		$(".download").text("下载" +ven +"总分拣单");
            		var date = $("#date").val();
					reloadData("<%=path%>/ajax/getChartsAjax.action?vendor=" + ven+"&date="+date);
                });
                
                
                $(".download").click(function(){
					$("#form3").attr("action", "<%=path%>/downloadZFJD?"+new Date()).submit();
                });
                $(".downloadVendor").click(function(){
					$("#form3").attr("action", "<%=path%>/downloadVendor?"+new Date()).submit();
                });
                
                
                $('#fileupload').fileupload({
					url:"ajax/uploadAjax.action",
    				done: function (e, data) {
        				$.each(data.result, function (index, file) {
            			$('<p/>').text(file.name + ' uploaded').appendTo($("body"));
        			});
    				}
				});
				
				$(".yanwen").click(function(){
					 $.ajax({  
            url:"<%=path%>/xml/yanwenLogin.action?name=303616&password=RL303616",  
            type:"GET",  
            dataType:"xml",  
            error: function(xml){  
                alert('Error loading XML document'+xml);  
            },  
            success: function(xml){  
               // $(xml).find("User").each(function(i){  
               //     var id=$(this).children("id");   //取对象  
               //     var id_value=$(this).children("id").text();  //取文本 或者 $("id" , xml).text();   
               //     var name_value=$(this).children("name").text();  
                    //alert(id_value);//这里就是ID的值了。  
                    //alert($(this).attr("email")); //这里能显示student下的email属性。  
                //    $('<li></li>').html(id_value+"&nbsp;&nbsp;&nbsp;"+name_value+"&nbsp;&nbsp;&nbsp;"+$(this).attr("email")).appendTo('ol');  
                //});
                var apiToken =   $(xml).find("User").children("ApiToken").text();
                alert(apiToken);
            }  
        }); 
        				}); 
				$(".getTrackingno").click(function(){
					 $.ajax({  
            url:"<%=path%>/xml/getTrackingno.action",  
            type:"GET",  
            dataType:"xml",  
            timeout: 5000,  
            error: function(xml){  
                alert('Error loading XML document'+xml);  
            },  
            success: function(xml){  
               // $(xml).find("User").each(function(i){  
               //     var id=$(this).children("id");   //取对象  
               //     var id_value=$(this).children("id").text();  //取文本 或者 $("id" , xml).text();   
               //     var name_value=$(this).children("name").text();  
                    //alert(id_value);//这里就是ID的值了。  
                    //alert($(this).attr("email")); //这里能显示student下的email属性。  
                //    $('<li></li>').html(id_value+"&nbsp;&nbsp;&nbsp;"+name_value+"&nbsp;&nbsp;&nbsp;"+$(this).attr("email")).appendTo('ol');  
                //});
                var apiToken =   $(xml).find("User").children("ApiToken").text();
                alert(apiToken);
            }  
        });  
				
				
					<%-- $.get("<%=path%>/xml/yanwenLogin.action?name=100000&password=100001",function(data){
						var uid = $(data).find(User).text();
						alert(uid);
  					});  --%>
					 
/* 
	    $.ajax({
            url: "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20xml%20where%20url%3D'http%3A%2F%2Fwww.webservicex.net%2FCurrencyConvertor.asmx%2FConversionRate%3FFromCurrency%3DNOK%26ToCurrency%3DEUR'&format=json&callback=cbfunc",
            dataType: 'jsonp',
            jsonp: 'callback',
            jsonpCallback: 'cbfunc'
        });
        function cbfunc(data) {
            alert("OK");
        } */

					/* $.ajax({
	  					//url: 'http://online.yw56.com.cn/service_sandbox/Common/LoginUser/100000/100001',
	  					url: 'http://localhost:8080/RLOM/html/xm.xml',
	  					dataType: 'html',
	  					success: function(data){
	  					processData: false,
					 	$(data).find("LoginUserResponseType").each(function(index, ele) {
							var titles = $(ele).find("title").text();
							var links = $(ele).find("link").text();
						});
						 
					 	}
					});
				 */
				});
				
				
            });
            

            
</script>


</head>

<body>
<div style="">
	<form id="form1" name="form1" method="post"
		enctype="multipart/form-data" action="<%=path%>/upload.action">
		<table>
			<tr>
				<td><a href="<%=path%>/main.action" style="color:#0000CC;">返回主页</a>||<a href="<%=path%>/listUploadFiles" style="color:#0000CC;">我的订单</a><br />
					<br /></td>
			</tr>
			<tr>
				<td><span class="reply-tr">支持后缀名为xls的excel97~2003文件。</span><br />
					<br /> 
					<input type="file" name="uploadFile">
  					<input type="submit"  value="预览" />
				</td>
			</tr>
		</table>
	</form>
	
		<form id="form2" name="form2" method="post"
		enctype="multipart/form-data" action="<%=path%>/uploadTrackingno.action">
		<table>
			<tr>
				<td><span class="reply-tr">上传TrackingNo。</span><br />
					<br /> 
					<input type="file" name="uploadTrackingno">
  					<input type="submit"  value="预览" />
				</td>
			</tr>
		</table>
	</form>
	
	<form id="form4" name="form4" method="post"
		enctype="multipart/form-data" action="<%=path%>/uploadAmazonAmount.action">
		<table>
			<tr>
				<td><span class="reply-tr">上传Amazon amount。</span><br />
					<br /> 
					<input type="file" name="uploadAmount">
  					<input type="submit"  value="预览" />
				</td>
			</tr>
		</table>
	</form>
	
<%-- 	<form id="form3" name="form3" method="post"
		enctype="multipart/form-data" action="<%=path%>/uploadSkuinfo.action">
		<table>
			<tr>
				<td><span class="reply-tr">上传Sku info</span><br />
					<br /> 
					<input type="file" name="skuinfo">
  					<input type="submit"  value="预览" />
				</td>
			</tr>
		</table>
	</form> --%>
	
	<div id="chartdiv12" style="width: 640px; height: 400px;"></div>
<form id="form3" name="form3" method="post"
		enctype="multipart/form-data" action="<%=path%>/.action">
 				请选择日期: <input type="text" value="" id="date">
		<script type="text/javascript" charset="utf-8">
			new Kalendae.Input('date', {
				months : 2,
				mode : 'range',
			});
		</script> 
					<input id="vendor" name="vendor" type="text" style="visibility: hidden;" value="ALL">
									<a href="javascript:void(0)" class="download">下载总分拣单</a>
									<a href="javascript:void(0)" class="downloadVendor">下载供应商标签</a>
	</form>
				<p class="color">
				<a href="javascript:void(0)" class="c1 vendor">ALL</a>
			<c:forEach items="${vendors}" var="vendor"  varStatus="sum">
				<a href="javascript:void(0)" class="c${(sum.index+2)%8+1} vendor">${vendor}</a>
			</c:forEach>
			<a href="javascript:void(0)" class="yanwen">燕文对接</a>
			<a href="javascript:void(0)" class="getTrackingno">trac</a>
				</p>
				
				
				
</div>
<%-- <input id="fileupload" type="file" name="files" data-url="<%=path%>/ajax/uploadOrderAjax" multiple> --%>
</body>
</html>
