$(document).ready(function(){

	$("#loggedInUserName").html(localStorage.getItem("loggedInUserName"));
	
	//Start : Loading all the expense sheets when the page loads
	$.ajax({
		url:"../getAllSheets.do",
		type:"GET"
		
	}).done(function(data){
		if(data.length == 0){
			$("#noRecord").show();
		}else{
			$("#expense-table").show();
			$.each(data, function(key, value){
				console.log(value);
				$("#expense-table tbody").append("<tr onclick='' id='"+ value.sheetId +"'><td>" + value.sheetName + "</td><td>" + value.sheetCreatedByUser.firstName + "</td><td>" + value.sheetCreatedDate + "</td><td><a class='btn btn-danger del-button' id='" + value.sheetId + "'>Delete</a></td></tr>");
			});
		}
	}).fail(function(){
		alert("Something went wrong");
	});
	//End : Loading all the expense sheets when the page loads
	
		
	//Start : Delete a Expense Sheet
	$(document).on("click", ".del-button", function(e){
		e.stopPropagation();	//This is added to stop the click event on table row
		deleteSheet($(this).attr("id"));
	});

	function deleteSheet(sheetId){
		
		$.ajax({	
			url:"../deleteSheet.do",
			type:"GET",
			data:"sheetId=" +sheetId,
		}).done(function(data){
			
			//$("#"+sheetId).hide("500", function(){
				$("#"+sheetId).remove();
			//});
			
			var dataRow = $("#expense-table tbody").find("tr");
			if(dataRow.length == 0){
				$("#expense-table").hide();
				$("#noRecord").show();
			}
			
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});
	}
	//End : Delete a Expense Sheet
	
	//Start : Add a Expense sheet
	$(document).on("click", "#addSheetButton", function(){
		
		var newSheetName = $("#newSheetName").val().trim();
		if(newSheetName != ""){
			addSheet(newSheetName);
			$("#addSheetForm")[0].reset();
			$("#addsheetmodal").modal("hide");
		}else{
			$("#addSheetForm")[0].reset();
			alert("Please enter the sheet name.");
		}
		
	});
	
	function addSheet(sheetName){
		
		$.ajax({
			url:"../addsheet.do",
			type:"POST",
			data:"sheetName=" + sheetName,
		}).done(function(data){
			
			var dataRow = $("#expense-table tbody").find("tr");
			if(dataRow.length == 0){
				$("#expense-table").show();
				$("#noRecord").hide();
			}
			
			$("#expense-table tbody").append("<tr id='"+ data.sheetId +"'><td>" + data.sheetName + "</td><td>" + data.sheetCreatedByUser.firstName + "</td><td>" + data.sheetCreatedDate + "</td><td><a class='btn btn-danger del-button' id='" + data.sheetId + "'>Delete</a></td></tr>");
			
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});

	}
	//End : Add a Expense Sheet

	//Start : Click on a sheet to edit
	$(document).on("click", "#expense-table tbody tr", function(e){
		localStorage.setItem("sheetId", $(this).attr("id"));
		localStorage.setItem("sheetName", $(this).find("td").html());
		window.location.href="../selected_sheet/index.jsp";
		
	});
	//End : Click on a sheet to edit


	
});