<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>

<script type="text/javascript" src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" />

<title>Task Phrase Extraction</title>
</head>
<body class="center">
<div>
<%--<h3>This is error: <%=request.getAttribute("error")%></p>--%>
<%! String test="";%>
<%--test=(String)request.getAttribute("error"); --%>
<% if(request.getAttribute("error") == null){ %>
	<%}else{ %>
		<script type="text/javascript"> window.onload = alertmemory; </script>
	<%} %>
</div>
<div id="dataload" style="display:none"><!--processing data-->
<table width=50% height=50% border=0 align=center valign=middle>
<tr>
<td>
<div class="loading">
<img src="<%=request.getContextPath()%>/loading.gif" style="width:200px;height:200px;"/>
</div>
</td>
</tr>
</table>
</div>
<div class="jumbotron text-center" id="header">
  <h2>Extract Task Phrases</h2>
  <p>from natural language sentences</p> 
</div>
<nav class="navbar navbar-inverse" id="header">
  <ul class="nav navbar-nav">
    <li><a href="index.jsp" id="Maintab">Home</a></li>
    <li><a href="setting.jsp" id="Settingtab">Settings</a></li>
    <li><a href="login.jsp" target="view_window">Admin</a></li>
    <li><a href="About.jsp" id="Abouttab">About</a></li>
  </ul>
</nav>
<form name="myForm" action="finding" method="post">
	<div style="margin-left:10px">
	<div class="row">
    <div class="col-sm-6" id="divsize">
    	<h4><span class="label label-primary">Input:</span></h4>
    </div>
    <div class="col-sm-4" id="divsize">
    	<h4><span class="label label-primary">Extracted Task Phrases:</span></h4>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6" id="divsize">  
    <textarea rows="10" id="text" name="text" class="comments" placeholder="Input natural language sentences (max. 60 words). Example: This sentence tests the extraction of task phrases."></textarea>  
	</div> 
    <div class="col-sm-4" id="divsize">
    <table>
    	<% 
				@SuppressWarnings("unchecked")
				List<String> tasks = (List<String>)request.getAttribute("tasks");
				if(tasks != null){	
					String task = "";
					for(int i=0;i<tasks.size();i++){
						task = tasks.get(i);%>
					<tr><td><%=(i+1)+"."+" "+ task%></td></tr>
				  <%}
					if(task == ""){%>
					<tr><td>there is no task.</td></tr>
					<%}
	 			}
	 			%>
	</table>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6" id="divsize">
    	<span id ="numleft">60 words left</span>
    </div>
  </div>
  </div>
	<div class="position">
		<button type="submit" id="send" class="btn btn-primary" style="width:200px;">Extract Task Phrases</button>
	</div>
</form>
	
<div class="modal fade" id="oversize" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red">Warning</h4>
        </div>
        <div class="modal-body">
          <p>Every time, you are ONLY allowed to input text within 60 words. </p>
          <p>Please re-input</p>
        </div>
        <div class="modal-footer">
          <button type="button" id="OKOverSize" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
</div>
<div class="modal fade" id="outofmemory" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title" style="color:red">Warning</h4>
        </div>
        <div class="modal-body">
            <p>out of memory. </p>
            <p>Please re-input</p>
        </div>
        <div class="modal-footer">
            <button type="button" id="OKOverSize" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
</div>
</body>
</html>
