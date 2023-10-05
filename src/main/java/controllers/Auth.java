package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import services.UsersService;
import services.EmailService;

public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersService usersService;
       
    public Auth() {
        super();
        usersService = new UsersService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch (page) {
			case "signin":
				loadSignInPage(request, response);
				break;
			case "signup":
				loadSignUpPage(request, response);
				break;
			case "confirm":
				String email = request.getParameter("email");
				loadConfirmPage(request, response, email);
				break;
			case "profile":
				loadProfilePage(request, response);
				break;
			case "page403":
				loadPage403Page(request, response);
				break;
			case "signout":
				doSignoutAction(request, response);
				break;
			case "ajax_signup":
				loadJSONSignUpData(request, response);
				break;
		}
	}

    private void loadSignUpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Registration");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/SignUp.jsp");
		dispatcher.forward(request, response);
	}
    
    private void loadSignInPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Authorization");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/SignIn.jsp");
		dispatcher.forward(request, response);
	}
    
    private void doSignoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String user = (String)session.getAttribute("user");
    	if (user != null) {
    		session.invalidate();
    	}
    	
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie cookie : cookies) {
    			cookie.setMaxAge(0);
    			response.addCookie(cookie);
    		}
    	}
    	
    	response.sendRedirect("index.jsp");
	}
    
    private void loadConfirmPage(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
		request.setAttribute("title", "- About Page");
		String color = "";
		String message = "";
		boolean result = usersService.confirmUser(email);
		
		if (result) {
			color = "green";
			message = "Success! Your account is activated!";
		} else {
			color = "crimson";
			message = "Failed! Something went wrong!";
		}
		
		request.setAttribute("title", "- Confirmation result");
		request.setAttribute("message", message);
		request.setAttribute("color", color);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/Confirm.jsp");
		dispatcher.forward(request, response);
	}
    
    private void loadProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- Profile Page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/Profile.jsp");
		dispatcher.forward(request, response);
	}
    
    private void loadPage403Page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "- About Page");
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/Page403.jsp");
		dispatcher.forward(request, response);
	}
    
    private void loadJSONSignUpData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String checkLogin = request.getParameter("login");
    	boolean checkResult = usersService.checkLoginFree(checkLogin);
    	String backMessage = "";
    	// ->
    	if (checkResult) {
    		backMessage = "YES";
    	} else {
    		backMessage = "NO";
    	}
    	// ->
    	
    	response.getWriter().write(backMessage);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch(page) {
			case "signup":
				handleSignUpData(request, response);
				break;
			case "signin":
				handleSignInData(request, response);
				break;
		}
	}
	
	protected void handleSignUpData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputLogin = request.getParameter("login");
		String inputPassword = request.getParameter("password");
		String inputEmail = request.getParameter("email");
		
		String hashPassword = getMD5Hash(inputPassword);
		boolean result = usersService.addUser(inputLogin, hashPassword, inputEmail, 1, 1);
		String message = "";
		String color = "";
		
		if (result) {
			color = "green";
			message = "You successfully registrated on Our website!";
			ServletContext context = getServletContext();
			
			String user = context.getInitParameter("user");
			String password = context.getInitParameter("password");
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			
			String address = inputEmail;
			String topic = "Confirmation of registration on MyCompany website!";
			String url = "http://localhost:8080/MyCompany/Auth?page=confirm&email=" + inputEmail;
			
			String html = "<h2>You were successfully registrated on myCompany website</h2><hr/><h3>To confirm your email, please, click on link below:<br/></h3>";
			html += String.format("<a href=\"%s\">Approve my email!</a>", url);
			
			try {
				EmailService.sendEmail(host, port, user, password, address, topic, html);
			} catch (MessagingException ex) {
				System.out.println("EmailException - " + ex.getMessage());
				message = "Unsuccess! Something went wrong.";
				color = "red";
			}
		} else {
			color = "red";
			message = "You are not able to registrate on this website!";
		}
		
		request.setAttribute("title", "- Registration result");
		request.setAttribute("color", color);
		request.setAttribute("message", message);
		request.setAttribute("email", inputEmail);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/SignUpRes.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void handleSignInData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputLogin = request.getParameter("login");
		String inputPassword = request.getParameter("password");
		String inputRemember = request.getParameter("remember");
		
		String hashPassword = getMD5Hash(inputPassword);
		boolean resultSuccess = usersService.loginUser(inputLogin, hashPassword);
		boolean confirmSuccess = usersService.checkConfirm(inputLogin);
		
		String color = "";
		String message = "";
		
		if (!resultSuccess) {
			color = "crimson";
			message = "User was not found! Access denined!";
		} else if (resultSuccess && !confirmSuccess) {
			color = "red";
			message = "Email address is not approved!";
		} else if (resultSuccess && confirmSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("user", inputLogin);
			// ->
			if (inputRemember != null && inputRemember.equals("yes")) {
				Cookie cookie = new Cookie("user", inputLogin);
				cookie.setMaxAge(10 * 24 * 60 * 60);
				response.addCookie(cookie);
			}
			// ->
			color = "forestgreen";
			message = "You successfully authorized on website!";
			
			request.setAttribute("title", "Authorization Result");
			request.setAttribute("color", color);
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/auth/SignInRes.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    private String getMD5Hash(String initPassword) {
    	String hashPassword = "";
    	try {
    		MessageDigest md5 = MessageDigest.getInstance("MD5");
    		md5.update(StandardCharsets.UTF_8.encode(initPassword));
    		hashPassword = String.format("%032x", new BigInteger(1, md5.digest()));
    	} catch (NoSuchAlgorithmException ex) {
    		System.out.println("getMD5Hash - " + ex.getMessage());
    	}
    	return hashPassword;
    }

}
