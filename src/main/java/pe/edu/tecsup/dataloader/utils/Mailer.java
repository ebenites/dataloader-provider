package pe.edu.tecsup.dataloader.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Created by ebenites on 30/05/2017.
 */
@Component
public class Mailer {

    private static final Logger log = Logger.getLogger(Mailer.class);

    private static final String SUBJECT = "Dataloader Provider";

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String FROM;

    @Value("${mail.to}")
    private String TO;

    public void sendMail(String message){
        log.info("sendMail: " + message);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
            mailMsg.setFrom(FROM);
            mailMsg.setTo(TO);
            mailMsg.setCc("ebenites@tecsup.edu.pe");
            mailMsg.setSubject(SUBJECT + " : " + this.getClass().getSimpleName());
            mailMsg.setText(message);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.error(e, e);
        }
    }

}
