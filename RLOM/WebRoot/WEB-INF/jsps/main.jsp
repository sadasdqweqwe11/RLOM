<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="./common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>锐蓝订单处理系统</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<%--<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600"
	rel="stylesheet" type="text/css" />
--%><!--[if lte IE 8]><script src="<%=path%>/main/js/html5shiv.js"></script><![endif]-->
<script src="<%=path%>/main/js/jquery.min.js"></script>
<script src="<%=path%>/main/js/skel.min.js"></script>
<script src="<%=path%>/main/js/skel-panels.min.js"></script>
<script src="<%=path%>/main/js/init.js"></script>
<script src="<%=path%>/amcharts/amcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/amcharts/serial.js"></script>
<script type="text/javascript" src="<%=path%>/amcharts/themes/light.js"></script>
<noscript>
	<link rel="stylesheet" href="<%=path%>/main/css/skel-noscript.css" />
	<link rel="stylesheet" href="<%=path%>/main/css/style.css" />
	<link rel="stylesheet" href="<%=path%>/main/css/style-wide.css" />
</noscript>
<!--[if lte IE 9]><link rel="stylesheet" href="<%=path%>/main/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="<%=path%>/main/css/ie8.css" /><![endif]-->


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
chart.write('chartdiv');
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
        		$(".download").text("下载" +ven +"总分拣单")
				reloadData("<%=path%>/ajax/getChartsAjax.action?vendor=" + ven);
            });
            
            
            $(".download").click(function(){
				$("#form3").attr("action", "<%=path%>/downloadZFJD?"+new Date()).submit();
            });
            
            $("#upload").hide();
            $("#upload").change(function(){
            	var value = $(this).val();
            	$("#choose").val(value);
            });
            
            
            $("#choose").click(function(){
            	$("#upload").click();
            });
            
            $(".sc").click(function(){
				$("#form1").submit();
				$("#uploadTable").load('<%=path%>/ajax/orderTableAjax.action', {date: selDate ,func:fun, code :number });				
            });
            
        });
</script>

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
    border: 1px solid #2A5ABE;
}
.color .c5:hover {
    background: none repeat scroll 0 0 #000000;
    border: 1px solid #000000;
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


</head>
<body>

	<!-- Header -->
	<div id="header" class="skel-panels-fixed">

		<div class="top">

			<!-- Logo -->
			<div id="logo">
				<span class="image avatar48"><img
					src="<%=path%>/main/images/avatar.jpg" alt="" />
				</span>
				<h1 id="title">Jane Doe</h1>
				<span class="byline">Hyperspace Engineer</span>
			</div>

			<!-- Nav -->
			<nav id="nav">
				<ul>
					<li><a href="#top" id="top-link"
						class="skel-panels-ignoreHref"><span class="icon icon-home">Home</span>
					</a>
					</li>
					<li><a href="#portfolio" id="portfolio-link"
						class="skel-panels-ignoreHref"><span class="icon icon-th">Status</span>
					</a>
					</li>
					<li><a href="#about" id="about-link"
						class="skel-panels-ignoreHref"><span class="icon icon-user">Download</span>
					</a>
					</li>
					<li><a href="#contact" id="contact-link"
						class="skel-panels-ignoreHref"><span
							class="icon icon-envelope">Search</span>
					</a>
					</li>
				</ul>
			</nav>

		</div>

		<div class="bottom">

			<!-- Social Icons -->
			<ul class="icons">
				<li><a href="#" class="icon icon-twitter"><span>Twitter</span>
				</a>
				</li>
				<li><a href="#" class="icon icon-facebook"><span>Facebook</span>
				</a>
				</li>
				<li><a href="#" class="icon icon-github"><span>Github</span>
				</a>
				</li>
				<li><a href="#" class="icon icon-dribbble"><span>Dribbble</span>
				</a>
				</li>
				<li><a href="#" class="icon icon-envelope"><span>Email</span>
				</a>
				</li>
			</ul>

		</div>

	</div>

	<!-- Main -->
	<div id="main">

		<!-- Intro -->
		<section id="top" class="one">
			<div class="container">
			<div id="chartdiv" style="width: 800px; height: 400px;float:left;"></div>
			<p style="position:relative;top:100px">点击<strong>这里</strong>上传excel文件</p>
				
			
				<input id="choose" type="text" class="text" style="position:relative;top:70px;clear:both;width:200px;" placeholder="上传" />
			<strong><a href="javascript:void(0)" class="sc" style="position:relative;top:70px;clear:both;font-color:#74480D">上传</a></strong>
			
			
				<%-- <a href="" class="image featured">
							<img src="<%=path%>/main/images/pic01.jpg" alt="" /> 
				</a>
							--%>
				<header style="clear:left">
					<h2 class="alt">
<br/>
						<strong>点击选择</strong>供应商  下载供应商总分拣单.
					</h2>
				</header>

				<p class="color">
				<a href="javascript:void(0)" class="c1 vendor">ALL</a>
			<c:forEach items="${vendors}" var="vendor"  varStatus="sum">
				<a href="javascript:void(0)" class="c${(sum.index+1)%8} vendor">${vendor}</a>
			</c:forEach>
				</p>


				<footer>
					<a href="#portfolio" class="button scrolly download">下载当日总分拣单</a>
				</footer>

			</div>
		</section>

		<!-- Portfolio -->
		<section id="portfolio" class="two">
			<div class="container">

				<header>
					<h2>请校验表单</h2>
				</header>
				<div id="uploadTable">
				</div>
<!-- 
				<p>Vitae natoque dictum etiam semper magnis enim feugiat
					convallis convallis egestas rhoncus ridiculus in quis risus amet
					curabitur tempor orci penatibus. Tellus erat mauris ipsum fermentum
					etiam vivamus eget. Nunc nibh morbi quis fusce hendrerit lacus
					ridiculus.</p>

				<div class="row">
					<div class="4u">
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/A-million-suns-384369739"
								class="image full"><img
								src="<%=path%>/main/images/pic02.jpg" alt="" />
							</a>
							<header>
								<h3>Ipsum Feugiat</h3>
							</header>
						</article>
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/Mind-is-a-clear-stage-375431607"
								class="image full"><img
								src="<%=path%>/main/images/pic03.jpg" alt="" />
							</a>
							<header>
								<h3>Rhoncus Semper</h3>
							</header>
						</article>
					</div>
					<div class="4u">
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/You-really-got-me-345249340"
								class="image full"><img
								src="<%=path%>/main/images/pic04.jpg" alt="" />
							</a>
							<header>
								<h3>Magna Nullam</h3>
							</header>
						</article>
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/Ad-infinitum-354203162"
								class="image full"><img
								src="<%=path%>/main/images/pic05.jpg" alt="" />
							</a>
							<header>
								<h3>Natoque Vitae</h3>
							</header>
						</article>
					</div>
					<div class="4u">
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/Elysium-355393900"
								class="image full"><img
								src="<%=path%>/main/images/pic06.jpg" alt="" />
							</a>
							<header>
								<h3>Dolor Penatibus</h3>
							</header>
						</article>
						<article class="item">
							<a
								href="http://ineedchemicalx.deviantart.com/art/Emperor-of-the-Stars-370265193"
								class="image full"><img
								src="<%=path%>/main/images/pic07.jpg" alt="" />
							</a>
							<header>
								<h3>Orci Convallis</h3>
							</header>
						</article>
					</div>
				</div>
 -->

			</div>
		</section>

		<!-- About Me -->
		<section id="about" class="three">
			<div class="container">

				<header>
					<h2>About Me</h2>
				</header>

				<a
					href="http://ineedchemicalx.deviantart.com/art/Pasadena-357650036"
					class="image featured"><img
					src="<%=path%>/main/images/pic08.jpg" alt="" />
				</a>

				<p>Tincidunt eu elit diam magnis pretium accumsan etiam id urna.
					Ridiculus ultricies curae quis et rhoncus velit. Lobortis elementum
					aliquet nec vitae laoreet eget cubilia quam non etiam odio
					tincidunt montes. Elementum sem parturient nulla quam placerat
					viverra mauris non cum elit tempus ullamcorper dolor. Libero rutrum
					ut lacinia donec curae mus vel quisque sociis nec ornare iaculis.</p>

			</div>
		</section>

		<!-- Contact -->
		<section id="contact" class="four">
			<div class="container">

				<header>
					<h2>Contact</h2>
				</header>

				<p>Elementum sem parturient nulla quam placerat viverra mauris
					non cum elit tempus ullamcorper dolor. Libero rutrum ut lacinia
					donec curae mus. Eleifend id porttitor ac ultricies lobortis sem
					nunc orci ridiculus faucibus a consectetur. Porttitor curae mauris
					urna mi dolor.</p>

				<form method="post" action="#">
					<div class="row half">
						<div class="6u">
							<input type="text" class="text" name="name" placeholder="Name" />
						</div>
						<div class="6u">
							<input type="text" class="text" name="email" placeholder="Email" />
						</div>
					</div>
					<div class="row half">
						<div class="12u">
							<textarea name="message" placeholder="Message"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="12u">
							<a href="#" class="button submit">Send Message</a>
						</div>
					</div>
				</form>

			</div>
		</section>

	</div>

	<!-- Footer -->
	<div id="footer">

		<!-- Copyright -->
		<div class="copyright">
			<p>&copy; 2014 RayLine tech. All rights reserved.</p>
			<ul class="menu">
				<li>Design: <a href="#">HTML5 UP</a>
				</li>
			</ul>
		</div>

	</div>
	<form id="form1" name="form1" method="post"
		enctype="multipart/form-data" action="<%=path%>/upload.action">
				<input id="upload" type="file"  name="uploadFile"/>
				</form>
</body>
</html>