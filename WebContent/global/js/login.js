$(document).ready(function(){
	
	$("#loginContainer").show();
	$("body").css("height", window.innerHeight);
	var middle = window.innerHeight/2 - $(".container-fluid").height()/2;
	$(".container-fluid").css({"margin-top":middle,"position":"absolute","width":"100%"});
	
	
	
	$(document).on("click", "#loginButton", function(){
		$.ajax({
			url:"login.do",
			type:"POST",
			data:{
				email:$("#email").val(),
				password:$("#password").val()
			}
		}).done(function(data){
			if(data.id){
				localStorage.setItem("loggedInUserId", data.id);
				localStorage.setItem("loggedInUserName", data.firstName);
				window.location = "all_expense_sheets/index.jsp";
			}
		}).fail(function(XHttpError){
			alert("something went wrong");
		});
	});
	
	$(document).on("click", "#createAcctButton", function(){
		
		var userInfo = {
			firstName:$("#firstName").val(),
			emailId:$("#emailId").val(),
			password:$("#password").val(),
			
		};
		alert(userInfo);
		$.ajax({
			url:"signup.do",
			type:"POST",
			contentType: "application/json; charset=utf-8",
	        dataType: "json",
			data:JSON.stringify(userInfo)
		}).done(function(data){
			if(data.id){
				localStorage.setItem("loggedInUserId", data.id);
				localStorage.setItem("loggedInUserName", data.firstName);
				window.location = "all_expense_sheets/index.jsp";
			}
		}).fail(function(XHttpError){
			alert("something went wrong");
		});
	});

	$(document).on("click", "#createAcctPageLink", function(){
		$("#loginContainer").hide();
		$("#signupContainer").slideDown();
	});
	
	$(document).on("click", "#loginPageLink", function(){
		$("#signupContainer").hide();
		$("#loginContainer").slideDown();
		
	});
	
});