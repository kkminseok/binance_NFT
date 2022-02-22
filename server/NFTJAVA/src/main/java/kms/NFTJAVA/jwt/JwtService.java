package kms.NFTJAVA.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.config.JwtConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@Slf4j
@Getter
public class JwtService {

    @Autowired
    private JwtConfig jwtConfig;

    //private long expireMin = 5;

    //public final long TOKEN_VALIDATION_SECOND = 1000L * expireMin;
    //    public final long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 5 * expireMin;

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";

    private Key getSigningKey(String secretKey) {

        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException{
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(jwtConfig.getSalt()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUid(String token){
        return extractAllClaims(token).get("uid",String.class);
    }

    public Boolean isTokenExpired(String token){
        final Date expiration = extractAllClaims(token).getExpiration();
        //현재시간보다 더 이전이면 true
        return expiration.before(new Date());
    }

    public String generateToken(final UserDTO userDTO){
        log.info("TOKEN VAL : {}",jwtConfig.getTokenSecond());
        return doGenerateToken(userDTO.getUid(),jwtConfig.getTokenSecond());
    }

    public String generateRefreshToken(final UserDTO userDTO){
        log.info("RETOKEN VAL : {}",jwtConfig.getRefreshTokenSecond());
        return doGenerateToken(userDTO.getUid(),jwtConfig.getRefreshTokenSecond());
    }

    public String doGenerateToken(String uid, long expireTime){
        Claims claims = Jwts.claims();
        claims.put("uid",uid);

        //header를 안 넣어두 되나 확인.
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(jwtConfig.getSalt()), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    public Boolean validateToken(String token, UserEntity userEntity){
        final String uid = getUid(token);

        return (uid.equals(userEntity.getUid()) && !isTokenExpired(token));
    }


    /*
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

     */

}
