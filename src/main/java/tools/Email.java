package tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    public static final String from = Config.getConfig("email_address");
    public static final String password = Config.getConfig("email_password");
    public static final String host = "smtp.gmail.com";
    public static final String smptPort = "465";

    public static void sendEmail(String to, String subject, String text){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", smptPort);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        Session ses = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(ses);
            assert from != null;
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}