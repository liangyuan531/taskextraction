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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>

<script src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" />

<title>main page</title>
</head>
<body>
<div class="jumbotron text-center" id="header">
  <h2>Extract Task Phrases</h2>
  <p>reduce your reading loads</p> 
</div>
<nav class="navbar navbar-inverse" id="header">
  <p class="navbar-text">Exetracting System</p>
  <ul class="nav navbar-nav">
    <li><a href="index.jsp" id="Maintab">Main</a></li>
    <li><a href="setting.jsp" id="Settingtab">Setting</a></li>
    <li><a href="login.jsp">Database</a></li>
  </ul>
  
</nav>
<form name="myForm" action="finding" method="post">
	<div style="margin-left:10px">
	<div class="row">
    <div class="col-sm-6" id="divsize">
    	<h4><span class="label label-primary">Text:</span></h4>
    </div>
    <div class="col-sm-4" id="divsize">
    	<h4><span class="label label-primary">Results:</span></h4>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6" id="divsize">  
    <textarea rows="10" id="text" name="text" class="comments" placeholder="input sentences within 60 words"></textarea>  
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
    <!-- <div class="col-sm-4" id="userinput" id="divsize"></div> -->
  </div>
  </div>
	<div class="position">
		<button type="submit" id="send" class="btn btn-primary">Search</button>
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
</body>
</html>