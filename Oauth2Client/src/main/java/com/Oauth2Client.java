package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableOAuth2Sso
@SpringBootApplication
public class Oauth2Client
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Oauth2Client.class, args);
    }
}
