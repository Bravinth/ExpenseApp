$(document).ready(function(){
	
	$('#createSheetDiv').dialog({
		autoOpen:false,
		draggable:false,
		resizable:false,
		modal:true,
		title:"Create a new sheet",
		show: {
	        effect: "blind",
	        duration: 500
	      },
		buttons:{
			"Create Sheet":function(){
				addSheet();
				$(this).dialog("close");
			}
		}
		
	});
	
	$('#createSheetLink').on({
		click: function(){
					$('#createSheetDiv').dialog("open");
				}
		
	});
	
	function addSheet(){
		
		$.ajax({
			url:"addsheet.do",
			type:"POST",
			data:"sheetName=" + $('#sheetName').val(),
		}).done(function(data){
			
			
			var sheetName = $('#sheetName').val();
			var successMsg = "Sheet "+sheetName+ " got added successfully";
			var listElem = document.createElement('li');
			$("#sheetList").append(listElem);
			$(listElem).html(sheetName + "<a href=\"#\" class=\"right\">Delete</a>").addClass("sheetLink").attr("value",data).attr("id",data).hide().show("clip", 500);
			
			var newDiv = document.createElement('div');

			$(newDiv).html(successMsg).addClass("modalMsg").dialog({
				modal:true,
				position:"top",
				height:"100",
				title:"Sheet Added",
				hide:{
			        effect: "fade",
			        duration: 4000 
			      },
				}).animate({
					color:"green",
					backgroundColor:"aqua"
				});
			$(newDiv).dialog("close", 1000, callback);
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});

	}
	
	function callback(){
		alert("elai");
		//$('div').dialog("close");
	}
	
	
	$("#addMemberDiv").dialog({
		autoFocus:false,
		autoOpen:false,
		draggable:false,
		resizable:false,
		title:"Member Detail",
		modal:true,
		width:325,
		buttons: {
			Add:function(){
				addMember();
				$(this).dialog("close");
			},
			Cancel:function(){
				$(this).dialog("close");
			}
		}
	});
	
	$("#addPersonButton").click(function(){
		$("#addMemberDiv").dialog("open");
	});
	
	function addMember(){
		alert("hi");
		$.ajax({
			url:"adduser.do",
			type:"POST",
			data:"emailId=" + $('#emailId').val() + "&nickName=" + $('#nickName').val(),
		}).done(function(data){
			var newDiv = document.createElement('div');

			$(newDiv).html(data).addClass("modalMsg").dialog({
				modal:true,
				title:"User Added",
				hide:{
			        effect: "fade",
			        duration: 4000 
			      },
				}).animate({
					color:"green",
					backgroundColor:"aqua"
				});
			$(newDiv).dialog("close", 2000, callback);
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});
	}
	
	$("li .right").live({
		click : function(){
			var sheetId = $(this).parent().val();
			deleteSheet(sheetId);
		}
	});
	
	$("li.sheetLink").live({
		click : function(){
			alert("form getting submitted");
			var sheetId = $(this).val();
			$("#eligibleSheetsForm").attr("action", "editSheet.do?sheetId="+sheetId).attr("method", "POST").submit();		
		}
	});
	
	function deleteSheet(sheetId){
		$.ajax({	
			url:"deleteSheet.do",
			type:"GET",
			data:"sheetId=" +sheetId,
		}).done(function(data){
			
			$("#"+sheetId).remove().hide("explode", 2000);
			
			var newDiv = document.createElement('div');
			$(newDiv).html(data).addClass("modalMsg").dialog({
				modal:true,
				position:"top",
				height:"100",
				title:"Sheet Removed",
				hide:{
			        effect: "fade",
			        duration: 3000 
			      },
				}).animate({
					color:"green",
					backgroundColor:"aqua"
				});
			$(newDiv).dialog("close", 2000, callback);
			
		}).fail(function(jXHR, errorStatus){
			alert(errorStatus);
		});
	}
	
	
	$(document).on("change", ".colorpicker", function(){
		alert($(this).parent().get(0));
		$(this).parent().css("background", $(this).val());
		
	});
	
});