package kms.NFTJAVA.filter;

import io.jsonwebtoken.ExpiredJwtException;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.cookie.CookieUtil;
import kms.NFTJAVA.jwt.JwtService;
import kms.NFTJAVA.service.RedisUtil;
import kms.NFTJAVA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenitcationFilter extends OncePerRequestFilter {

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final Cookie jwtToken = cookieUtil.getCookie(request,JwtService.ACCESS_TOKEN_NAME);

        String uid = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUid = null;

        try{
            if(jwtToken != null){
                jwt = jwtToken.getValue();
                uid = jwtService.getUid(jwt);
            }
            if(uid != null){
                UserEntity finduserbyid = userService.finduserbyid(uid);

                if(jwtService.validateToken(jwt,finduserbyid)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(finduserbyid,null, AuthorityUtils.NO_AUTHORITIES);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException e){
            Cookie refreshToken = cookieUtil.getCookie(request,JwtService.REFRESH_TOKEN_NAME);
            if(refreshToken != null){
                refreshJwt = refreshToken.getValue();
            }
        }catch(Exception e){

        }

        try{
            if(refreshJwt != null){
                refreshUid = redisUtil.getData(refreshJwt);

                if(refreshUid.equals(jwtService.getUid(refreshJwt))){
                    UserEntity finduserbyid = userService.finduserbyid(refreshUid);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(finduserbyid,null, AuthorityUtils.NO_AUTHORITIES);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    UserDTO userDTO = new UserDTO();
                    userDTO.setUid(refreshUid);
                    String newToken = jwtService.generateToken(userDTO);

                    Cookie newAccessToken = cookieUtil.createCookie(JwtService.ACCESS_TOKEN_NAME,newToken);
                    response.addCookie(newAccessToken);
                }
            }
        }catch (ExpiredJwtException e){

        }
        filterChain.doFilter(request,response);
    }




    /*
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 리퀘스트에서 토큰 가져오기.
            String token = parseBearerToken(request);
            log.info("Filter is running...");
            // 토큰 검사하기. JWT이므로 인가 서버에 요청 하지 않고도 검증 가능.
            if (token != null && !token.equalsIgnoreCase("null")) {
                // userId 가져오기. 위조 된 경우 예외 처리 된다.
                String userId = tokenProvider.validateAndGetUserId(token);
                log.info("Authenticated user ID : " + userId );
                // 인증 완료; SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId, // 인증된 사용자의 정보. 문자열이 아니어도 아무거나 넣을 수 있다.
                        null, //
                        AuthorityUtils.NO_AUTHORITIES
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        // Http 리퀘스트의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

     */
}