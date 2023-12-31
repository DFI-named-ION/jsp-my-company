<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			.my_form {
				color: wheat;
				width: 700px;
				margin: 20px auto;
				text-align: left;
				margin-top: 50px;
			}
			.form-group {
				margin-top: 5px;
			}
			footer {
				padding-top: 0px;
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
					<h2>Сторінка зворотнього зв'язку</h2>
					<form class="my_form" action="Home?page=sendMessage" method="post">
						<div class="form-group">
							<label for="topic" class="form-label">Заголовок:</label>
							<input id="topic" class="form-control" type="text"  name="topic" required/>
						</div>
						<div class="form-group">
							<label for="content" class="form-label">Повідомлення:</label>
							<textarea id="content" class="form-control" name="content" rows="10" required></textarea>
						</div>
						<div class="form-group">
							<label for="recipient" class="form-label">Ваша пошта:</label>
							<input id="recipient" class="form-control" type="email"  name="recipient" required/>
						</div>
						<div class="form-group text-center">
							<input type="submit" value="Відправити" class="btn btn-success"/>
							<input type="reset" value="Очистити" class="btn btn-danger"/>
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