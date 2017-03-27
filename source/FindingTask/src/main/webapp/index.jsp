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
<script type="text/javascript">
/* display words left */
 $(document).ready(function()
		 {
		 var wordCounts = {};
		 $("#text").keyup(function() {
		     var matches = this.value.match(/\b/g);
		     wordCounts[this.id] = matches ? matches.length / 2 : 0;
		     var finalCount = 0;
		     $.each(wordCounts, function(k, v) {
		         finalCount += v;
		     });
		     var str = parseInt(60-finalCount);
		     if(str > 0 ){
			 		$('#numleft').html(str+' words left');
			 	}else{
			 		$('#numleft').html('0 word left');
			 		$("#text").val('');
			 		$("#oversize").modal();
			 	}
		 }).keyup();
		 });

 $(function(){
     $("#send").click(function(){
        var val=$('input:radio[name="customize"]:checked').val();
        if(val=="yes"){
            var verbs = $("#verbs").val();
            /*  window.alert();*/
        	if(verbs==""){
        		$("#ownverbs").modal();
        	} 	
        }          
     });
 });
</script>
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
  <h2>Extract Task Phrases</h2>
  <p>reduce your reading loads</p> 
</div>
<form name="myForm" action="finding" method="post">
	<table>
		<thead>
      			<tr>
        			<th><h4><span class="label label-primary">Text:</span></h4></th>
        			<th><h4><span class="label label-primary">Results:</span></h4></th>
      			</tr>
    	</thead>
    	<tbody>
		<tr>
			<td>
				<textarea id="text" name="text" rows="10" cols="100"
					onblur="if(this.value == ''){this.style.color = '#ACA899'; this.value = 'input sentences within 60 words'; }" 
    				onfocus="if(this.value == 'input sentences within 60 words'){this.value =''; this.style.color = '#000000'; }" 
                                style="color:#ACA899;">input sentences within 60 words</textarea><br>                            
    		</td> 
    		<td>
    			<textarea rows="10" cols="100" readonly="readonly">
    			<% 
				@SuppressWarnings("unchecked")
				List<String> tasks = (List<String>)request.getAttribute("tasks");
				if(tasks != null){	
					String task = "";
					for(int i=0;i<tasks.size();i++){
						task = tasks.get(i);%>
					<%=task %>
				  <%}
					if(task == ""){%>
					there is no tasks.
					<%}
	 			}
	 			%>
	 			</textarea>
    		</td>                         
    	</tr>
    	<tr>
    		<td>
    			<span id ="numleft">60 left</span>
    		</td>
    	</tr>
    	</tbody>
    </table>
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
		<button type="submit" id="send" class="btn btn-primary">send</button>
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
          <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
</div>
<div class="modal fade" id="ownverbs" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red">Warning</h4>
        </div>
        <div class="modal-body">
          <p>If you choose 'yes' for customization</p>
          <p>you have to input your verbs</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
</div>
</body>
</html>