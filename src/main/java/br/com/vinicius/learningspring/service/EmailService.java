package br.com.vinicius.learningspring.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class EmailService {

    public void sendNotificationEmail(String to, String subject, String message) throws EmailException {
        try {
            Dotenv dotenv = Dotenv.load();
            String hostName = dotenv.get("EMAIL_HOST");
            int smtpPort = Integer.parseInt(dotenv.get("EMAIL_PORT"));
            String username = dotenv.get("EMAIL_USERNAME");
            String password = dotenv.get("EMAIL_PASSWORD");

            DefaultAuthenticator authenticator = new DefaultAuthenticator(username, password);

            Email email = new SimpleEmail();
            email.setHostName(hostName);
            email.setSmtpPort(smtpPort);
            email.setAuthenticator(authenticator);
            email.setStartTLSEnabled(true);

            email.setFrom(username);
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(message);

            email.send();
            System.out.println("Email successfully sent!");
        } catch (EmailException e) {
            throw new EmailException("Error sending notification email", e);
        }
    }
}
