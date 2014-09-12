<!doctype html>

<html>
	<head>
		<title>Expense Sharing</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" href="../global/images/logo.jpg">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="./global/css/template.css">
	</head>
	
	<body class="login_body">
			<div class="container-fluid" id="loginContainer">
				<div class="row">
					<div class="col-xs-1 col-sm-3 col-md-4 col-lg-4"></div>
					<div class="col-xs-10 col-sm-6 col-md-4 col-lg-3">
						<form id="loginForm">
							<div id="loginbox">
								<div class="login_title border_bottom_white">Expense Login</div>
								<input type="email" class="form-control margin-top-10" placeholder="Email Address"  name="email" id="email" value="bravinth@gmail.com"></input>
								<input type="password" class="form-control margin-top-5" placeholder="Password"  name="password" id="password" value="dei"></input>
								<a type="button" class="btn btn-primary form-control margin-top-10" id="loginButton">Login</a>
								<div style="font-size:0.9em;margin-top:10px;margin-bottom:15px;">
									<a href="" style="color:#ddd;">Forgot Password</a>
								</div>
								
								<div class="login_title border_top_white"><a id="createAcctPageLink" class="text_white ">Create New Account</a></div>
							</div>
						</form>
					</div>
					<div class="col-xs-1 col-sm-3 col-md-4 col-lg-5"></div>
				</div>
			</div>
			
			<div class="container-fluid" id="signupContainer">
				<div class="row">
					<div class="col-xs-1 col-sm-3 col-md-4 col-lg-4"></div>
					<div class="col-xs-10 col-sm-6 col-md-4 col-lg-3">
						<form id="signupForm">
							<div id="signupbox">
								<div class="login_title border_bottom_white">Create Your Account</div>
								<!-- input type="text" class="form-control margin-top-10" placeholder="First Name"  name="firstName" id="firstName"></input> -->
								<input type="text" class="form-control margin-top-5" placeholder="User Name"  name="firstName" id="firstName"></input>
								<input type="email" class="form-control margin-top-5" placeholder="Email Address"  name="emailId" id="emailId"></input>
								<input type="password" class="form-control margin-top-5" placeholder="Password"  name="password" id="password"></input>
								<input type="password" class="form-control margin-top-5" placeholder="Confirm Password"  name="confirmPassword" id="confirmPassword"></input>
								<a type="button" class="btn btn-primary form-control margin-top-10 margin-bottom-10" id="createAcctButton">Create Account</a>
								<div class="margin-top-10"></div>
								<div class="login_title border_top_white"><a id="loginPageLink" class="text_white">Already Have An Account</a></div>
							</div>
						</form>
					</div>
					<div class="col-xs-1 col-sm-3 col-md-4 col-lg-5"></div>
				</div>
			</div>
	</body>

	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="global/js/login.js"></script>
</html>