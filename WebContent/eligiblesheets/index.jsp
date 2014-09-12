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
		

	<div id="container">
		<nav>
			<ul>
				<li id="addPersonButton">Add a Member</li>
				<li id="goToPeopleButton">View Members</li>
			</ul>
		</nav>
		<main>
			<div class="mainTitle">All Expense Sheets</div>
			<div class="mainDiv">
				<div class="mainDivHeader">
					<div id="addSheetButton">Add Sheet</div>
				</div>
				<table class="sheetListTable">
					<thead>
						<tr>
							<td><input type="checkbox"></td>
							<td>Sheet Name</td>
							<td>Created Date</td>
							<td>Created By</td>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${eligibleSheets}" var="sheet">
							<tr>
								<td><input type="checkbox" id="${ sheet.sheetId }"></td>
								<td>${ sheet.sheetName }</td>
								<td>${ sheet.sheetCreatedDate }</td>
								<td>${ sheet.sheetCreatedByUser.id } </td>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
			</div>
		</main>
		<aside>
		</aside>
	</div>

	<jsp:include page="../global/footer.jsp"></jsp:include>
	
		<div id="createSheetDiv">
			<input type="text" placeholder="Enter your sheet name" class="text" name="sheetName" id="sheetName" value=""/>
		</div>
		
		<div id="addMemberDiv">	
			<div style="padding:10px;"><span style="color:blue;">Email Id:&nbsp;&nbsp;</span><input type="text" id="emailId"/></div>
			<div style="padding:10px;"><span style="color:blue;">Nickname:	</span><input type="text" id="nickName"/></div>
		</div>
			
	</form>
</body>
</html>