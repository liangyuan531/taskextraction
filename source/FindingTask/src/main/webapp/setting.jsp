<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/setting.js"></script>
<script>
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
    <li><a href="" id="Maintab" data-toggle="modal" data-target="#saveoptions">Main</a></li>
    <li><a href="setting.jsp" id="Settingtab">Setting</a></li>
  </ul>
</nav>
<form name="set" action="setting" method="get">
<div class="container">           
  		<table class="table">
    		<thead>
      			<tr>
        			<th><h4><span class="label label-primary">Use pre-defined list?</span></h4></th>
        			<th><h4><span class="label label-primary">Use pre-defined generic accusative list?</span></h4></th>
        			<th><h4><span class="label label-primary">Grammatical dependencies</span></h4></th>
        			<th><h4><span class="label label-primary">Code</span></h4></th>
      			</tr>
    		</thead>
    		<tbody>
      			<tr>
        		<td width="30%">
        			<div class="radio">
      					<label><input type="radio" id="noprogramming" name="programming" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" id="yesprogramming" name="programming" value="yeswithdefined" checked="checked">Yes,use pre-defined verbs</label>
    					<a href="programlist" target="view_window">[see details]</a>
    				</div>
    				<div class="radio">
        				<label><input type="radio" id="yesselfprogramming" name="programming" value="yes">Yes,use own verbs</label>
        				<textarea class="comments" id="yesownverbs" name="verbs" rows="3" placeholder="input your verbs, divided by comma"></textarea>
    				</div>
       			</td>
        		<td width="30%">
        			<div class="radio">
      					<label><input type="radio" id="nogeneric" name="generic" value="no">No</label>
    				</div>
        			<div class="radio">
      					<label><input type="radio" id="yesgeneric" name="generic" value="yeswithdefined" checked="checked">Yes,use pre-defined</label>
      					<a href="genericlist" target="view_window">[see details]</a>
    				</div>
    				<div class="radio">
    					<label><input type="radio" id="yesselfgeneric" name="generic" value="yes">Yes,use own</label>
        				<textarea class="comments" id="owngeneric" name="selfgeneric" rows="3" placeholder="input your accusatives, divided by comma"></textarea>
    				</div>
        		</td>
        		<td width="30%">
        			<div class="checkbox">
      					<label><input type="checkbox" id="yesdirectobj" name="direct_object" value="yes" checked="checked">direct object</label>
      					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="eg.creates a task"/>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yespassive"name="passive_nominal_subject" value="yes" checked="checked">passive nominal subject</label>
    					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="eg.task is created"/>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yesrelative" name="relative_clause_modifier" value="yes" checked="checked">relative clause modifier</label>
    					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="eg.create from text"/>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yesprepositional"name="prepositional_modifier" value="yes" checked="checked">prepositional modifier</label>
    					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="eg.text inCludes camel case"/>
    				</div>
        		</td>
        		<td >
        			<div class="checkbox">
      					<label><input type="checkbox" id="yesregexed"name="RegexedCode" value="yes" checked="checked">RegexedCode</label>
      					<a href="regularexpression.html" target="view_window">[see details]</a>
      					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="regular expression"/>
    				</div>
    				<div class="checkbox">
      					<label><input type="checkbox" id="yestagged"name="TaggedCode" value="yes" checked="checked">TaggedCode</label>
      					<img src="explain.png" height="15px" width="15px" data-toggle="popover" data-trigger="hover" data-content="<tt></tt>"/>
    				</div>
        		</td>
      			</tr>
    		</tbody>
  			</table>
	</div>
	<div class="position">
		<button type="submit" id="ok" class="btn btn-primary">SAVE</button>
	</div>
</form>
<div class="modal fade" id="saveoptions" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red">Warning</h4>
        </div>
        <div class="modal-body">
          <p>you did not save your options</p>
        </div>
        <div class="modal-footer">
          <button type="button" id="OKbutton" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div> 
    </div>
</div>	
</body>
</html>