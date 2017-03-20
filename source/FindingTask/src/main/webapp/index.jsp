<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>main page</title>
<style>
  .jumbotron {
      background-color: #4169E1;
      color: #fff;
  }
  </style>
</head>
<body>
<div class="jumbotron text-center">
  <h1>Extract Task Phrases</h1> 
</div>
<form action="finding" method="post">
	<textarea class="form-control" name="text" rows="10"></textarea>
	<button type="submit" class="btn btn-primary">send</button>
	<!-- <input type="submit" value="send"/> -->
</form>
	<% 
	@SuppressWarnings("unchecked")
	List<String> tasks = (List<String>)request.getAttribute("tasks");
	if(tasks != null){ %>
	<table> <!-- class="table" -->
	<%	
		String task = "";
		for(int i=0;i<tasks.size();i++){
			task = tasks.get(i);
	%>
		<tr>
			<td><%=task %></td>
		</tr>
		<% 	} %>
		<% if(task == ""){
			%>
		<tr>
			<td>there is no tasks.</td>
		</tr>
		<%} %>
	<% } %>
	</table>
</body>
</html>