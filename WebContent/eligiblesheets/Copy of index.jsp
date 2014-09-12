<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Expense Sharing</title>
<link href="./global/css/template.css" type="text/css" rel="stylesheet">
<link href="./global/css/eligiblesheets.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<script src="./global/js/eligiblesheets.js"></script>

</head>


<body>
	<form method="GET" id="eligibleSheetsForm">
		
		<jsp:include page="../global/header.jsp"></jsp:include>
		
		<div class="leftNavFrame"><input type="color" class="colorpicker"/></div>
		<div class="rightNavFrame"><input type="color" class="colorpicker"/></div>	
		
		<div class="leftNav">
				<ul>
					<li id="addPersonButton">Add a Member</li>
					<li id="goToPeopleButton">View Members</li>
				</ul>
		</div>
			
		<div id="content" class="content">
			<div class="sheetContainer">
				<ul id="sheetList">
					<li id="createSheetLink">Create a sheet </li>
					<li class="noListStyle">&nbsp;</li>
					<li class="noListStyle">&nbsp;</li>
					<c:forEach items="${eligibleSheets}" var="sheet">
						<li  id="${sheet.sheetId }" value="${sheet.sheetId }" class="sheetLink" style="border:1px solid red"><div class="sheetDiv" style="float:left;">${sheet.sheetName}</div><img src="./global/images/ima1.jpg" style="display:inline;height:25px;"/>
						</li>
					
						<li class="sheetLink" id="${sheet.sheetId }" value="${sheet.sheetId }">${sheet.sheetName}<a href="#" class="right">Delete</a></li>
					</c:forEach>
				</ul>
			</div>
			
			
		</div>
	
		
	
	
		<div id="createSheetDiv">
			<input type="text" placeholder="Enter your sheet name" class="text" name="sheetName" id="sheetName" value=""/>
		</div>
		
		<div id="addMemberDiv">	
			<div style="padding:10px;"><span style="color:blue;">Email Id:&nbsp;&nbsp;</span><input type="text" id="emailId"/></div>
			<div style="padding:10px;"><span style="color:blue;">Nickname:	</span><input type="text" id="nickName"/></div>
		</div>
	
		
		
	</form>
	<input type="color" class="colorpicker"/>
</body>
</html>