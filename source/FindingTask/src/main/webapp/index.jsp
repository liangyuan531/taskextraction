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
  <p>reduce your reading loads</p> 
</div>
<form action="finding" method="post">
	<textarea class="form-control" name="text" rows="10"></textarea>	
	<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><span class="label label-primary">programming</span></th>
        			<th><span class="label label-primary">generic</span></th>
      			</tr>
    		</thead>
    		<tbody>
      			<tr>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="programming" value="yes">Yes</label>
    				</div>
    				<div class="radio">
      					<label><input type="radio" name="programming" value="no">No</label>
    				</div>
       			</td>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="generic" value="yes">Yes</label>
    				</div>
    				<div class="radio">
      					<label><input type="radio" name="generic" value="no">No</label>
    				</div>
        		</td>
      			</tr>
    		</tbody>
  			</table>
	</div>
	<button type="submit" class="btn btn-primary btn-block">send</button>
</form>
	<br/>
	<br/>
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