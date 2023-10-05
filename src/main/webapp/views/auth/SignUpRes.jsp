<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<div class="title-box">
					<h1>Моя компанія</h1>
					<h3>Офіційний сайт - Super-Ultra-Mega Evil Compnay</h3>
				</div>
				<div class="content-box">
					<!-- Content Placeholder  -->
					<h2>Result of registration</h2>
					<hr/>
					<h3 style="color: ${color}">${message}</h3>
					<hr/>
					<c:if test="${color == 'green'}">
						<h4 style="color: gray">On this - <span style="color: forestgreen"><c:out value="${email}"/></span> email,<br>
						was sent confirmation message!</h4>
					</c:if>
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