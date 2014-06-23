<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
int totalPage = (Integer)request.getAttribute("totalPage");
int currentPage = (Integer)request.getAttribute("currentPage");
//int totalPage = 12;
//int currentPage = 6;
//int topicCount = 1000;
%>

<div id="yema" class="quotes">

	<% if(totalPage!=1){%>
		<% if(currentPage!=1){%>
	<span > <a title="上一页" href="?p=<%=currentPage-1 %>">&lt;</a> </span>
		<%} %> 
	<%} %> 
	<% if(totalPage<=10){
 	%>
		 <% for(int i=1;i<currentPage;i++){
 	%>
		  <span> <a title="转到第<%=i %>页" href="?p=<%=i %>"><%=i %></a> </span>
	<%} %> 
	<span class="current"><%=currentPage %></span>
	<% for(int j=currentPage+1;j<=totalPage;j++){
 	%>
 		  <span> <a title="转到第<%=j %>页" href="?p=<%=j %>"><%=j %></a> </span>
	<%} %> 
<%}else if(totalPage>10&&currentPage<5){%> 
	  
	<% for(int i=1;i<currentPage;i++){
 	%>
		  <a title="转到第<%=i %>页" href="?p=<%=i %>"><%=i %></a>
	<%} %> 
		  <span class="current"><%=currentPage %></span>
	<% for(int j=currentPage+1;j<=currentPage+3;j++){
 	%>
 		  <a title="转到第<%=j %>页" href="?p=<%=j %>"><%=j %></a>
	<%} %>
	 	  ...
	 	  <a title="转到第<%=totalPage %>页" href="?p=<%=totalPage %>"><%=totalPage %></a>
<%}else if(totalPage>10&&(totalPage-currentPage)<=3){ %>
		  <a title="转到第1页" href="?p=1">1</a>
		  ...
	<% for(int i=currentPage-3;i<currentPage;i++){
 	%>
		  <a title="转到第<%=i %>页" href="?p=1"><%=i %></a>
	<%} %> 
		 <span class="current"><%=currentPage %></span>
	<% for(int j=currentPage+1;j<=totalPage;j++){
 	%>
 		  <a title="转到第<%=j %>页" href="?p=<%=j %>"><%=j %></a>
	<%} %>
<%}else {%>
          <a title="转到第1页" href="?p=1">1</a>
		  ...
	<% for(int i=currentPage-3;i<currentPage;i++){
 	%>
		  <a title="转到第<%=i %>页" href="?p=<%=i %>"><%=i %></a>
	<%} %> 
	
		  <span class="current"><%=currentPage %></span>
	<% for(int j=currentPage+1;j<=currentPage+3;j++){
 	%>
 		   <a title="转到第<%=j %>页" href="?p=<%=j %>"><%=j %></a>		  
    <%} %>
          ...
	 	  <a title="转到第<%=totalPage %>页" href="?p=<%=totalPage %>"><%=totalPage %></a>
<%} %>

	<% if(totalPage!=currentPage){%>
		  <a title="下一页" href="?p=<%=currentPage +1%>">&gt;</a>
<%} %>
	

</div>

