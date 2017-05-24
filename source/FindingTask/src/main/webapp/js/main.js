//word count
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
			 	var gettext=document.getElementById("text");
			 	var temp_string=new Array();
			 	temp_string=gettext.value.split(" ");
			 	var display="";
			 	for(var i=0;i< 59;i++){
			 		temp_string[i] = (temp_string[i]+" ");
			 		display += temp_string[i];
			 	}
			 	$("#text").val(display);
			 	$("#oversize").modal();
			 }
		 }).keyup();
		 var field=document.getElementById("text");
		 if(sessionStorage.getItem("text")){
			 $('#text').html(sessionStorage.getItem("text"));
		 }
		 });
//keep text
$(function(){
     $("#send").click(function(){
    	 $("#dataload").show();
    	sessionStorage.setItem("text",$("#text").val());
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
function alertmemory(){
	alert("Invalid input, please re-input");
}

