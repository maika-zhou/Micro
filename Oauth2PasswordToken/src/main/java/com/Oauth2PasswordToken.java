package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2PasswordToken
{
    public static void main(String[] args) throws Exception {



        SpringApplication.run(Oauth2PasswordToken.class, args);
    }
}
