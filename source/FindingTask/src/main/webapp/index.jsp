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
</head>
<body>
<div style="color:#990033">
  <p>To find tasks, input sentences below:</p>
</div>
<form action="finding" method="post">
	<textarea name="text" rows="10" cols="30"></textarea>
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