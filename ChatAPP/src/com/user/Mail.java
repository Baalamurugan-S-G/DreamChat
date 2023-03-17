package com.user;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static boolean send(final String from, final String password, String to,String sub,String msg) throws AddressException, MessagingException{
		Properties p=new Properties();
		System.out.println("heyman");
	    p.put("mail.smtp.host", "smtp.gmail.com");    
            p.put("mail.smtp.socketFactory.port", "465");    
            p.put("mail.smtp.socketFactory.class",    
                      "javax.net.ssl.SSLSocketFactory");    
            p.put("mail.smtp.auth", "true");    
            p.put("mail.smtp.port", "465");    

          	
          	Session session = Session.getDefaultInstance(p,    
                    new javax.mail.Authenticator() {    
                    protected PasswordAuthentication getPasswordAuthentication() {    
                    return new PasswordAuthentication(from,password);  
                    }    
                   });    
		
		try {
		MimeMessage message=new MimeMessage(session);
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		message.setSubject(sub);
		message.setText(msg);
		
		Transport.send(message);
		System.out.println("Message sent Sucessfully");
		}
		catch(Exception e){
			System.out.println("No");}
		return true;
	}
}
