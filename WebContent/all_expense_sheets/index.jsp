<!DOCTYPE html>
<html>
<head>

	<title>All Expense Sheets</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="../global/images/logo.jpg">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="../global/css/template.css">
	
</head>
<body>

	<!--Create a new sheet Modal -->
	<div class="modal fade modal-location" id="addsheetmodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>New Expense Sheet</h4>
				</div>
				<div class="modal-body">
					<form id="addSheetForm">
						<input type="text" class="form-control" placeholder="Expense sheet Name" id="newSheetName" autofocus></input>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Close</a>
					<a class="btn btn-primary" id="addSheetButton">Create Sheet</a>
				</div>
			</div>
		</div>
	</div>
	<!--Create a new sheet Modal -->

	<!--Page Header -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle"  data-toggle="collapse" data-target="#userNavigation">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<img class="logo" src="../global/images/logo.jpg" alt="image could not be loaded"/>
			<a class="navbar-brand">Expense Sharing</a>		
		</div>
		<div class="collapse navbar-collapse" id="userNavigation">
			<ul class="nav navbar-nav navbar-right" style="margin-right:10px;">
				<li><a href="#" id="loggedInUserName"></a></li>
				<li><a href="#">Sign Out</a></li>
			</ul>
		</div>
	</nav>
	<!--Page Header -->

	<!--Main Content-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-hidden col-sm-hidden col-sm-1"></div>
			<div class="col-xs-12 col-sm-12 col-md-10" id="allSheetContainer">

				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#addsheetmodal">Add Sheet</a>
				<span class="title"> All Expense Sheets </span>
				<hr/>
				<div id='noRecord'>No Expense Sheets to display</div>
				<table class="table table-striped" id="expense-table">
					<thead>
						<tr>
							<th>Expense Sheet</th>
							<th>Created By</th>
							<th>Created On</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- All the expense sheets will be listed here -->
					</tbody>
				</table>
			</div>
			<div class="col-xs-hidden col-sm-hidden col-sm-1"></div>
		</div>
	</div>
	<!--Main Content-->
	
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="../global/js/allexpensesheets.js"></script>

</body>
</html>