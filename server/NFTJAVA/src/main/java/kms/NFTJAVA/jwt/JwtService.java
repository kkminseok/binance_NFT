package kms.NFTJAVA.jwt;

import io.jsonwebtoken.*;
import kms.NFTJAVA.DTO.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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
        Claims claims = Jwts.claims();
        claims.put("uid",userDTO.getUid());
        //JWT TOKEN = Header + Payload + Signagure
        //Header
        builder.setHeaderParam("typ","JWT");

        //Payload
        //setSubject - token 제목
        builder.setSubject("kms token")
                //expire
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 10 * expireMin))
                .setClaims(claims)
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
    public Map<String,Object> get(final String jwt) throws UnsupportedEncodingException {
        Jws<Claims> claims = null;
        try{
            System.out.println("jwt : "+ jwt);
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        }catch(ExpiredJwtException e){//token 만료
            log.warn("token이 만료되었습니다.");
        }catch (Exception e){
            log.warn("token get error : {}",e);
        }

        log.trace("claims : {}", claims);

        return claims.getBody();
    }

}
