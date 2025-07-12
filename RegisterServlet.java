package mypack;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendConfirmationEmail(String toEmail, String fullName) {
        final String fromEmail = "youremail@gmail.com"; // Your email
        final String password = "your-app-password";    // App password (not Gmail password)

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "Event Portal"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("🎉 Registration Successful");
            msg.setText("Hi " + fullName + ",\n\nThank you for registering for the event!\n\nEvent Team");

            Transport.send(msg);
            System.out.println("Email sent to: " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
