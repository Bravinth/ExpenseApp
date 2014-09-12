<!DOCTYPE html>
<html>
<head>
<script src="./global/js/expense.js"></script>
</head>

<body>
	<div id="expenseDetails">

		<div class="leftNav">
			<ul>
				<li id="addExpenseButton">Add a Expense</li>
				<li id="addPersonButton">Add a Member</li>
				<li id="goToPeopleButton">View Members</li>
			</ul>
		</div>
		
		<div id="users-contain" class="ui-widget">	
			<table id="users" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th>Store/Item</th>
						<th>Date</th>
						<th>Price</th>
						<th>Paid By</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>
