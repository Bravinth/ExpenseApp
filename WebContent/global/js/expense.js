$(document).ready(function(){
	
	var jsonUserObj=null;
	if(sessionStorage!=null){//This check is made for browsers that doesn't support sessionStorage
		jsonUserObjString= sessionStorage.getItem("userInfo");
		jsonUserObj = JSON.parse(jsonUserObjString);
	}
	
	if(jsonUserObj != null){
		alert("if");
		loadExpenseData(jsonUserObj);
	}else{
		//alert("else");
		$.ajax({
			url:"./global/json/personList.json",
			type:"POST",
			dataType:"json"
		}).done(function(jsonObj){
			if(sessionStorage!=null){//This check is made for browsers that doesn't support sessionStorage
				sessionStorage.setItem("userInfo", JSON.stringify(jsonObj));
			}
			jsonUserObj = jsonObj;
			loadExpenseData(jsonObj);
	 
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});
		
	}
	

	function loadExpenseData(jsonObj){
		if(jsonObj != null){
			//Setting the header of the expense table
			var userTbody = "#users thead tr";
			for (var i=0;i<jsonObj.length;i++) {
				$(userTbody).append("<th>"+jsonObj[i].name+"</th>");
			}
			
			//Displaying the data in the expense table
			$.ajax({
				url:"./global/json/expense.json",
				type:"POST",
				dataType:"json"
			}).done(function(expenseObj){

				for (var i=0;i<expenseObj.length;i++) {
					$("#users tbody").append("<tr>"
							+ "<td>"+expenseObj[i].Item+"</td>"
							+ "<td  nowrap>"+expenseObj[i].Date+"</td>"
							+ "<td>"+expenseObj[i].Price+"</td>"
							+ "<td>"+expenseObj[i].PaidBy+"</td>");
					for (var j=0;j<jsonObj.length;j++) {
						//var name=jsonObj[j].name;
						var value = expenseObj[i].PaidFor[j].Share;//find out how to find the values
						$("#users tbody tr").last().append("<td>"+ value +"</td>");
					}
					$("#users tbody").append( "</tr>");
				}
			}).fail(function(jXHR, errorStatus){
					alert(errorStatus);
			});
			
		}
	}
	
	//delete if this function is not used
	function loadMemberData(){
		$.ajax({
			url:"./global/json/personList.json",
			type:"POST",
			dataType:"json"
		}).done(function(jsonObj){
			return jsonObj;
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});
	}
	
	$("#addExpenseButton").click(function(){
		$("#addExpenseDiv").dialog("open");
		for (var j=0;j<jsonUserObj.length;j++) {
			var name = jsonUserObj[j].name;
			$("#paidBy").append("<option value='" + name + "'>"+ name +"</option>");
		}
	});
	
	$("#addPersonButton").click(function(){
		$("#addMemberDiv").dialog("open");
	});
	
	$("#goToPeopleButton").click(function(){
		$("#content").load("people.html");
	});
		
});