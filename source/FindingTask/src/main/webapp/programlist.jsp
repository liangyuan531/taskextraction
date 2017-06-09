<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Task Phrase Extraction</title>
</head>
<body>
<div style="margin-left:20px">
<table>
	<%String[] verbs = (String[])session.getAttribute("programminglist");%>
	<tr>
	<td>
	<%for(int i=0;i<verbs.length/2;i++){%>
		<%=verbs[i] + " "%><br>
	<%}%>
	</td>
	<td>
	<%for(int i=verbs.length/2;i<verbs.length;i++){%>
		<%=verbs[i] + " "%><br>
	<%}%>
	</td>
	</tr>
</table>
</div>
</body>
</html>