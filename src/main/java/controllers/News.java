package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.NewsService;

import java.io.IOException;

public class News extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsService newsService;

    public News() {
        super();
        newsService = new NewsService();
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
		int newsId = 0;
		// ->
		switch (page) {
		case "news_admin":
			loadNewsAdminPage(request, response);
			break;
		case "news_create":
			if (!user.equals("joji123")) {
				response.sendRedirect("Auth?page=page403");
			} else {
				loadNewsCreatePage(request, response);
			}
			break;
		}
	}

	private void loadNewsAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Новини");
		request.setAttribute("service_ref", newsService);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/news/NewsAdmin.jsp");
		dispatcher.forward(request, response);
	}

	private void loadNewsCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Створення новини");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/news/NewsCreate.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String operation = "undefined";
		String newsTitle = "undefined";
		String newsDesc = "undefined";
		boolean success = false;
		int newsId = 0;
		// ->
		switch (page) {
			case "news_create":
				newsTitle = request.getParameter("newsTitle");
				newsDesc = request.getParameter("newsDesc");
				operation = "Створення новини";
				success = newsService.createNews(newsTitle, newsDesc);
				loadReportPage(request, response, operation, success);
				break;
		}
	}
	
	private void loadReportPage(HttpServletRequest request, HttpServletResponse response, String operation, boolean success) throws ServletException, IOException {
		request.setAttribute("title", "- Сторінка звітів");
		request.setAttribute("operation", operation);
		request.setAttribute("success", success);
		RequestDispatcher dipatcher = request.getRequestDispatcher("views/news/NewsReport.jsp");
		dipatcher.forward(request, response);
	}

}
