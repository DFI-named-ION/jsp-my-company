<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Employee" %>
<%@ page import="services.EmployeesService" %>
<%@ page import="services.DepartmentsService" %>
<!DOCTYPE html>
<html lang="uk">
	<head>
		<%@ include file="../layouts/head.jsp" %>
		<style>
			#deps_table {
				width: 85%;
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
					<!--
					<h2 style="">Employees admin page</h2>
					<p>
						<a class="btn btn-lg w-75 btn-light" href="Employees?page=emp_create">Створити підрозділ</a>
					</p>
					-->
					<%
						DepartmentsService depService = (DepartmentsService)request.getAttribute("service2_ref");
					    EmployeesService service = (EmployeesService)request.getAttribute("service_ref");
					    List<Employee> empsList = service.getEmpsList();
					
					    // Створюємо мапу для групування співробітників за департаментами
					    Map<Integer, List<Employee>> depEmployeesMap = new HashMap<>();
					
					    // Заповнюємо мапу співробітниками
					    for (Employee emp : empsList) {
					        int depId = emp.getDepId();
					        List<Employee> employeesInDep = depEmployeesMap.getOrDefault(depId, new ArrayList<>());
					        employeesInDep.add(emp);
					        depEmployeesMap.put(depId, employeesInDep);
					    }
					%>
					
					<% for (Map.Entry<Integer, List<Employee>> entry : depEmployeesMap.entrySet()) { %>
					    <h2 class="align-self-left">Департамент: <%= depService.getDepById(entry.getKey()).getName() %></h2>
					    <table id="deps_table">
					        <thead>
					            <tr>
					                <th>ID</th>
					                <th>Ім'я</th>
					                <th>Вік</th>
					                <th>Зарплата</th>
					                <th>Управління даними</th>
					            </tr>
					        </thead>
					        <tbody>
					            <% for (Employee emp : entry.getValue()) { %>
					                <tr>
					                    <td class="cell1"><%= emp.getId() %></td>
					                    <td class="cell2 px-3"><%= emp.getName() %></td>
					                    <td class="cell3 px-3"><%= emp.getAge() %></td>
					                    <td class="cell4 px-3"><%= emp.getSalary() %>$</td>
					                    <td class="cell5">
					                        <a class="btn btn-info btn-sm" href="Employees?page=emp_details&id=<%= emp.getId() %>">
					                            Деталі
					                        </a>
					                        <a class="btn btn-warning btn-sm" href="Employees?page=emp_update&id=<%= emp.getId() %>">
					                            Змінити	
					                        </a>
					                        <a class="btn btn-danger btn-sm" href="Employees?page=emp_delete&id=<%= emp.getId() %>">
					                            Видалити
					                        </a>
					                    </td>
					                </tr>
					            <% } %>
					        </tbody>
					    </table>
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