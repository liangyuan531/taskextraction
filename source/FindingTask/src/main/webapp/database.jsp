<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Database</title>
</head>
<body>
<%
	@SuppressWarnings("unchecked")
	List<String> data = (List<String>)session.getAttribute("data");
	if(data != null){
		for(int i=0;i<data.size();i++){
			String str = data.get(i);%>
				<div class="well">
				<%= str%>
				</div>
		<%}
	}
%>
</body>
</html>