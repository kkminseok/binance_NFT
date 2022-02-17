package kms.NFTJAVA;

import kms.NFTJAVA.jwt.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class NftjavaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(NftjavaApplication.class, args);
	}




}


/*
1. 부동산
2. 게임
3. BTC
4. 주식
5.
 */