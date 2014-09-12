$(document).ready(function(){

	$.ajax({
		url:"json/people.json",
		type:"POST",
		dataType:"json"
	}).done(function(jsonObj){
		for(var i=0;i<jsonObj.length;i++){
			
			if(i%3==0){
			$("#personDetails table").append("<tr>");
			}
			$("#personDetails table tr").last().after(
									"<td>" 
									+ "<div id='person" + i + "' class='person' draggable='true'>	Name: " + jsonObj[i].people.name
									+ "<br/>Email: " + jsonObj[i].people.emailid
									+ "</div>"
									+ "</td>"
									);
			if(i%3==2){						
			$("#personDetails table tr td").last().append("</tr>");
			}
			
		}
		
		$(".person").click(function(){
			$(this).dialog({
				width:325,
				height:175,
				title:"Person Detail",
				modal:true,
				show:{
					effect:"puff",
					delay:200
				},
				hide:{
					effect:"puff",
					delay:200
				},
				close: function( event, ui ) {
					$(this).dialog( "destroy" );
				}
			});
		});
	
	
	}).fail(function(jXHR, errorStatus){
		alert(errorStatus);
	});
	
	$("#goToExpenseButton").click(function(){
		$("#content").load("expense.html");
	});
	
	$("#addPersonButton").click(function(){
		alert("create person from div");
	});
	
});