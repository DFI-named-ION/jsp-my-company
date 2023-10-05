<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Department" %>
<%@ page import="services.DepartmentsService" %>
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
					<h2 style="">Departments admin page</h2>
					<p>
						<a class="btn btn-lg w-75 btn-light" href="Departments?page=dep_create">Створити підрозділ</a>
					</p>
					<%
						DepartmentsService service = (DepartmentsService)request.getAttribute("service_ref");
						List<Department> depsList = service.getDepsList();
					%>
					<table id="deps_table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Назва підрозділу</th>
								<th>Управління даними</th>
							</tr>
						</thead>
						<tbody>
							<% for (Department dep : depsList) { %>
								<tr>
									<td class="cell1"><%= dep.getId() %></td>
									<td class="cell2"><%= dep.getName() %></td>
									<td class="cell3">
										<a class="btn btn-info btn-sm" href="Departments?page=dep_details&id=<%= dep.getId() %>">
											Деталі
										</a>
										<a class="btn btn-warning btn-sm" href="Departments?page=dep_update&id=<%= dep.getId() %>">
											Змінити
										</a>
										<a class="btn btn-danger btn-sm" href="Departments?page=dep_delete&id=<%= dep.getId() %>">
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