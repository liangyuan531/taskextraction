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
<title>Task Phrase Extraction</title>
<%  
    if(session.getAttribute("userinfo") == null) {  
%>  
        <script type="text/javascript"> 
        	//$("#login").modal();
        	alert("Must login");
            window.document.location.href="login.jsp";  
        </script>   
<%  
    }else{
    	//session.removeAttribute("userinfo");
    	session.setMaxInactiveInterval(180);
    }
%> 
</head>
<body>
<table class="table table-striped">
<thead>
      <tr>
        <th>Time</th>
        <th>Country</th>
        <th width="50%">Results</th>
        <th>isNonprogramming</th>
        <th>isGenericAction</th>
        <th>PROGRAMMING_ACTIONS</th>
        <th>GENERIC_ACCUSATIVES</th>
        <th>GRAMMATICAL_DEPENDENCIES_and_CODE</th>
        <th>TEXT</th>
      </tr>
    </thead>
<%
	@SuppressWarnings("unchecked")
	List<String> data = (List<String>)session.getAttribute("data");
	if(data != null){
		for(int i=0;i<data.size();i+=9){%>
			<tr>
				<%-- <% for(int j=i;j<data.size();j++){%>
					<td><%=data.get(j) %></td>
				<% }%> --%>	
				<td><%=data.get(i) %></td>
				<td><%=data.get(i+1) %></td>
				<td width="50%"><%=data.get(i+2) %></td>
				<td width="10%"><%=data.get(i+3) %></td>
				<td width="10%"><%=data.get(i+4) %></td>
				<td><%=data.get(i+5) %></td>
				<td><%=data.get(i+6) %></td>
				<td><%=data.get(i+7) %></td>
				<td><%=data.get(i+8) %></td>
			</tr>
		<%}
		}%>
</table>
<div class="modal fade" id="login" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red">Warning</h4>
        </div>
        <div class="modal-body">
          <p>Must login</p>
        </div>
        <div class="modal-footer">
          <button type="button" id="OKbutton" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
  </div>
</body>
</html>