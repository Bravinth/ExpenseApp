<!DOCTYPE html>
<html>

<head>
<title>Expense Sharing</title>
<link href="./global/css/template.css" type="text/css" rel="stylesheet">
<link href="./global/css/expense.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<script src="./global/js/custom.js"></script>
</head>


<body>

	<jsp:include page="../global/header.jsp"></jsp:include>

	<div class="leftNavFrame"></div>
	<div class="rightNavFrame"></div>	
	
	<div id="content" class="content">
		Loading ...	
		
		
	</div>
	
	
	
	
	
	<div id="addExpenseDiv">
		<div style="padding:8px;"><pre><span style="color:blue;font-family: Verdana,Arial,sans-serif;font-size:1.2em;">Store/Item:	</span><input type="text" id="item"/></pre></div>
		<div style="padding:8px;"><pre><span style="color:blue;font-family: Verdana,Arial,sans-serif;font-size: 1.2em;">Date:		</span><input type="text" id="date"/></pre></div>
		<div style="padding:8px;"><pre><span style="color:blue;font-family: Verdana,Arial,sans-serif;font-size: 1.2em;">Price:		</span><input type="text" id="price"/></pre></div>
		<div style="padding:8px;"><pre><span style="color:blue;font-family: Verdana,Arial,sans-serif;font-size: 1.2em;">Paid By:		</span><select id="paidBy" style="width:120px;"></select> </pre></div>
		<div style="padding:8px;"><pre><span style="color:blue;font-family: Verdana,Arial,sans-serif;font-size: 1.2em;">Paid For:		All  or  <a href="#">Select people</a></span></pre></div>
	</div>

	<div id="addMemberDiv">	
		<div style="padding:10px;"><span style="color:blue;">Name:	</span><input type="text" id="name"/></div>
		<div style="padding:10px;"><span style="color:blue;">Email:&nbsp;&nbsp;</span><input type="text" id="email"/></div>
	</div>

	

</body>
</html>