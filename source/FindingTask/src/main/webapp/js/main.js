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
		 //text.addEventListener("change",function(){
		//	sessionStorage.setItem("text",field.value); 
		// });
		 //myfunction();
		 //myfunction();
		 //myfunction();
		 });
//keep text
$(function(){
     $("#send").click(function(){
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

