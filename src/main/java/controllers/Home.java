package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch (page) {
			case "about":
				loadAboutPage(request, response);
				break;
			case "contacts":
				loadContactsPage(request, response);
				break;
			case "feedback":
				loadFeedbackPage(request, response);
				break;
		}
	}
	
	private void loadAboutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- About Page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/home/About.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadContactsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Contacts Page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/home/Contacts.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadFeedbackPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Feedback Page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/home/Feedback.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
