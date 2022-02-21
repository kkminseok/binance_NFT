package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.jwt.JwtService;
import kms.NFTJAVA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@Slf4j
public class userController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @PostMapping("/signin2")
    public void login(@RequestBody UserDTO userdto, Model model){
        log.info("login user = {} , {}", userdto.getUid(),userdto.getPassword());
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String,Object>> signin(@RequestBody UserDTO userDTO, HttpServletResponse res){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        //test
        UserEntity saveduser = userService.userSave(userDTO);
        //

        try{
            UserDTO loginUser = userService.sigin(saveduser);
            log.info("새로 만듦 id : {}, pw : {}",loginUser.getUid(),loginUser.getPassword());
            //success login?
            String token = jwtService.createToken(loginUser);
            String retoken = jwtService.createRefreshToken();
            res.setHeader("jwt-auth-token",token);
            res.setHeader("jwt-auth-refresh-token",retoken);
            resultMap.put("status",true);
            resultMap.put("data",loginUser);
            status = HttpStatus.ACCEPTED;
            System.out.println(resultMap);
        }catch (RuntimeException e){
            log.error("login fail",e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

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
        }
        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody UserDTO userDTO){
        log.info("signup!! : {}  + {} + {}",userDTO.getUid(),userDTO.getPassword(),userDTO.getUname());
        userService.userSave(userDTO);
        return "OK";
    }

}
