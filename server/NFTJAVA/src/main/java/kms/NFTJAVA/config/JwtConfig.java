package kms.NFTJAVA.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:application.yml")
public class JwtConfig {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.TOKEN_VALIDATION_SECOND}")
    private long tokenSecond;

    @Value("${jwt.REFRESH_TOKEN_VALIDATION_SECOND}")
    private long refreshTokenSecond;
}
