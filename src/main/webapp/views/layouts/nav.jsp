<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
				  <div class="container">
					    <a class="navbar-brand" href="index.jsp">MyCompany</a>
					    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
					      <span class="navbar-toggler-icon"></span>
					    </button>
					    <div class="collapse navbar-collapse" id="navbarCollapse">
						      <ul class="navbar-nav me-auto mb-2 mb-md-0">
							        <li class="nav-item">
							          	<a class="nav-link active" aria-current="page" href="index.jsp">Головна</a>
							        </li>
							        <li class="nav-item">
							          	<a class="nav-link" href="Home?page=about">Про сайт</a>
							        </li>
							        <li class="nav-item">
							          	<a class="nav-link" href="News?page=news_admin">Новини</a>
							        </li>
							        <li class="nav-item">
							          	<a class="nav-link" href="Products?page=catalog">Продукти</a>
							        </li>
							        <li class="nav-item">
							          	<a class="nav-link" href="Home?page=contacts">Контакти</a>
							        </li>
							        <li class="nav-item">
							          	<a class="nav-link" href="Home?page=feedback">Зворотній зв'язок</a>
							        </li>
							        <li class="nav-item dropdown">
							        	<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-bs-toggle="dropdown" aria-expanded="false">Адмін</a>
							        	<ul class="dropdown-menu" aria-labelledby="dropdown">
							        		<li><a class="dropdown-item" href="Departments?page=dep_admin">Департаменти</a></li>
							        		<li><a class="dropdown-item" href="Employees?page=emp_admin">Співробітники</a></li>
							        		<li><a class="dropdown-item" href="Users?page=user_admin">Користувачі</a></li>
							        	</ul>
							        </li>
						      </ul>
						      <%
						      	  String user = (String)request.getSession().getAttribute("user");
						      	  if (user == null) {
						      		  Cookie[] cookies = request.getCookies();
						      		  if (cookies != null) {
						      			  for (Cookie cookie : cookies) {
						      				  if (cookie.getName().equals("user")) {
						      					  user = cookie.getValue();
						      					  break;
						      				  }
						      			  }
						      		  }
						      	  }
						      	  pageContext.setAttribute("user", user);
						      %>
						      <ul class="navbar-nav ms-auto nb-2 mb-md-0">
							  		<li class="nav-item">
							      		<a class="nav-link" href="#" style="margin-right: 75px">
							      			Привіт,
							      			<c:if test="${user != null}">
							      				<span style="color: lime">
							      					<c:out value="${user}"/>!
							      				</span>	
							      			</c:if>
							      			<c:if test="${user == null}">
							      				<span style="color: yellow">
							      					Гість!
							      				</span>	
							      			</c:if>
							      		</a>	      		
							      	</li>
							      	
							      	<c:if test="${user == null}">
								      	<li class="nav-item">
								      		<a class="nav-link" href="Auth?page=signin">Вхід</a>	      		
								      	</li>
								      	<li class="nav-item">
								      		<a class="nav-link" href="Auth?page=signup">Реєстрація</a>	      		
								      	</li>
								    </c:if>
								    
								    <c:if test="${user != null}">
								      	<li class="nav-item">
								      		<a class="nav-link" href="Auth?page=signout">Вихід</a>	      		
								      	</li>
								      	<li class="nav-item">
								      		<a class="nav-link" href="Auth?page=profile">Профіль</a>	      		
								      	</li>
								    </c:if>
						      </ul>
					    </div>
				  </div>
			</nav>