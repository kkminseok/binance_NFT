package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.Mail.MailDTO;
import kms.NFTJAVA.DTO.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;


@Slf4j
@Component
public class MailService {

   @Autowired
    private JavaMailSender mailSender;

    public String sendMail(MailDTO mailDTO){

        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
            //final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            String rand = Integer.toString((int) (Math.random() * 100000));
            mailDTO.setCheck(rand);
            mailDTO.setMessage("kms에서 발송한 인증번호는 :  " + rand + "  입니다.");

            mailHelper.setTo(mailDTO.getAddress());
            mailHelper.setSubject(mailDTO.getTitle());
            mailHelper.setText(mailDTO.getMessage());

            log.info("메일 발송 준비완료");
            log.info("난수 : {}",rand);

            mailSender.send(mail);
            return rand;
        } catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}