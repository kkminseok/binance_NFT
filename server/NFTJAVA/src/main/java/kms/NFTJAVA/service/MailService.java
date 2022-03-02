package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.Mail.MailDTO;
import kms.NFTJAVA.DTO.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;


@Slf4j
@Component
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public Boolean sendMail(MailDTO mailDTO){

        try{
            //final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            String rand = Integer.toString((int) (Math.random() * 100000));
            mailDTO.setCheck(rand);
            mailDTO.setMessage("kms에서 발송한 인증번호는 :  " + rand + "  입니다.");
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(mailDTO.getAddress());
            message.setSubject(mailDTO.getTitle());
            message.setText(mailDTO.getMessage());

            log.info("메일 발송 준비완료");
            //난수 생성

            mailSender.send(message);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}