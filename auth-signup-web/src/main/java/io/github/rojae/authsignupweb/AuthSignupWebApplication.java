package io.github.rojae.authsignupweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthSignupWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSignupWebApplication.class, args);
	}

}
