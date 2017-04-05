<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Setting</title>
<style>
  .jumbotron {
  	background-color: #007991;
  	color: #fff;
  }
  .btn {
  	width:120px;
  	height:40px;
  }
  .position{ 
  	position:relative;  
  	left:600px;
  }
  .comments {  
    width: 100%;
    overflow: auto;  
    word-break: break-all;  
    resize:none;
   }
</style>
</head>
<body>
<div class="jumbotron text-center">
  <h2>Extract Task Phrases</h2>
  <p>reduce your reading loads</p> 
</div>
<nav class="navbar navbar-inverse">
  <p class="navbar-text">Exetracting System</p>
  <ul class="nav navbar-nav">
    <li><a href="index.jsp" id="Maintab">Main</a></li>
    <li><a href="setting.jsp" id="Settingtab">Setting</a></li>
  </ul>
</nav>
<form name="set" action="setting" method="get">
<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><h4><span class="label label-primary">Detect tasks that contain verbs from a pre-defined list?</span></h4></th>
        			<th><h4><span class="label label-primary">Use generic actions?</span></h4></th>
        			<th><h4><span class="label label-primary">Grammatical dependencies</span></h4></th>
        			<th><h4><span class="label label-primary">Code</span></h4></th>
      			</tr>
    		</thead>
    		<tbody>
      			<tr>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="programming" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" name="programming" value="yeswithdefined" checked="checked">Yes,use pre-defined verbs</label>
    				</div>
    				<div class="radio">
        				<label><input type="radio" name="programming" value="yes">Yes,use own verbs</label>
        				<textarea class="comments" id="verbs" name="verbs" rows="3"></textarea>
    				</div>
       			</td>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="generic" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" name="generic" value="yeswithdefined" checked="checked">Yes,use pre-defined</label>
    				</div>
    				<div class="radio">
    					<label><input type="radio" name="generic" value="yes">Yes,use own</label>
        				<textarea class="comments" id="selfgeneric" name="selfgeneric" rows="3"></textarea>
    				</div>
        		</td>
        		<td>
        			<div class="checkbox">
      					<label><input type="checkbox" name="DO" value="direct_object">direct object</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" name="PNS" value="passive_nominal_subject">passive nominal subject</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" name="RCM" value="relative_clause_modifier">relative clause modifier</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" name="PM" value="prepositional_modifier">prepositional modifier</label>
    				</div>
        		</td>
        		<td>
        			<div class="checkbox">
      					<label><input type="checkbox" name="RC" value="RegexedCode">RegexedCode</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" name="TC" value="TaggedCode">TaggedCode</label>
    				</div>
        		</td>
      			</tr>
    		</tbody>
  			</table>
	</div>
	<div class="position">
		<button type="submit" id="ok" class="btn btn-primary">OK</button>
	</div>
</form>	
</body>
</html>