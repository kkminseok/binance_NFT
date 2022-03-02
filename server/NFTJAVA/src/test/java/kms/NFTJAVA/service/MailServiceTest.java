package kms.NFTJAVA.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {

    @Autowired
    JavaMailSender mailSender;
    @Test
    public void mailSendTest() throws Exception{

        String subject = "test mail";
        String content = "mail test";
        String from = "minseok19950727@gmail.com";
        String to = "aaa727@khu.ac.kr";

        try{
            //final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            String rand = Integer.toString((int) (Math.random() * 100000));


            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("kms에서 발송한 인증번호는 :  " + rand + "  입니다.");
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            mailSender.send(message);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

}