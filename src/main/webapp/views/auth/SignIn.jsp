<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			#signupForm {
				border: 1px solid silver;
				border-radius: 15px;
				width: 450px;
				padding: 20px 40px;
				margin: 20px auto;
				text-align: left;
			}
			label{
				padding-bottom: 0px;
				margin-top: 20px;
				color: olive;
			}
			.my-btn {
				width: 150px;
				margin-top: 25px;
				margin-bottom: 10px;
				border-radius: 5px;
			}
			.error {
				display: block;
				margin-top: 3px;
				font-style: italic;
				font-size: 10pt;
				color: crimson;
			}
			footer {
				padding-top: 20px;
			}
		</style>
	</head>
	<body>
		<header>
			<%@ include file="../layouts/nav.jsp" %>
		</header>
		<main class="container">
			<div class="bg-dark p-5 rounded text-center main-box">
				<div class="title-box">
					<h1>Моя компанія</h1>
					<h3>Офіційний сайт - Super-Ultra-Mega Evil Compnay</h3>
				</div>
				<div class="content-box">
					<!-- Content Placeholder  -->
					<h2>Authorization</h2>
					<form id="signinForm" class="form" action="Auth?page=signin" method="post" onSubmit="return true">
						<div class="form-group">
							<label for="login">Login: </label>
							<input type="text" id="login" name="login" class="form-control" required/>
							<span id="login_error" class="error"></span>
						</div>
						<div class="form-group">
							<label for="password">Password: </label>
							<input type="password" id="password" name="password" class="form-control" required/>
							<span id="password_error" class="error"></span>
						</div>
						<div class="form-group text-left mt-3">
							<input type="checkbox" id="remember" name="remember" class="form-control" value="yes"/>
							<label for="remember">Stay In</label>
						</div>
						<div class="form-group text-center">
							<input type="submit" id="submit" value="Go on!" class="btn btn-success my-btn"/>
							&nbsp;
							<input type="reset" id="reset" value="Clear" class="btn btn-danger my-btn"/>
						</div>
					</form>
					<!-- Content Placeholder  -->
				</div>
			</div>	
		</main>
		<footer class="container text-center">
			<%@ include file="../layouts/footer.jsp" %>
		</footer>
		<!-- Scripts -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
		<script src="static/js/main.js"></script>
	</body>
</html>