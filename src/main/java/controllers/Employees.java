package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DepartmentsService;
import services.EmployeesService;

import java.io.IOException;

public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeesService empsService;

    public Employees() {
        super();
        empsService = new EmployeesService();
    }
    
    private String defineUser(HttpServletRequest request) {
    	boolean isAuthorized = false;
    	String user = (String) request.getSession().getAttribute("user");
    	// ->
    	if (user != null) {
    		isAuthorized = true;
    	} else {
    		Cookie[] cookies = request.getCookies();
    		if (cookies != null) {
    			for (Cookie cookie: cookies) {
    				if (cookie.getName().equals("user")) {
    					isAuthorized = true;
    					user = cookie.getValue();
    					break;
    				}
    			}
    		}
    	}
    	// ->
    	if (isAuthorized) {
    		return user;
    	}
    	return "Гість";
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String user = defineUser(request);
		int empId = 0;
		// ->
		if (!user.equals("joji123")) {
			response.sendRedirect("Auth?page=page403");
		} else {
			switch (page) {
				case "emp_admin":
					loadEmpAdminPage(request, response);
					break;
				case "emp_create":
					loadEmpCreatePage(request, response);
					break;
				case "emp_update":
					empId = Integer.parseInt(request.getParameter("id"));
					loadEmpUpdatePage(request, response, empId);
					break;
				case "emp_details":
					empId = Integer.parseInt(request.getParameter("id"));
					loadEmpDetailsPage(request, response, empId);
					break;
				case "emp_delete":
					empId = Integer.parseInt(request.getParameter("id"));
					loadEmpDeletePage(request, response, empId);
					break;
			}
		}
	}

	private void loadEmpAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Всі спіробітники");
		request.setAttribute("service_ref", empsService);
		request.setAttribute("service2_ref", new DepartmentsService());
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/emps/EmpAdmin.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadEmpUpdatePage(HttpServletRequest request, HttpServletResponse response, int empId) throws ServletException, IOException {
		request.setAttribute("title", "- Зміна співробітника");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/emps/EmpUpdate.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadEmpDeletePage(HttpServletRequest request, HttpServletResponse response, int empId) throws ServletException, IOException {
		request.setAttribute("title", "- Видалення співробітника");
		request.setAttribute("delEmp", empsService.getEmpById(empId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/emps/EmpDelete.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadEmpDetailsPage(HttpServletRequest request, HttpServletResponse response, int depId) throws ServletException, IOException {
		request.setAttribute("title", "- Деталі співробітника");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/emps/EmpDetails.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadEmpCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Створення співробітника");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/emps/EmpCreate.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String operation = "undefined";
		boolean success = false;
		int empId = 0;
		// ->
		switch (page) {
			case "emp_create":
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				double salary = Double.parseDouble(request.getParameter("salary"));
				int depId = Integer.parseInt(request.getParameter("depId"));
				
				operation = "Створення нового співробітника";
				success = empsService.createEmp(name, age, salary, depId);
				loadReportPage(request, response, operation, success);
				break;
			case "emp_delete":
				empId = Integer.parseInt(request.getParameter("id"));
				operation = "Видалення заданого співробітника";
				success = empsService.deleteEmp(empId);
				loadReportPage(request, response, operation, success);
				break;
		}
	}

	private void loadReportPage(HttpServletRequest request, HttpServletResponse response, String operation, boolean success) throws ServletException, IOException {
		request.setAttribute("title", "- Сторінка звітів");
		request.setAttribute("operation", operation);
		request.setAttribute("success", success);
		RequestDispatcher dipatcher = request.getRequestDispatcher("views/emps/EmpReport.jsp");
		dipatcher.forward(request, response);
	}
	
}
