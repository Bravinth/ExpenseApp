$(document).ready(function(){
	
	$("#content").load("./expensesheet/expensedetail.jsp");
	
	$("#addExpenseDiv").dialog({
		autoFocus:false,
		autoOpen:false,
		draggable:false,
		resizable:true,
		title:"Expense Detail",
		modal:true,
		width:400,
		buttons: {
			Add:function(){
				addExpenseToTable();
				$(this).dialog("close");
			},
			Cancel:function(){
				$(this).dialog("close");
			}
		}
	});
	
	$("#addMemberDiv").dialog({
		autoFocus:false,
		autoOpen:false,
		draggable:false,
		resizable:true,
		title:"Member Detail",
		modal:true,
		width:400,
		buttons: {
			Add:function(){
				
				$(this).dialog("destroy");
			},
			Cancel:function(){
				$(this).dialog("destroy");
			}
		}
	});
	
	function addExpenseToTable(){
		var item=$("#item").val();
		var date=$("#date").val();
		var price=$("#price").val();	
		var paidBy=$("#paidBy").val();
		$("#users>tbody").append("<tr>" + "<td>" + item + "</td>"
										+ "<td>" + date + "</td>"			
										+ "<td>" + price + "</td>"
										+ "<td>" + paidBy + "</td>"	+ "</tr>");
	}
	
	$("#date").datepicker({
		dateFormat: "d M, yy, D",
		showAnim: "drop"
	});

	$("#goToExpenseButton").click(function(){
		$("#content").load("expense.html");
	});
	
	$("#addPersonButton").click(function(){
		alert("create person from div");
	});
	
});