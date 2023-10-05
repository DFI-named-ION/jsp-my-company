package services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	
	public static void sendEmail(String host, String port, String user, String password,
			String address, String topic, String content) throws AddressException, MessagingException {
		// 1
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		// 2
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};

		// 3
		Session session = Session.getInstance(props, auth);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		InternetAddress[] recipients = { new InternetAddress(address) };
		
		// 4
		message.setRecipients(Message.RecipientType.TO, recipients);
		message.setSubject(topic);
		message.setSentDate(new Date());
		message.setContent(content, "text/html; charset=utf-8");
		
		// 5
		Transport.send(message);
	}
}
