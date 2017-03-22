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
</style>
</head>
<body>
<div class="jumbotron text-center">
  <h1>Extract Task Phrases</h1>
  <p>reduce your reading loads</p> 
</div>
<form action="finding" method="post">
	<table>
		<thead>
      			<tr>
        			<th><span class="label label-primary">Text:</span></th>
        			<th><span class="label label-primary">Results:</span></th>
      			</tr>
    	</thead>
    	<tbody>
		<tr>
			<td>
				<textarea name="text" rows="10" cols="100"
					onblur="if(this.value == ''){this.style.color = '#ACA899'; this.value = 'input sentences within 300 words'; }" 
    				onfocus="if(this.value == 'input sentences within 300 words'){this.value =''; this.style.color = '#000000'; }" 
                                style="color:#ACA899;">input sentences within 300 words</textarea>
    		</td> 
    		<td>
    			<% 
	@SuppressWarnings("unchecked")
	List<String> tasks = (List<String>)request.getAttribute("tasks");
	if(tasks != null){	
		String task = "";
		for(int i=0;i<tasks.size();i++){
			task = tasks.get(i);
	%>
			<%=task %><br>
		<% 	} 
		if(task == ""){
			%>
			<label>there is no tasks.</label>
		<%}
	 } %>
    		</td>                         
    	</tr>
    	</tbody>
    </table>
	<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><span class="label label-primary">programming</span></th>
        			<th><span class="label label-primary">generic</span></th>
        			<th><span class="label label-primary">customize</span></th>
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
        		<td>
        			<textarea class="form-control" name="text" rows="5"
        			onblur="if(this.value == ''){this.style.color = '#ACA899'; this.value = 'input verbs, divide by comma'; }" 
    				onfocus="if(this.value == 'input verbs, divide by comma'){this.value =''; this.style.color = '#000000'; }" 
                    style="color:#ACA899;">input verbs, divide by comma</textarea>
        		</td>
      			</tr>
    		</tbody>
  			</table>
	</div>
	<div class="position">
		<button type="submit" class="btn btn-primary">send</button>
	</div>
</form>	
</body>
</html>