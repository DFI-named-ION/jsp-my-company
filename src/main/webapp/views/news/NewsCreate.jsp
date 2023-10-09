<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			.my_form {
				color: wheat;
				width: 400px;
				margin: 20px auto;
				text-align: left;
				margin-top: 50px;
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
					<h2>News create page</h2>
					<form class="my_form" action="News?page=news_create" method="post">
						<div class="form-group">
							<label for="newsTitle" class="form-label">Заголовок новини:</label>
							<input id="newsTitle" class="form-control" type="text"  name="newsTitle" required/>
						</div>
						<div class="form-group">
							<label for="newsDesc" class="form-label">Опис новини:</label>
							<textarea id="newsDesc" class="form-control" name="newsDesc"></textarea>
						</div>
						<div class="form-group mt-4 text-center">
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