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
</style>
</head>
<body>
<div class="jumbotron text-center">
  <h2>Extract Task Phrases</h2>
  <p>reduce your reading loads</p> 
</div>
<form name="set" action="setting" method="get">
<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><h4><span class="label label-primary">Programming</span></h4></th>
        			<th><h4><span class="label label-primary">Generic</span></h4></th>
        			<th><h4><span class="label label-primary">Customize</span></h4></th>
      			</tr>
    		</thead>
    		<tbody>
      			<tr>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="programming" value="yes" checked="checked">Yes</label>
    				</div>
    				<div class="radio">
      					<label><input type="radio" name="programming" value="no">No</label>
    				</div>
       			</td>
        		<td>
        			<div class="radio">
      					<label><input type="radio" name="generic" value="yes" checked="checked">Yes</label>
    				</div>
    				<div class="radio">
      					<label><input type="radio" name="generic" value="no">No</label>
    				</div>
        		</td>
        		<td>
        			<label>using your verb?(input verbs, divide by comma)</label><br>
        			<input type="radio" name="customize" value="yes">Yes
        			<input type="radio" name="customize" value="no" checked="checked">No
        			<textarea class="form-control" id="verbs" name="verbs" rows="3"></textarea>
        			<!-- <textarea class="form-control" id="verbs" name="verbs" rows="3"
        			onblur="if(this.value == ''){this.style.color = '#ACA899'; this.value = 'input verbs, divide by comma'; }" 
    				onfocus="if(this.value == 'input verbs, divide by comma'){this.value =''; this.style.color = '#000000'; }" 
                    style="color:#ACA899;">input verbs, divide by comma</textarea> -->
        		</td>
      			</tr>
    		</tbody>
  			</table>
	</div>
	<div class="position">
		<button type="submit" id="send" class="btn btn-primary">OK</button>
	</div>
</form>	
</body>
</html>