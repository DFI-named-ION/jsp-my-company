package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.EmailService;

import java.io.IOException;

import javax.mail.MessagingException;

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
			case "feedback_resc":
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
		String page = request.getParameter("page");
		switch(page) {
			case "sendMessage":
				handleSendMessage(request, response);
				break;
		}
	}
	
	protected void handleSendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ServletContext context = getServletContext();
		
		String user = context.getInitParameter("user");
		String password = context.getInitParameter("password");
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		
		String address = request.getParameter("recipient");
		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		String html = "<!DOCTYPE HTML><html><head><title></title><style type=\"text/css\">@media only screen and (min-width: 620px) {  .u-row {    width: 600px !important;  }  .u-row .u-col {    vertical-align: top;  }  .u-row .u-col-33p33 {    width: 199.98px !important;  }  .u-row .u-col-36p67 {    width: 220.02px !important;  }  .u-row .u-col-63p33 {    width: 379.98px !important;  }  .u-row .u-col-66p67 {    width: 400.02px !important;  }  .u-row .u-col-100 {    width: 600px !important;  }}@media (max-width: 620px) {  .u-row-container {    max-width: 100% !important;    padding-left: 0px !important;    padding-right: 0px !important;  }  .u-row .u-col {    min-width: 320px !important;    max-width: 100% !important;    display: block !important;  }  .u-row {    width: 100% !important;  }  .u-col {    width: 100% !important;  }  .u-col > div {    margin: 0 auto;  }}body {  margin: 0;  padding: 0;}table,tr,td { vertical-align: top;  border-collapse: collapse;}p {  margin: 0;}.ie-container table,.mso-container table {  table-layout: fixed;}* {  line-height: inherit;}a[x-apple-data-detectors='true'] {  color: inherit !important;  text-decoration: none !important;}table, td { color: #000000; } #u_body a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_heading_1 .v-container-padding-padding { padding: 30px 10px 10px !important; } #u_content_heading_1 .v-font-size { font-size: 14px !important; } #u_content_heading_2 .v-container-padding-padding { padding: 20px 10px 10px 20px !important; } #u_content_heading_2 .v-font-size { font-size: 23px !important; } #u_content_image_2 .v-container-padding-padding { padding: 10px !important; } #u_content_image_2 .v-text-align { text-align: center !important; } #u_content_divider_1 .v-container-padding-padding { padding: 10px 20px !important; } #u_content_text_1 .v-container-padding-padding { padding: 20px 20px 30px !important; } #u_content_button_1 .v-container-padding-padding { padding: 10px 10px 50px 20px !important; } #u_content_heading_3 .v-container-padding-padding { padding: 30px 20px 10px !important; } #u_content_heading_3 .v-text-align { text-align: center !important; } #u_content_text_2 .v-container-padding-padding { padding: 0px 20px 10px !important; } #u_content_text_2 .v-text-align { text-align: center !important; } #u_content_social_1 .v-container-padding-padding { padding: 10px 0px 10px 75px !important; } #u_content_text_3 .v-container-padding-padding { padding: 10px 0px !important; } #u_content_text_3 .v-font-size { font-size: 13px !important; } #u_content_text_3 .v-text-align { text-align: center !important; } #u_content_image_3 .v-container-padding-padding { padding: 20px 0px !important; } #u_content_image_3 .v-src-width { width: auto !important; } #u_content_image_3 .v-src-max-width { max-width: 49% !important; } #u_content_image_3 .v-text-align { text-align: center !important; } }</style></head><body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e7e7e7;color: #000000\"><table id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #e7e7e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\"><div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\"><div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\"><div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\"><div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\"><div style=\"background-color: #ffffff;height: 100%;width: 100% !important;\"><div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><table id=\"u_content_heading_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\"><h1 class=\"v-text-align v-font-size\" style=\"margin: 0px; line-height: 140%; text-align: center; word-wrap: break-word; font-size: 17px; font-weight: 400;\">T  H  A  N  K      Y  O  U      S  O      M  U  C  H</h1></td></tr></tbody></table><table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 0px 0px;font-family:arial,helvetica,sans-serif;\" align=\"left\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><img align=\"center\" border=\"0\" src=\"https://images.unsplash.com/photo-1581022788558-2ffc54ee2b99?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80\" alt=\"image\" title=\"image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 530px;\" width=\"530\" class=\"v-src-width v-src-max-width\"/></td></tr></table></td></tr></tbody></table></div></div></div></div></div></div><div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\"><div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\"><div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\"><div class=\"u-col u-col-66p67\" style=\"max-width: 320px;min-width: 400px;display: table-cell;vertical-align: top;\"><div style=\"background-color: #ffffff;height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><table id=\"u_content_heading_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px 10px 60px;font-family:arial,helvetica,sans-serif;\" align=\"left\"><h1 class=\"v-text-align v-font-size\" style=\"margin: 0px; color: #242424; line-height: 140%; text-align: left; word-wrap: break-word; font-size: 27px; font-weight: 700;\"><span data-metadata=\"&lt;!--(figmeta)eyJmaWxlS2V5IjoiU2U5SHB1R01sNXhHYjZ2dHZSTU1mdyIsInBhc3RlSUQiOjQ4NTg2NTcxMiwiZGF0YVR5cGUiOiJzY2VuZSJ9Cg==(/figmeta)--&gt;\"></span><span ></span><span>Повідомлення отримано!</span></h1></td></tr></tbody></table></div></div></div></div></div><div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\"><div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\"><div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\"><div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\"><div style=\"background-color: #ffffff;height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><table id=\"u_content_divider_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 48px;font-family:arial,helvetica,sans-serif;\" align=\"left\"><table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #000000;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\"><tbody><tr style=\"vertical-align: top\"><td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\"><span>&#160;</span></td></tr></tbody></table></td></tr></tbody></table><table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"><tbody><tr><td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 90px 30px 60px;font-family:arial,helvetica,sans-serif;\" align=\"left\"><div class=\"v-text-align v-font-size\" style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\"><p style=\"line-height: 140%;\"><span data-metadata=\"&lt;!--(figmeta)eyJmaWxlS2V5IjoiU2U5SHB1R01sNXhHYjZ2dHZSTU1mdyIsInBhc3RlSUQiOjE2MzgzMzEwMzIsImRhdGFUeXBlIjoic2NlbmUifQo=(/figmeta)--&gt;\" style=\"line-height: 19.6px;\"></span><span style=\"line-height: 19.6px;\"></span><span style=\"line-height: 19.6px;\">Ми інформуємо Вас " + address + " про те, що ми отримали ваше повідомлення!</span></p><p style=\"line-height: 140%;\"><span><br/></span></p><p style=\"line-height: 140%;\"><span style=\"line-height: 19.6px;\">Ваше звернення:</span></p><p style=\"line-height: 140%;\"><span><br/></span></p><p style=\"line-height: 140%;\"></p><p style=\"line-height: 140%;\"><span style=\"line-height: 19.6px;\">" + topic + "</span></p><p style=\"line-height: 140%;\"><span><br/></span></p><p style=\"line-height: 140%;\"><span style=\"line-height: 19.6px;\">" + content + "</span></p></div></td></tr></tbody></table></div></div></div></div></div></div></td></tr></tbody></table></body></html>";
		String message = "";
		String color = "";
		
		try {
			EmailService.sendEmail(host, port, user, password, address, topic, html);
			color = "forestgreen";
			message = "Ваше звернення успішно надіслане! І авжеж буде прочитане, замість того щоб бути одразу видаленим!";
		} catch (MessagingException ex) {
			System.out.println("EmailException - " + ex.getMessage());
			message = "Unsuccess! Something went wrong.";
			color = "crimson";
		}
		
		request.setAttribute("title", "- Feedback result");
		request.setAttribute("color", color);
		request.setAttribute("message", message);
		request.setAttribute("email", address);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/home/FeedbackRes.jsp");
		dispatcher.forward(request, response);
	}
}
