package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.DepartmentsService;

public class Departments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentsService depsService;

    public Departments() {
        super();
        depsService = new DepartmentsService();
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
		int depId = 0;
		// ->
		if (!user.equals("joji123")) {
			response.sendRedirect("Auth?page=page403");
		} else {
			switch (page) {
				case "dep_admin":
					loadDepAdminPage(request, response);
					break;
				case "dep_create":
					loadDepCreatePage(request, response);
					break;
				case "dep_update":
					depId = Integer.parseInt(request.getParameter("id"));
					loadDepUpdatePage(request, response, depId);
					break;
				case "dep_details":
					depId = Integer.parseInt(request.getParameter("id"));
					loadDepDetailsPage(request, response, depId);
					break;
				case "dep_delete":
					depId = Integer.parseInt(request.getParameter("id"));
					loadDepDeletePage(request, response, depId);
					break;
			}
		}
	}
	
	private void loadDepAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Підрозділи компанії");
		request.setAttribute("service_ref", depsService);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/deps/DepAdmin.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadDepUpdatePage(HttpServletRequest request, HttpServletResponse response, int depId) throws ServletException, IOException {
		request.setAttribute("title", "- Зміна підрозділу");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/deps/DepUpdate.jsp");
		dispatcher.forward(request, response);
	}

	private void loadDepDeletePage(HttpServletRequest request, HttpServletResponse response, int depId) throws ServletException, IOException {
		request.setAttribute("title", "- Видалення підрозділу");
		request.setAttribute("delDep", depsService.getDepById(depId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/deps/DepDelete.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadDepDetailsPage(HttpServletRequest request, HttpServletResponse response, int depId) throws ServletException, IOException {
		request.setAttribute("title", "- Деталі підрозділу");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/deps/DepDetails.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadDepCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Створення підрозділу");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/deps/DepCreate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String operation = "undefined";
		String depName = "undefined";
		boolean success = false;
		int depId = 0;
		// ->
		switch (page) {
			case "dep_create":
				depName = request.getParameter("depName");
				operation = "Створення нового департаменту";
				success = depsService.createDep(depName);
				loadReportPage(request, response, operation, success);
				break;
			case "dep_delete":
				depId = Integer.parseInt(request.getParameter("id"));
				operation = "Видалення заданого підрозділу";
				success = depsService.deleteDep(depId);
				loadReportPage(request, response, operation, success);
				break;
			case "dep_update":
				break;
		}
	}

	private void loadReportPage(HttpServletRequest request, HttpServletResponse response, String operation, boolean success) throws ServletException, IOException {
		request.setAttribute("title", "- Сторінка звітів");
		request.setAttribute("operation", operation);
		request.setAttribute("success", success);
		RequestDispatcher dipatcher = request.getRequestDispatcher("views/deps/DepReport.jsp");
		dipatcher.forward(request, response);
	}
}
