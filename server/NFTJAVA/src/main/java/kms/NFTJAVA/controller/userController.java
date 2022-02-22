package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.Response;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.config.JwtConfig;
import kms.NFTJAVA.cookie.CookieUtil;
import kms.NFTJAVA.jwt.JwtService;
import kms.NFTJAVA.service.RedisUtil;
import kms.NFTJAVA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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


    @PostMapping("/signin2")
    public void login(@RequestBody UserDTO userdto, Model model){
        log.info("login user = {} , {}", userdto.getUid(),userdto.getPassword());
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO, HttpServletResponse res){



        UserEntity user = userService.crdentials(userDTO);
        if(user == null){
            log.info("SADASDAS");
        }
        //Map<String,Object> resultMap = new HashMap<>();
        //HttpStatus status = null;
        UserDTO loginUser = UserDTO.builder()
                .uid(user.getUid())
                .refreshToken(user.getRefreshToken())
                .password(user.getPassword())
                .uname(user.getUname())
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
            //res.setHeader("jwt-auth-token",token);
            //res.setHeader("jwt-auth-refresh-token",retoken);
            //resultMap.put("status",true);
            //resultMap.put("data",loginUser);
            //status = HttpStatus.ACCEPTED;
            //System.out.println(resultMap);

            log.info("res : {} user : {} ",res.getHeaders("accessToken"),loginUser.getUid());
            return ResponseEntity.ok(loginUser);
        }catch (RuntimeException e){
            log.error("login fail",e);
            //resultMap.put("message", e.getMessage());
            //status = HttpStatus.INTERNAL_SERVER_ERROR;
            Response response = Response.builder()
                    .data(e)
                    .message(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }


        //return new ResponseEntity<Map<String,Object>>(resultMap,status);


    }

    /*
    @PostMapping("/info")
    public ResponseEntity<Map<String,Object>> getInfo(HttpServletRequest req, @RequestBody UserDTO userDTO){
        log.info("id : {}, pwd : {}",userDTO.getUid(),userDTO.getPassword());
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            String info = userService.getServerinfo();
            resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));

            resultMap.put("status",true);
            resultMap.put("info",info);
            resultMap.put("request_body",userDTO);
            log.info("info로 진입하였습니다.");
            status = HttpStatus.ACCEPTED;
        }catch (RuntimeException e){
            log.error("info fail",e);
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

     */

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO userDTO){
        log.info("signup!!");
        UserEntity user = userService.userSave(userDTO);
        return user.getUid();
    }

}
