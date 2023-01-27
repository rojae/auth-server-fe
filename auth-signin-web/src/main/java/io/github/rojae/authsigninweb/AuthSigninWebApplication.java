package io.github.rojae.authsigninweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthSigninWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthSigninWebApplication.class, args);
    }

}
