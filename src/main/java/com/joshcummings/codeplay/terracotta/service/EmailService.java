package com.joshcummings.codeplay.terracotta.service;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.joshcummings.codeplay.terracotta.email.gateway.EmailGateway;

public class EmailService {
    private String from = "no-reply-terracotta-bank@mailinator.com";
    private String host = "in-v3.mailjet.com";
    private Properties properties = System.getProperties();
    private EmailGateway emailGateway;

    {
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
    }

    public EmailService(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    public MimeMessage sendMessage(String to, String subject, String content) {
        Objects.requireNonNull(to);
        Objects.requireNonNull(subject);
        Objects.requireNonNull(content);
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("49ef5e4854ccd94d532dd275b77135b8", "296e1e0085b8aa90e06da635c357ecf1");
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            emailGateway.sendEmail(message);
        } catch (MessagingException mex) {
            throw new IllegalStateException(mex);
        }
        return message;
    }
}
