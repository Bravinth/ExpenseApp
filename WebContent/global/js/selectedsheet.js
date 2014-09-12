$(document).ready(function(){
	
	var share_sharedByUserId="";
	var row_itemId="";
	$("#loggedInUserName").html(localStorage.getItem("loggedInUserName"));
	
	var sheetId = localStorage.getItem("sheetId");
	$("#expenseTitleDisplay").html(localStorage.getItem("sheetName"));
	$("#expenseTitleDisplay1").html(localStorage.getItem("sheetName"));
	
	var source = $("#userOptions").html();
	var template = Handlebars.compile(source);
	
	//Start : Display the items on the page when the page loads
	var userArray = new Array();
	
	$.ajax({
		url:"../getUsersInSheet.do",
		type:"GET",
		data:"sheetId=" + sheetId
	}).done(function(data){

		localStorage.setItem("sheetUsersList", JSON.stringify(data));
		var userOptions = template(data);
		$("#itempaidby").append(userOptions);
		$("#itemsharedby").append(userOptions);
		$("#itemsharedby").multipleSelect();
		$("#itemsharedby").multipleSelect("checkAll");
		$(".ms-parent").css({"width": "100%"});
		
		$.each(data, function(key, value){
			$("#itemTable thead tr").append("<th class='align_right'>" + value.firstName + "</th>");
			$("#itemTable tfoot tr").append("<td class='align_right'><span class='total_"+ value.id +"'></span><sup class='share_sup'>&nbsp;&nbsp;</sup> </td>");
			$("#overpaidDetails").append("<div class='row' id='balance_" + value.id + "'><div class='col-xs-1 col-sm-1 col-md-1 col-lg-1'></div><div class='col-xs-6 col-sm-4 col-md-5 col-lg-5'>" + value.firstName + "</div><div class='balanceAmt'>0.00</div></div>");			
			userArray.push(value.id);
		});	
		
			
		$.ajax({
			url:"../getSelectedSheetItems.do",
			type:"GET",
			data:{"sheetId" : sheetId}
		}).done(function(data){
			
			if(data.length == 0){
				$("#noRecord").show();
			}else{
				$("#itemTable").show();
				$("#balancesBox").show();
				
				$.each(data, function(key, value){
					insertItemInTable(value, null);
				});
				
				calculateAllTotals();
				calculateBalances();
			}
		});
				
	});
	
	function insertItemInTable(row, pos){
		
		var totalShares = (row.totalShares) ? row.totalShares : 1;
		var splitAmt = row.itemPrice / totalShares;
		var rowData = "<tr data-item-id='"+ row.itemId+"'><td><span class='bold deleteIcon' onclick=''>x</span></td><td onclick='' class='itemname'>" + row.itemDescription 
										+ "</td><td onclick='' class='itemdate'>" + row.itemPaidDate 
										+ "</td><td  onclick='' class='paidby' data-paidby-userid='" + row.itemPaidBy.id + "'>" + row.itemPaidBy.firstName
										+ "</td><td onclick='' data-sharedby-price=total class='itemprice align_right'><span>" + row.itemPrice.toFixed(2) 
										+ "</span></td></tr>";
		if(pos == null){
			$("#itemTable tbody").append(rowData);
		}else{
			$(pos).before(rowData);
		}
		
		for(var i=0; i<userArray.length; i++){
			var share = row["itemShareUserMap"][userArray[i]];
			share = (share) ? share : 0;
			var amt = share * splitAmt;
			
			rowData = "<td onclick='' class='sharedby align_right' data-sharedby-userid='" + userArray[i] + "'><span>" + amt.toFixed(2)  + "</span><sup class='share_sup'><a href='#' class='share_link' title='Edit share Info'>"+ share +"</a></sup></td>";
			if(pos == null){
				$("#itemTable tbody tr:last").append(rowData);
			}else{
				$(pos).prev().append(rowData);
			}
		}
	}
	//End : Display the items on the page when the page loads
	
	
	//Start : Add a new item when the add item button is clicked
	$(document).on("click", "#addItemButton", function(){
		//validate code has to be written
		//alert($("#itemsharedby").val());
		$("#addexpenseitemmodal").modal('hide');
		//return;
		
		var item = {
				sheetId: localStorage.getItem("sheetId"),
				itemDescription: $("#itemname").val(),
				itemPrice: $("#itemprice").val(),
				itemPaidDate:$("#itemdate").val(),
				itemPaidBy: { id:$("#itempaidby").val()	},
				itemCreatedBy: { id:localStorage.getItem("loggedInUserId") },
				itemShareUserMap:{}
			};

		var sharedArray = $("#itemsharedby").val();
		for(var i=0; i<sharedArray.length; i++){
			item["itemShareUserMap"][sharedArray[i]] = 1;
		}
		$("#addItemForm")[0].reset();
		
		$.ajax({
			url:"../addItem.do",
			type:"POST",
			contentType: "application/json; charset=utf-8",
	        dataType: "json",
			data:JSON.stringify(item)
		}).done(function(data){

			var dataRow = $("#itemTable tbody").find("tr");
			if(dataRow.length == 0){
				$("#itemTable").show();
				$("#balancesBox").show();
				$("#noRecord").hide();
			}
			
			insertItemInTable(data, null);
			calculateAllTotals();
			calculateBalances();
		}).fail(function(error){
			alert("Something went wrong");
		});
	});
	//End : Add a new item when the add item button is clicked
	
	//Start : Add Jquery UI date to the paid date field in 
	$("#itemdate").on('focus', function(){
		$(this).attr('readonly', true);
		$(this).datepicker({
			autoclose: true,
			format: "M dd, yyyy"
		}).datepicker('show');
	});
	$("#itemdate").on('blur', function(){
		$(this).attr('readonly', false);
	});
	
	//End : Add Jquery UI date to the paid date field
	
	
	//Start Edit and save every value in the cell
	var editMode = false;
	var oldValue = false;
	
	$(document).on("click", "#itemTable td.itemname",function(){
		editMode = true;
		closeSharePopUp();
		oldValue = $(this).html();
		$(this).html("<input type='text' class='form-control' value='"+ oldValue +"'></input>");
		$(this).children().first().focus();
	} );
	
	$(document).on("click", "#itemTable td.itemdate",function(){
		editMode = true;
		closeSharePopUp();
		oldValue = $(this).html();
		$(this).html("<input type='text' readonly='true' class='form-control' value='"+ oldValue +"'></input>");
		$(this).children().first().focus();
		$(this).children().first().datepicker({autoclose:true,	format: "M dd, yyyy",}).datepicker('show').on("hide", function(e){saveEditedInfo(this, "../editItemDate.do");});
	} );

	$(document).on("click", "#itemTable td.itemprice",function(){
		editMode = true;
		closeSharePopUp();
		oldValue = $(this).find('span').html();
		$(this).html("<input type='number' class='form-control' value='"+ oldValue +"'></input>");
		$(this).children().first().focus();
	} );
	
	$(document).on("click", "#itemTable td.paidby",function(){
		editMode = true;
		closeSharePopUp();
		oldValue = $(this).html();
		var userList = localStorage.getItem("sheetUsersList");
		$(this).html("<select id='paidByDropDown' class='form-control'>"+ template(JSON.parse(userList)) +"</select>");
		$("#paidByDropDown").find('[value='+ $(this).attr('data-paidby-userid') +']').attr("selected", "selected");
		$(this).children().first().focus();
	} );
	
	
	$(document).on("click", "#itemTable td input, #itemTable td select",function(e){
		e.stopPropagation();	
	} );
	
	$(document).on("blur", "#itemTable td.itemname input", function(e){saveEditedInfo(this, "../editItemName.do");});
	
	$(document).on("blur", "#itemTable td.itemprice input",function(e){
		
		editMode = false;
		var newValue = $(this).val().trim();
		newValue = parseFloat(newValue).toFixed(2);
		row_itemId = $(this).parent().parent().data("itemId");
		if(oldValue == newValue){
			$(this).parent().html(oldValue);
		}else{
			$.ajax({
				context:this,
				url:"../editItemPrice.do",
				type:"POST",
				data:{
					sheetId:sheetId,
					itemId:row_itemId,
					modifiedValue:newValue
					}
			}).done(function(data){
				refreshItem(data);
				calculateAllTotals();
				calculateBalances();
			}).fail(function(error){
				alert("Something went wrong");
				$(this).parent().html(oldValue);
			});
			
		}
	});
	
	function saveEditedInfo(elem, url){
		editMode = false;
		var newValue = $(elem).val().trim();
		if(oldValue == newValue){
			$(elem).parent().html(newValue);
		}else{
			$.ajax({
				url:url,
				type:"POST",
				data:{
					sheetId:sheetId,
					itemId:$(elem).parent().parent().data("itemId"),
					modifiedValue:newValue
					}
			}).done(function(data){
				$(elem).parent().html(newValue);
			}).fail(function(error){
				alert("Something went wrong");
				$(elem).parent().html(oldValue);
			});
			
		}
	}
	
	
	$(document).on("change", "#itemTable td select",function(e){
		
		editMode = false;
		var newValue = $(this).find(":selected").data("display");
		var newUserId = $(this).find(":selected").val();
		if(oldValue == newValue){
			$(this).parent().html(oldValue);
		}else{
			$.ajax({
				context:this,
				url:"../editItemPaidBy.do",
				type:"POST",
				data:{
					sheetId:sheetId,
					itemId:$(this).parent().parent().data("itemId"),
					modifiedValue:newUserId
					}
			}).done(function(data){				
				$(this).parent().attr("data-paidby-userid", newUserId);
				$(this).parent().html(newValue);
				calculateBalances();
			}).fail(function(error){
				alert("Something went wrong");
				$(this).parent().html(oldValue);
			});
		}
		
	} );
	
	$(document).on("blur", "#itemTable td select",function(e){
		$(this).parent().html(oldValue); //this clicked outside
	});
	
	
	function closeSharePopUp(){
		if($(".sharePopUp").length > 0){
			$(".sharePopUp").remove();
		}
	}
	
	$(document).on('click', ".share_link", function(e){
		e.preventDefault();
		closeSharePopUp();
		oldValue=$(this).html();
		share_sharedByUserId=$(this).parent().parent().data("sharedby-userid");
		row_itemId=$(this).parent().parent().parent().data("itemId");
			
		var divElem = document.createElement('div');
		$(divElem).addClass("sharePopUp").html("<div class='sharePopUpTitle'>Edit Share</div><input type='number' class='sharePopUpInput' value='"+ oldValue +"'></input><span class='smallButtonGreen' onclick=''>&#10004;</span><span class='smallButtonRed' onclick=''>&#10008;</span>");
		$("body").append(divElem);
		positionPopUp();
		
	});
	
	function positionPopUp(){
		
		var sourceElem = $("#itemTable").find("[data-item-id=" + row_itemId + "]").find("[data-sharedby-userid="+ share_sharedByUserId +"]").find(".share_link");
		var pos = $(sourceElem).offset();
		var popUpElem = $(".sharePopUp");
		if($(window).width() > parseInt(pos.left + $(".sharePopUp").width())){
			$(popUpElem).css("left", pos.left);
			$(popUpElem).css("right", "");
		}else{
			$(popUpElem).css("right", parseInt($(window).width() - pos.left));
			$(popUpElem).css("left", "");
		}
		
		if($(window).height() > parseInt(pos.top + $(popUpElem).height())){
			$(popUpElem).css("top", pos.top);
			$(popUpElem).css("bottom", "");
		}else{
			$(popUpElem).css("bottom", parseInt($(window).height() - pos.top));
			$(popUpElem).css("top", "");
		}
	}
	
	$(window).resize(function(){
		if($(".sharePopUp").length > 0){
			positionPopUp();
		}
	});
	
	$(document).on('click', '.smallButtonGreen', function(){
		var newValue = $(".sharePopUpInput").val().trim();
		//alert(sheetId + " "+ row_itemId + " " + share_sharedByUserId + " " + newValue);
		if(oldValue == newValue){
			closeSharePopUp();
		}else{
			$.ajax({
				context:this,
				url:"../editItemSharedBy.do",
				type:"POST",
				data:{
					sheetId:sheetId,
					itemId:row_itemId,
					userId:share_sharedByUserId,
					modifiedValue:newValue
					}
			}).done(function(data){		
				closeSharePopUp();
				refreshItem(data);
				calculateAllTotals();
				calculateBalances();
			}).fail(function(error){
				alert("Something went wrong");
				$(this).parent().html(oldValue);
			});
		}
		
	});
	
	function refreshItem(updatedRowData){
		var oldRow = $("#itemTable").find("[data-item-id=" + row_itemId + "]");
		var nextRow = $(oldRow).next();
		nextRow = ($(nextRow).length > 0) ? nextRow : null;
		$(oldRow).remove();
		insertItemInTable(updatedRowData, nextRow);
	}
	
	$(document).on('click', '.smallButtonRed', function(){
		$(".sharePopUp").remove();
	});
	//End Edit and save every value in the cell
	
	//Start Deleting a row
	$(document).on('click', '.deleteIcon', function(){
		var row =  $(this).parent().parent();
		var itemId = row.data("item-id");
		$.ajax({
			context:this,
			url:'../deleteItem.do',
			type:'POST',
			data:{
				sheetId:sheetId,
				itemId:itemId
			}
		}).done(function(data){
			
			$(row).remove();
			calculateAllTotals();
			calculateBalances();
			
			var dataRow = $("#itemTable tbody").find("tr");
			console.log(dataRow.length);
			if(dataRow.length == 0){
				$("#itemTable").hide();
				$("#balancesBox").hide();
				$("#noRecord").show();
			}
			
		}).fail(function(error){
			alert("something went wrong");
		});
	});
	//End Deleting a row
	
	
	//Start Calculating the total
	function calculateAllTotals(){
		calculateTotal("data-sharedby-price=total", ".total_price");
		$.each(userArray, function(index, value){
			calculateTotal("data-sharedby-userid=" + value, ".total_" + value);
		});
	}
	
	function calculateTotal(priceRow, totalRow){
		var total = 0;
		$("[" + priceRow + "]").each(function(){
			total += parseFloat($(this).find("span").html());
		});
		$(totalRow).html(total.toFixed(2));
	}
	//End Calculating the total
	
	function calculateBalances(){
		$.each(userArray, function(index, userId){
			var totalPaid = 0;
			$("[data-paidby-userid=" + userId + "]").each(function(){
				totalPaid += parseFloat($(this).next().find("span").html());
			});
			
			var calculatedAmt = totalPaid - parseFloat($(".total_" + userId).html());
			$("#balance_" + userId).find(".balanceAmt").html(Math.abs(calculatedAmt.toFixed(2)));
			
			if(calculatedAmt > 0){
				$("#overpaidDetails").append($("#balance_" + userId));
			}else{
				$("#owedDetails").append($("#balance_" + userId));
			}
		});
	}
	//Start click on Add/Link a buddy
	$(document).on("click", "#linkBuddyButton",function(){
		$.ajax({
			url:"../retrieveBuddies.do",
			type:"GET",
		}).done(function(data){
			if(data.length == 0){
				$("#linkBuddyModal").modal('hide');
				$("#addBuddyModal").modal('show');
			}else{
				$("#buddiesList").html("");
				$.each(data, function(index, value){
					var checked = (userArray.indexOf(value.id) == -1) ? "" : "checked disabled";
					$("#buddiesList").append("<div class='col-sm-4 col-xs-6 col-md-3'><label class='buddyLabel'><input type='checkbox' " + checked + " title='" + value.emailId + "'/>" + value.nickName+ "</label></div>");
				});
			}
		});
	});
	
	//End click on Add/Link a buddy
	
	$(document).on("click", "#addBuddyLink",function(){
		$("#linkBuddyModal").modal('hide');
	});
	
	//Start Add a new Buddy
	$(document).on("click", "#addBuddyButton",function(){
		
		var nickName = $("#buddyNickName").val();
		var emailId =  $("#buddyEmail").val();
		$("#addBuddyModal").modal('hide');
		$("#addBuddyForm")[0].reset();
	
		$.ajax({
			url:"../addUserWithNickname.do",
			type:"POST",
			data:{
				sheetId : sheetId,
				emailId : emailId,
				nickName: nickName
			}
		}).done(function(data){
			window.location.reload();
		});
	});
	//End Add a new Buddy
	//$("#balancesBox").hide();
});

