<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.News" %>
<%@ page import="services.NewsService" %>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			#deps_table {
				width: 75%;
				margin: 10px auto;
				margin-top: 30px;
			}
			th, td {
				padding: 5px;
				border: 1px solid silver;
				color: wheat;
			}
			th {
				font-style: italic;
			}
			td a {
				width: 80px;
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
			<!--<div class="title-box">
					<h1>Моя компанія</h1>
					<h3>Офіційний сайт - Super-Ultra-Mega Evil Compnay</h3>
				</div>-->
				<div class="content-box">
					<!-- Content Placeholder  -->
					<h2 style="">News admin page</h2>
					<c:if test="${user == 'joji123'}">
					    <p>
					        <a class="btn btn-lg w-75 btn-light" href="News?page=news_create">Створити новину</a>
					    </p>
					</c:if>
					
					<%
						NewsService service = (NewsService)request.getAttribute("service_ref");
						List<News> newsList = service.getNewsList();
					%>
					<table id="deps_table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Заголовок новини</th>
								<th>Опис новини</th>
								<th>Управління даними</th>
							</tr>
						</thead>
						<tbody>
							<% for (News news : newsList) { %>
								<tr>
									<td class="cell1"><%= news.getId() %></td>
									<td class="cell2"><%= news.getTitle() %></td>
									<td class="cell2"><%= news.getDescription() %></td>
									<td class="cell3">
										<a class="btn btn-info btn-sm" href="News?page=news_details&id=<%= news.getId() %>">
											Деталі
										</a>
										<a class="btn btn-warning btn-sm" href="News?page=news_update&id=<%= news.getId() %>">
											Змінити
										</a>
										<a class="btn btn-danger btn-sm" href="News?page=news_delete&id=<%= news.getId() %>">
											Видалити
										</a>
									</td>
								</tr>
							<% } %>
						</tbody>
					</table>
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