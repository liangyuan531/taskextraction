<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>generic accusative</title>
</head>
<body>
<nav class="navbar navbar-inverse" id="header">
  <ul class="nav navbar-nav">
    <li><a href="setting.jsp">back</a></li>
  </ul>
</nav>
<div style="margin-left:20px">
<table>
	<%String[] generic = (String[])session.getAttribute("genericlist");%>
	<tr>
	<td>
	<%for(int i=0;i<generic.length/2;i++){%>
		<%=generic[i]+ " "%><br>
	<%}%>
	</td>
	<td>
	<%for(int i=generic.length/2;i<generic.length;i++){%>
		<%=generic[i]+ " "%><br>
	<%}%>
	</td>
	</tr>
</table>
</div>
</body>
</html>