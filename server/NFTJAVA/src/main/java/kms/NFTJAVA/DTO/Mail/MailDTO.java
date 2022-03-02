package kms.NFTJAVA.DTO.Mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
    private String address;
    private String title = "kms에서 발송한 메일입니다.";
    private String message;
    private String check;
}
