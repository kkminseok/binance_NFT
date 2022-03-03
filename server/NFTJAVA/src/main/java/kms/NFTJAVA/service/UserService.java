package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.Mail.MailDTO;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.jwt.JwtService;
import kms.NFTJAVA.repository.UserRedisRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private final UserRedisRepo userRedisRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MailService mailService;


    public UserEntity userSave(UserDTO userDTO){
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        UserEntity user  = new UserEntity(userDTO);
        return userRedisRepo.save(user);
    }

    public int crdentials(UserDTO userDTO){
        Optional<UserEntity> user = userRedisRepo.findById(userDTO.getUid());

        if(user.isPresent()){
            if(userDTO.getPassword() == null){
                return 4003;
            }
            if(passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword()) && userDTO.getUid().equals(user.get().getUid())){
                return 200;
            }
            return 4002;
        }
        return 4001;
    }

    public UserEntity finduserbyid(String uid){
        Optional<UserEntity> uiduser = userRedisRepo.findById(uid);
        if(uiduser.isPresent()){
            return uiduser.get();
        }

        return null;
    }


    public String findemail(String email) {
        try {
            log.info("이메일 검증 {}", email);
            MailDTO mailDTO = new MailDTO();
            mailDTO.setAddress(email);
            String result = mailService.sendMail(mailDTO);
            if(result !="error")
                return result;

        }catch(Exception e){
            return "error";
        }
        return "error";
    }
}
