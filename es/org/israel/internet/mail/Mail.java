package es.org.israel.internet.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private String username;
	private String password; //your password as ROT13 enconded

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public Mail(String username, String password) {
		this.username = username;
		this.password = Rot13(password);
	}

 
	//Code taken from: http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
	public void generateAndSendEmail(String... values) throws AddressException, MessagingException {
		//setting Gmail configuration
 		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
		// Setting Gmail session
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("MAIL_RCPT_TO")); //TO
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("MAIL_RCPT_CC")); //CC
		generateMailMessage.setSubject(values[0]);
		String emailBody = values[1];
		generateMailMessage.setContent(emailBody, "text/html");
 
		// Sending email
		Transport transport = getMailSession.getTransport("smtp");
 
		transport.connect("smtp.gmail.com", username, password);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
	public String Rot13(String texto) {
		StringBuilder text13 = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if       (c >= 'a' && c <= 'm') text13.append(c += 13);
			else if  (c >= 'A' && c <= 'M') text13.append(c += 13);
			else if  (c >= 'n' && c <= 'z') text13.append(c -= 13);
			else if  (c >= 'N' && c <= 'Z') text13.append(c -= 13);
		}
		
		return text13.toString();
	}
}
