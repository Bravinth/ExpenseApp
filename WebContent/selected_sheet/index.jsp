<!DOCTYPE HTML>
<html>
<head>

	<title>Expense Details</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="../global/images/logo.jpg">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="../global/bootstrap_datepicker/css/datepicker.css">
	<link rel="stylesheet" href="../global/multiselect/css/multiselect.css">
	<link rel="stylesheet" href="../global/css/template.css">
</head>
<body>

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
				<li><a id="loggedInUserName"></a></li>
				<li><a href="#">Sign Out</a></li>
			</ul>
		</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
			<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
			
				<a type="button" class="btn btn-primary margin-top-10" id="addExpenseButton" data-toggle="modal" data-target="#addexpenseitemmodal">Add a Expense</a>
				<a type="button" class="btn btn-primary margin-top-10" id="linkBuddyButton" data-toggle="modal" data-target="#linkBuddyModal">Add / Link Buddy</a>
				<div class="margin-top-5 center-align hidden-xs hidden-sm" id="expenseTitleDisplay"></div>
				
				<div class="panel panel-default margin-top-10">
					<div class="hidden-md hidden-lg panel-heading center-align" id="expenseTitleDisplay1"></div>
					<div class="table-responsive">
						<div id='noRecord'>No Items to display</div>
						<table class="table table-condensed" id="itemTable">
							<thead class="itemHeader">
								<tr>
									<th></th>
									<th>Store/Item</th>
									<th>Date</th>
									<th>Paid By</th>
									<th class="align_right">Price</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td><strong>Total</strong></td>
									<td class="align_right total_price"></td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<!-- <a href="123">Check Balances</a> -->
				<div class="row margin-top-50" id="balancesBox">
					<div class="col-xs-offset-1 col-xs-10 col-sm-4 col-md-4 col-lg-offset-0 col-lg-3">
						<div class="col-sm-12 col-md-12 col-lg-12 detailsBox">
							<div class="detailsBoxHeader">Amount Overpaid</div>
							<div class="detailsBoxData" id="overpaidDetails">
								<!-- Overpaid user details -->
							</div>
						</div>
					</div>
					<div class="col-xs-offset-1 col-xs-10 col-sm-4 col-md-4 col-lg-offset-0 col-lg-3">
						<div class="col-sm-12 col-md-12 col-lg-12 detailsBox">
							<div class="detailsBoxHeader">Amount Owed</div>
							<div class="detailsBoxData" id="owedDetails">
								<!-- Owed user details -->
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1"></div>
		</div>
		
	</div>

	<!--Link your buddies Modal -->
	<div class="modal fade modal-location" id="linkBuddyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div style="text-align:center;"><b>Link Your Buddies</b></div>
				</div>
				<div class="modal-body">
					<form id="linkBuddyForm">
						<div class="row" id="buddiesList">
							<!-- Display existing buddies of the logged in user -->
						</div>
						<br/>
						<a class="btn btn-primary right" id="linkBuddiesButton">Link Buddies</a>
						<hr/>
						<div style="text-align:center;" ><a id="addBuddyLink" data-toggle="modal" data-target="#addBuddyModal"><b>Add a New Buddy</b></a></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--Link your buddies Modal -->
	
	<!--Add a new buddy Modal -->
	<div class="modal fade modal-location" id="addBuddyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>Add a Buddy</h4>
				</div>
				<div class="modal-body">
					<form id="addBuddyForm">
						<div class="form-group">
							<label>Nick name</label>
							<input type="text" class="form-control" id="buddyNickName" placeholder="Buddy Nick Name"></input>
						</div>
						<div class="form-group">
							<label>Email Id</label>
							<input type="email" class="form-control" id="buddyEmail" placeholder="Buddy Email"></input>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Close</a>
					<a class="btn btn-primary" id="addBuddyButton">Add Buddy</a>
				</div>
			</div>
		</div>
	</div>
	<!--Add a new buddy Modal -->

	<!--Add a expense item to the sheet-->
	<div class="modal fade" id="addexpenseitemmodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>Add a Expense Item</h4>
				</div>
				<div class="modal-body">
					<form id="addItemForm">
						<div class="form-group">
							<label for="itemname">Store/Item</label>
							<input type="text" id="itemname" class="form-control" placeholder="Item name"></input>
						</div>
						<div class="form-group">
							<label for="itemdate">Purchase Date</label>
							<input type="text" id="itemdate" class="form-control" placeholder="Purchase date"></input>
						</div>
						<div class="form-group">
							<label for="itemprice">Price</label>
							<input type="number" id="itemprice" class="form-control" placeholder="Item Price"></input>
						</div>
						<div class="form-group">
							<label for="itempaidby">Paid By</label>
							<select id="itempaidby" class="form-control">
								<!-- Load all the users in the sheet here -->
							</select>
						</div>
						<div class="form-group">
							<label for="itemsharedby">Shared By</label>
							<select id="itemsharedby" class="form-control" multiple='multiple'>
								
								<!-- Load all the users in the sheet here -->
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Close</a>
					<a class="btn btn-primary" id="addItemButton">Add Item</a>
				</div>
			</div>
		</div>
	</div>
	<!--Add a expense item to the sheet-->

	<!--Add a new sheet Modal -->
	<div class="modal fade modal-location" id="newsheetmodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>New Expense Sheet</h4>
				</div>
				<div class="modal-body">
					<form>
						<input type="text" class="form-control" placeholder="Expense sheet Name"></input>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Close</a>
					<a class="btn btn-primary">Create Sheet</a>
				</div>
			</div>
		</div>
	</div>
	<!--Add a new sheet Modal -->
	
	<script id="userOptions" type="text/x-handlebars-template" >
		{{#each .}}
			<option value='{{ this.id }}' data-display='{{ this.firstName }}'> {{ this.firstName }} </option>
		{{/each}}
	</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="../global/bootstrap_datepicker/js/bootstrap-datepicker.js"></script>
	<script src="../global/handlebars/handlebars.js"></script>
	<script src="../global/multiselect/js/multiselect.js"></script>
	<script src="../global/js/selectedsheet.js"></script>
</body>
</html>