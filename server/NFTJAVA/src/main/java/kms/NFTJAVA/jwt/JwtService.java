package kms.NFTJAVA.jwt;

import io.jsonwebtoken.*;
import kms.NFTJAVA.DTO.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtService {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    public String createToken(final UserDTO userDTO){
        log.trace("time : {} ",expireMin);
        final JwtBuilder builder = Jwts.builder();
        //JWT TOKEN = Header + Payload + Signagure
        //Header
        builder.setHeaderParam("typ","JWT");

        //Payload
        //setSubject - token 제목
        builder.setSubject("login token")
                //expire
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
                .claim("User",userDTO)
                .claim("second","담고 싶은 정보")
                .setIssuedAt(new Date(System.currentTimeMillis()));
        //signature
        builder.signWith(SignatureAlgorithm.HS256,salt.getBytes());

        //serialization
        final String jwt = builder.compact();
        log.debug("make token : {}",jwt);
        return jwt;
    }

    public String createRefreshToken(){
        log.trace("refresh time : {}",expireMin);
        final JwtBuilder builder = Jwts.builder();
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * expireMin))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,salt.getBytes());
        final String refreshjwt = builder.compact();
        log.debug("refresh token made : {}",refreshjwt);
        return refreshjwt;
    }

    //token check
    //error  -> Runtime error
    public void checkValid(final String jwt){
        log.trace("token check : {}",jwt);
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
    }

    //token analysis
    public Map<String,Object> get(final String jwt){
        Jws<Claims> claims = null;
        try{
            System.out.println("jwt : "+ jwt);
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        }catch(final Exception e){
            throw new RuntimeException();
        }

        log.trace("claims : {}", claims);

        return claims.getBody();
    }

}
