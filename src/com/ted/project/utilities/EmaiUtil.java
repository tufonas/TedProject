package com.ted.project.utilities;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ted.project.entities.User;

public class EmaiUtil {
		private static final String username = "tedproj@gmail.com";
		private static final String password = "tedproject1234";
		private static Properties props;
		
	public EmaiUtil() {
	}
	
	private static Properties getProps() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;

		
	}
	
	public static String sendEmail(User user,StringBuffer url) {
		String email = user.getEmail();
		String onoma = user.getName();
		String epitheto = user.getSurname();
	    final String uuid = UUID.randomUUID().toString().replace("-", "");
	    int i;
	    for(i=0;i<6;i++)
	    {
	    	url.deleteCharAt(url.length()-1);
	    }
	    url.append("Home");
	    String verification_url=url+"?id="+uuid+"&email="+email; 

		Session session = Session.getInstance(getProps(),
		  new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email));
			message.setSubject("Verification Email,");
			message.setText("Dear "+onoma+" "+epitheto
				+ "\n\n This email is a verification email to activate your account.Please tap the link below!"+"\n"+verification_url);

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return uuid;
		
	}

}
