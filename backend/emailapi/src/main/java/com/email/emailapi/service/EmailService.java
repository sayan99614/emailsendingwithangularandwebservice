package com.email.emailapi.service;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public Boolean sendEmail(String subject,String message,String to){
        boolean f=false;
        String form = "dheemanpati1@gmail.com";

        String host = "";//your email
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", true);

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication("", "");//your mail and password
            }

        });
        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(form);
            m.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            Transport.send(m);
            f=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
