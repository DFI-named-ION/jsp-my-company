<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			footer {
				padding-top: 0px;
			}
			.my_form {
				color: wheat;
				width: 400px;
				margin: 20px auto;
				text-align: left;
				margin-top: 50px;
			}
		</style>
	</head>
	<body>
		<header>
			<%@ include file="../layouts/nav.jsp" %>
		</header>
		<main class="container">
			<div class="bg-dark p-5 rounded text-center main-box">
				<div class="content-box">
					<!-- Content Placeholder  -->
					<h2>Сторінка видалення співробітника</h2>
					<h4 style="margin-top: 30px; color: darkkhaki">Ви збираєтеся видалити співробітника</h4>
					<h3 style="color: lime">${ delEmp.getName() }</h3>
					<h4 style="color: darkkhaki">Ви впевнені?</h4>
					<form action="Employees?page=emp_delete&id=${delEmp.getId()}" method="post" class="my_form">
						<div class="form-group text-center">
							<input type="submit" value="Видалити" class="btn btn-danger" />
						</div>
					</form>
					<p><a href="Employees?page=emp_admin" class="btn btn-info">Назад до списку співробітників</a></p>
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