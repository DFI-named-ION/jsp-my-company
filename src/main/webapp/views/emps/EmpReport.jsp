<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
	</head>
	<body>
		<header>
			<%@ include file="../layouts/nav.jsp" %>
		</header>
		<main class="container">
			<div class="bg-dark p-5 rounded text-center main-box">
				<div class="content-box">
					<!-- Content Placeholder  -->
					<h2>Звіт про виконання операції</h2>
					<%
						String operation = request.getAttribute("operation").toString();
						boolean success = (boolean)request.getAttribute("success");
					%>
					<h3 style="color: lime"><%= operation %></h3>
					
					<% if (success) { %>
						<h5 style="color: darkkhaki">Операція була успішно виконана!</h5>
					<% } else { %>
						<h5 style="color: crimson">Спроба виконання операції була невдалою!</h5>
					<% } %>
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