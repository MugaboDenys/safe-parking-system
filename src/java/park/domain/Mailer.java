package park.domain;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ManagedBean
@SessionScoped
public class Mailer {
    public String sendEmail(String toEmail, String messages) {

        final String username = "safeparkingco1@gmail.com"; 
        final String password = "Ourcompany@13";
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); 
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Event Recorded");
            message.setText(messages);

            Transport.send(message);
            return "1";
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
}
