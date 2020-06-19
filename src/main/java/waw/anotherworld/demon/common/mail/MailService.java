package waw.anotherworld.demon.common.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class MailService {

    @Value("${mail.devId}")
    String devId;
    @Value("${mail.devPwd}")
    String devPwd;
    @Value("${mail.smtp.port}")
    String PORT;
    @Value("${mail.smtp.host}")
    String HOST;
    @Value("${mail.smtp.auth}")
    String AUTH;

    public void sendEmail (String data) {

        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", HOST);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(devId, devPwd);
            }
        });

        InternetAddress[] toList = {};


        try {

            MimeMessage message = new MimeMessage(session);
            InternetAddress to = new InternetAddress();

            message.setFrom(new InternetAddress(devId));
//            message.addRecipients(Message.RecipientType.TO, toList);
            message.addRecipients(Message.RecipientType.TO, "moonshue@gmail.com");
            message.setSubject("[주요사항보고] DEVELOP BY 2KNOWLEDGE");
            message.setText(data);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }

}
