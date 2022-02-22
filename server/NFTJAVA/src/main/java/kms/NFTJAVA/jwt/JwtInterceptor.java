package kms.NFTJAVA.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/*
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception{
        System.out.println(request.getMethod() +" : " +request.getServletPath());

        //option pass
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }else{
            //request parameter에서 autho_token을 찾음.
            String token = request.getHeader("jwt-auth-token");
            if(token != null && token.length() > 0){
                jwtService.checkValid(token);
                log.trace("Allow token {}",token);
                return true;
            }else {
                throw new RuntimeException("null token");
            }

        }
    }

}


 */