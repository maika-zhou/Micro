package com.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController
{
    public String name;


    @RequestMapping("/getUser")
    public Object getUser(Authentication auth, HttpServletRequest request)
    {
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer")+7);

        System.out.println("token = " + token);

        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();


    }
}
