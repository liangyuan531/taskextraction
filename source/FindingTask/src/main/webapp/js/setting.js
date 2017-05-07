
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
		//$("#yesownverbs").html("yes");
		$("#yesselfprogramming").attr("checked",true);
		sessionStorage.removeItem("yesselfprogramming");
	}
	if(sessionStorage.getItem("yesselfgeneric")=="yes"){
		//$("#yesownverbs").html("yes");
		$("#yesselfgeneric").attr("checked",true);
		sessionStorage.removeItem("yesselfgeneric");
	}
	if(sessionStorage.getItem("yesdirectobj") == "yes"){
		$("#yesdirectobj").attr("checked",true);
		sessionStorage.removeItem("yesdirectobj");
	}
	if(sessionStorage.getItem("yesownverbs")){
		$("#yesownverbs").html(sessionStorage.getItem("yesownverbs"));
		sessionStorage.removeItem("yesownverbs");
	}
	if(sessionStorage.getItem("owngeneric")){
		$("#owngeneric").html(sessionStorage.getItem("owngeneric"));
		sessionStorage.removeItem("owngeneric");
	}
	if(sessionStorage.getItem("yesdirectobj") == -1){
		$("#yesdirectobj").attr("checked",false);
		sessionStorage.removeItem("yesdirectobj");
	}
	if(sessionStorage.getItem("yespassive") == -1){
		$("#yespassive").attr("checked",false);
		sessionStorage.removeItem("yespassive");
	}
	if(sessionStorage.getItem("yesrelative") == -1){
		$("#yesrelative").attr("checked",false);
		sessionStorage.removeItem("yesrelative");
	}
	if(sessionStorage.getItem("yesprepositional") == -1){
		$("#yesprepositional").attr("checked",false);
		sessionStorage.removeItem("yesprepositional");
	}
	if(sessionStorage.getItem("yesregexed") == -1 ){
		$("#yesregexed").attr("checked",false);
		sessionStorage.removeItem("yesregexed");
	}
	if(sessionStorage.getItem("yestagged") == -1 ){
		$("#yestagged").attr("checked",false);
		sessionStorage.removeItem("yestagged");
	}
	if(sessionStorage.getItem('alloptions') == null ){
		var group=[0,1,0,0,1,0,1,1,1,1,1,1];
		//$("#yesownverbs").val(group);
		sessionStorage.setItem("alloptions",group);
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
		if(!$("#yesdirectobj").prop("checked")){
			sessionStorage.setItem("yesdirectobj",-1);
		}
		if(!$("#yespassive").prop("checked")){
			sessionStorage.setItem("yespassive",-1);
		}
		if(!$("#yesrelative").prop("checked")){
			sessionStorage.setItem("yesrelative",-1);
		}
		if(!$("#yesprepositional").prop("checked")){
			sessionStorage.setItem("yesprepositional",-1);
		}
		if(!$("#yesregexed").prop("checked")){
			sessionStorage.setItem("yesregexed",-1);
		}
		if(!$("#yestagged").prop("checked")){
			sessionStorage.setItem("yestagged",-1);
		}
		sessionStorage.setItem("yesownverbs",$("#yesownverbs").val());
		sessionStorage.setItem("owngeneric",$("#owngeneric").val());
		var nogen=$("#nogeneric");
		var nopro=$("#noprogramming");
		var yespro=$("#yesprogramming");
		var yesgen=$("#yesgeneric");
		var yesselfpro=$("#yesselfprogramming");
		var yesselfgen=$("#yesselfgeneric");
		var yesdir=$("#yesdirectobj");
		var yespas=$("#yespassive");
		var yesrel=$("#yesrelative");
		var yespre=$("#yesprepositional");
		var yesreg=$("#yesregexed");
		var yestag=$("#yestagged");
		var temp=sessionStorage.getItem("alloptions");
		var selected=[0,0,0,0,0,0,0,0,0,0,0,0];
		if(nopro.is(':checked')){
			selected[0]=1;
		}else if(yespro.is(':checked')){
			selected[1]=1;
		}else if(yesselfpro.is(':checked')){
			selected[2]=1;
		}
		
		if(nogen.is(':checked')){
			selected[3]=1;
		}else if(yesgen.is(':checked')){
			selected[4]=1;
		}else if(yesselfgen.is(':checked')){
			selected[5]=1;
		}
		
		if(yesdir.is(':checked')){
			selected[6]=1;
		}
		if(yespas.is(':checked')){
			selected[7]=1;
		}
		if(yesrel.is(':checked')){
			selected[8]=1;
		}
		if(yespre.is(':checked')){
			selected[9]=1;
		}
		if(yesreg.is(':checked')){
			selected[10]=1;
		}
		if(yestag.is(':checked')){
			selected[11]=1;
		}
		var k=0;
		var change=0;
		for(var i=0;i<12;i++){
			if(temp[k] != selected[i]){
				change=1;
				console.log("difference at "+i);
				console.log("this is temp: "+temp[i]+"\n");
				console.log("this is selected "+selected[i]+"\n");
			}
			k=k+2;
		}
		if(change == 1){
			//$("#yesownverbs").val("this is selected "+selected+"\n"+"this is previous "+temp);
			$("#saveoptions").modal();
		}else if(change == 0){
			//$("#yesownverbs").val("this is change =0");
			sessionStorage.setItem("alloptions",selected);
			$(location).attr('href','index.jsp');
		}
		
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
		if(!$("#yesdirectobj").prop("checked")){
			sessionStorage.setItem("yesdirectobj",-1);
		}
		if(!$("#yespassive").prop("checked")){
			sessionStorage.setItem("yespassive",-1);
		}
		if(!$("#yesrelative").prop("checked")){
			sessionStorage.setItem("yesrelative",-1);
		}
		if(!$("#yesprepositional").prop("checked")){
			sessionStorage.setItem("yesprepositional",-1);
		}
		if(!$("#yesregexed").prop("checked")){
			sessionStorage.setItem("yesregexed",-1);
		}
		if(!$("#yestagged").prop("checked")){
			sessionStorage.setItem("yestagged",-1);
		}
		sessionStorage.setItem("yesownverbs",$("#yesownverbs").val());
		sessionStorage.setItem("owngeneric",$("#owngeneric").val());
	});
});
$(function(){
	$("#ok").click(function(){
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
		if(!$("#yesdirectobj").prop("checked")){
			sessionStorage.setItem("yesdirectobj",-1);
		}
		if(!$("#yespassive").prop("checked")){
			sessionStorage.setItem("yespassive",-1);
		}
		if(!$("#yesrelative").prop("checked")){
			sessionStorage.setItem("yesrelative",-1);
		}
		if(!$("#yesprepositional").prop("checked")){
			sessionStorage.setItem("yesprepositional",-1);
		}
		if(!$("#yesregexed").prop("checked")){
			sessionStorage.setItem("yesregexed",-1);
		}
		if(!$("#yestagged").prop("checked")){
			sessionStorage.setItem("yestagged",-1);
		}
		sessionStorage.setItem("yesownverbs",$("#yesownverbs").val());
		sessionStorage.setItem("owngeneric",$("#owngeneric").val());
		
		var nogen=$("#nogeneric");
		var nopro=$("#noprogramming");
		var yespro=$("#yesprogramming");
		var yesgen=$("#yesgeneric");
		var yesselfpro=$("#yesselfprogramming");
		var yesselfgen=$("#yesselfgeneric");
		var yesdir=$("#yesdirectobj");
		var yespas=$("#yespassive");
		var yesrel=$("#yesrelative");
		var yespre=$("#yesprepositional");
		var yesreg=$("#yesregexed");
		var yestag=$("#yestagged");
		
		var selected=[0,0,0,0,0,0,0,0,0,0,0,0];
		if(nopro.is(':checked')){
			selected[0]=1;
		}else if(yespro.is(':checked')){
			selected[1]=1;
		}else if(yesselfpro.is(':checked')){
			selected[2]=1;
		}
		
		if(nogen.is(':checked')){
			selected[3]=1;
		}else if(yesgen.is(':checked')){
			selected[4]=1;
		}else if(yesselfgen.is(':checked')){
			selected[5]=1;
		}
		
		if(yesdir.is(':checked')){
			selected[6]=1;
		}
		if(yespas.is(':checked')){
			selected[7]=1;
		}
		if(yesrel.is(':checked')){
			selected[8]=1;
		}
		if(yespre.is(':checked')){
			selected[9]=1;
		}
		if(yesreg.is(':checked')){
			selected[10]=1;
		}
		if(yestag.is(':checked')){
			selected[11]=1;
		}
		
		sessionStorage.setItem("alloptions",selected);
	});
});
$(document).ready(function(){
    $('[data-toggle="popover"]').popover(); 
});