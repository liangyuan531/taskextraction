<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
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
		     //var UserInput="";
		     //UserInput += this.value;
		     //$("#userinput").html(UserInput);
		     var str = parseInt(60-finalCount);
		     if(str > 0 ){
			 	$('#numleft').html(str+' words left');
			 }else{
			 	$('#numleft').html('0 word left');
			 	//$("#text").val('');
			 	//$("#text").html("you are oversieze")
			 	//myfunction();
			 	var gettext=document.getElementById("text");
			 	//$("text").val("123456");
			 	var temp_string=new Array();
			 	temp_string=gettext.value.split(" ");
			 	var display="";
			 	//$("#text").val(temp_string);
			 	//$('#text').val(' ');
			 	for(var i=0;i< 59;i++){
			 		temp_string[i] = (temp_string[i]+" ");
			 		//$("#text").val(temp_string[i]);
			 		display += temp_string[i];
			 		//$("#text").val(display);
			 	}
			 	$("#text").val(display);
			 	/*val display="";
			 	for(var j=0;j < 60;j++){
			 		display += temp_string[j];
			 	}
			 	$("#text").val(display);*/
			 	//$("#text").val("you are oversize oversize oversize");
			 	$("#oversize").modal();
			 }
		 }).keyup();
		 var field=document.getElementById("text");
		 if(sessionStorage.getItem("text")){
			 $('#text').html(sessionStorage.getItem("text"));
		 }
		 text.addEventListener("change",function(){
			sessionStorage.setItem("text",field.value); 
		 });
		 //myfunction();
		 });
 /*$(function(){
	 $("text").html("you are over size");
 });*/
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
  	margin:0 auto;
    width:200px;
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
<div class="jumbotron text-center" style="width:100%">
  <h2>Extract Task Phrases</h2>
  <p>reduce your reading loads</p> 
</div>
<nav class="navbar navbar-inverse" style="width:100%">
  <p class="navbar-text">Exetracting System</p>
  <ul class="nav navbar-nav">
    <li><a href="index.jsp" id="Maintab">Main</a></li>
    <li><a href="setting.jsp" id="Settingtab">Setting</a></li>
  </ul>
  
</nav>
<form name="myForm" action="finding" method="post">
	<div style="margin-left:10px">
	<div class="row">
    <div class="col-sm-6" style="width:50%">
    	<h4><span class="label label-primary">Text:</span></h4>
    </div>
    <div class="col-sm-4" style="width:50%">
    	<h4><span class="label label-primary">Results:</span></h4>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6" style="width:50%">
    	input sentences within 60 words
    </div>
    <div class="col-sm-4" style="width:50%"></div>
  </div>
  <div class="row">
    <div class="col-sm-6" style="width:50%">  
    <textarea rows="10" id="text" name="text" class="comments"></textarea>  
	</div> 
    <div class="col-sm-4" style="width:50%">
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
    <div class="col-sm-6" style="width:50%">
    	<span id ="numleft">60 words left</span>
    </div>
    <div class="col-sm-4" id="userinput" style="width:50%"></div>
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
          <button type="button" id="OKbutton" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
</div>
</body>
</html>