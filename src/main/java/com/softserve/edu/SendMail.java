package com.softserve.edu;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.testng.log4testng.Logger;

public class SendMail {

	
	private final static Logger helperLogger = Logger.getLogger(SendMail.class);

    public static void main(String[] args) {
    	
        final String username = "nlushney@gmail.com";
        final String password = "capturetheflag";
        final String recipient = "nlushney@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

     
        Helper helper = new Helper(); 
        
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Weather");
            message.setContent(helper.getFormatedWeather(), "text/html; charset=utf-8");
            
            Transport.send(message);

//            System.out.println("Done");
            helperLogger.info("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}