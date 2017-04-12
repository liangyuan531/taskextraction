<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	/* $("#yesownverbs").html("this is yesownverbs"); */
	if(sessionStorage.getItem("noprogramming")=="no"){
		//$("#yesownverbs").html(sessionStorage.getItem("noprogramming"));
		$("#noprogramming").attr("checked",true);
		sessionStorage.removeItem("noprogramming");
	}
	if(sessionStorage.getItem("nogeneric")=="no"){
		//$("#yesownverbs").html(sessionStorage.getItem("noprogramming"));
		$("#nogeneric").attr("checked",true);
		sessionStorage.removeItem("nogeneric");
	}
	if(sessionStorage.getItem("yesselfprogramming")=="yes"){
		$("#yesownverbs").html("yes");
		$("#yesselfprogramming").attr("checked",true);
		sessionStorage.removeItem("yesselfprogramming");
	}
	if(sessionStorage.getItem("yesselfgeneric")=="yes"){
		$("#yesownverbs").html("yes");
		$("#yesselfgeneric").attr("checked",true);
		sessionStorage.removeItem("yesselfgeneric");
	}
	if(sessionStorage.getItem("yesownverbs")){
		$("#yesownverbs").html(sessionStorage.getItem("yesownverbs"));
	}
	if(sessionStorage.getItem("owngeneric")){
		$("#owngeneric").html(sessionStorage.getItem("owngeneric"));
	}
	
});
$(function(){
	$("#Maintab").click(function(){
		if($("#noprogramming").prop("checked")){
			sessionStorage.setItem("noprogramming",$("#noprogramming").val());
		}
		if($("#nogeneric").prop("checked")){
			sessionStorage.setItem("nogeneric",$("#nogeneric").val());
		}
		if($("#yesselfprogramming").prop("checked")){
			sessionStorage.setItem("yesselfprogramming",$("#yesselfprogramming").val());
		}
		if($("#yesselfgeneric").prop("checked")){
			sessionStorage.setItem("yesselfgeneric",$("#yesselfgeneric").val());
		}
		sessionStorage.setItem("yesownverbs",$("#yesownverbs").val());
		sessionStorage.setItem("owngeneric",$("#owngeneric").val());
	});
});
$(function(){
	$("#Settingtab").click(function(){
		if($("#noprogramming").prop("checked")){
			sessionStorage.setItem("noprogramming",$("#noprogramming").val());
		}
		if($("#nogeneric").prop("checked")){
			sessionStorage.setItem("nogeneric",$("#nogeneric").val());
		}
		if($("#yesselfprogramming").prop("checked")){
			sessionStorage.setItem("yesselfprogramming",$("#yesselfprogramming").val());
		}
		if($("#yesselfgeneric").prop("checked")){
			sessionStorage.setItem("yesselfgeneric",$("#yesselfgeneric").val());
		}
		sessionStorage.setItem("yesownverbs",$("#yesownverbs").val());
		sessionStorage.setItem("owngeneric",$("#owngeneric").val());
	});
});
/*
function reload(){
	if(sessionStorage.getItem("noprogramming")){
		//$("noprogramming").val('no');
		$("#yesownverbs").html("reloadnonprogramming");
	}
}*/
</script>
<link rel="stylesheet" href="css/main.css" />
<title>Setting</title>
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
  </ul>
</nav>
<form name="set" action="setting" method="get">
<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><h4><span class="label label-primary">Detect tasks that contain verbs from a pre-defined list?</span></h4></th>
        			<th><h4><span class="label label-primary">Use generic accusative?</span></h4></th>
        			<th><h4><span class="label label-primary">Grammatical dependencies</span></h4></th>
        			<th><h4><span class="label label-primary">Code</span></h4></th>
      			</tr>
    		</thead>
    		<tbody>
      			<tr>
        		<td>
        			<div class="radio">
      					<label><input type="radio" id="noprogramming" name="programming" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" id="yesprogramming" name="programming" value="yeswithdefined" checked="checked">Yes,use pre-defined verbs</label>
    					<a href="programlist">[see details]</a>
    				</div>
    				<div class="radio">
        				<label><input type="radio" id="yesselfprogramming" name="programming" value="yes">Yes,use own verbs</label>
        				<textarea class="comments" id="yesownverbs" name="verbs" rows="3" placeholder="input your verbs"></textarea>
    				</div>
       			</td>
        		<td>
        			<div class="radio">
      					<label><input type="radio" id="nogeneric" name="generic" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" id="yesgeneric" name="generic" value="yeswithdefined" checked="checked">Yes,use pre-defined</label>
      					<a href="genericlist">[see details]</a>
    				</div>
    				<div class="radio">
    					<label><input type="radio" id="yesselfgeneric" name="generic" value="yes">Yes,use own</label>
        				<textarea class="comments" id="owngeneric" name="selfgeneric" rows="3" placeholder="input your accusatives"></textarea>
    				</div>
        		</td>
        		<td>
        			<div class="checkbox">
      					<label><input type="checkbox" id="yesdirectobj" name="direct_object" value="yes" checked="checked">direct object</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yespassive"name="passive_nominal_subject" value="yes" checked="checked">passive nominal subject</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yesrelative" name="relative_clause_modifier" value="yes" checked="checked">relative clause modifier</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yesprepositional"name="prepositional_modifier" value="yes" checked="checked">prepositional modifier</label>
    				</div>
        		</td>
        		<td>
        			<div class="checkbox">
      					<label><input type="checkbox" id="yesregexed"name="RegexedCode" value="yes" checked="checked">RegexedCode</label>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yestagged"name="TaggedCode" value="yes" checked="checked">TaggedCode</label>
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