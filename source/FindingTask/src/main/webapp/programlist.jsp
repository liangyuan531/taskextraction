<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>programming verbs</title>
</head>
<body>
<nav class="navbar navbar-inverse" style="width:100%">
  <ul class="nav navbar-nav">
    <li><a href="setting.jsp">back</a></li>
  </ul>
</nav>
<%
	String[] verbs = (String[])session.getAttribute("programminglist");
	for(int i=0;i<verbs.length;i++){%>
		<%=verbs[i]%><br/>
	<%}
%>
</body>
</html>