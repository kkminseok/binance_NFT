package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.Response;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.config.JwtConfig;
import kms.NFTJAVA.config.MyHttpStatus;
import kms.NFTJAVA.cookie.CookieUtil;
import kms.NFTJAVA.jwt.JwtService;
import kms.NFTJAVA.service.RedisUtil;
import kms.NFTJAVA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

@RestController
@RequestMapping("/auth")
@Slf4j
public class userController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtConfig jwtConfig;

    MyHttpStatus myHttpStatus;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO, HttpServletResponse res, HttpServletRequest req){
        int status  = userService.crdentials(userDTO);
        if(status == 4001){
            log.info("user not find");
            return ResponseEntity.status(myHttpStatus.USER_NOT_FOUND).body("user가없습니다.");
        }
        else if(status == 4002){
            log.info("user password problem");
            return ResponseEntity.status(myHttpStatus.USER_PW_NON_CORRECT).body("비밀번호 불일치;");
        }else if(status == 4003){
            log.info("password null");
            return ResponseEntity.status(myHttpStatus.USER_PW_NULL).body("user password null");
        }
        UserDTO loginUser = UserDTO.builder()
                .uid(userDTO.getUid())
                .token(userDTO.getToken())
                .password(userDTO.getPassword())
                .uname(userDTO.getUname())
                .build();

        try{

            log.info("새로 만듦 id : {}, pw : {}",loginUser.getUid(),loginUser.getPassword());
            //success login?
            String token = jwtService.generateToken(loginUser);
            String retoken = jwtService.generateRefreshToken(loginUser);
            Cookie accessToken = cookieUtil.createCookie(jwtService.ACCESS_TOKEN_NAME,token);
            Cookie refreshToken = cookieUtil.createCookie(jwtService.REFRESH_TOKEN_NAME,retoken);

            redisUtil.setDataExpire(retoken,loginUser.getUid(), jwtConfig.getRefreshTokenSecond());
            res.addCookie(accessToken);
            res.addCookie(refreshToken);
            return ResponseEntity.ok(loginUser);
        }catch (RuntimeException e){
            log.error("login fail",e);
            Response response = Response.builder()
                    .data(e)
                    .message(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO userDTO, HttpServletRequest req){
        log.info("signup!!");
        UserEntity user = userService.userSave(userDTO);
        log.info("{} {} {} {}",user.getPassword(),user.getUid(),user.getUname());
        return user.getUid();
    }

    @PostMapping("/checkid")
    public Boolean checkid(@RequestBody UserDTO userDTO){
        if(userService.finduserbyid(userDTO.getUid()) == null)
            return false;
        return true;
    }

}
