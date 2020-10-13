package com.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class JwtTest
{
    @Test
    public void testCreateToken()
    {
        long now = System.currentTimeMillis();
        long expiredDate = now + 60*1000;// 1 min

        JwtBuilder jwtBuilder = Jwts.builder()
                                    .setId("8888")
                                    .setSubject("Rose")
                                    //每次生成Token都不一样，因为设置了当前Date
                                    .setIssuedAt(new Date())
                                    //设置Expired Date
                                    .setExpiration(new Date(expiredDate))
                                    .signWith(SignatureAlgorithm.HS256,"XXXXX")
                                    //设置自定义(也可以通过.addClaims()直接传Map)
                                    .claim("Role","Admin")
                                    .claim("logo","google.jpg");

        String token = jwtBuilder.compact();
        System.out.println("token = " + token);

        System.out.println("-------------------------------------------");
        String[] strs = token.split("\\.");
        System.out.println("Header = "+Base64Codec.BASE64.decodeToString(strs[0]));
        System.out.println("Payload = "+Base64Codec.BASE64.decodeToString(strs[1]));
        //无法解密
        System.out.println("Signature = "+Base64Codec.BASE64.decodeToString(strs[2]));
    }

    @Test
    public void testParseToken()
    {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYwMjA3NTQyMiwiZXhwIjoxNjAyMDc1NDgxLCJSb2xlIjoiQWRtaW4iLCJsb2dvIjoiZ29vZ2xlLmpwZyJ9.E-ppooFORQ0KqMPCo5XMipF2luDoksXrktVxWGxMKQ4";
        Claims claims = Jwts.parser()
                            .setSigningKey("XXXXX")
                            .parseClaimsJws(token)
                            .getBody();
        System.out.println("ID = "+claims.getId());
        System.out.println("Subject = "+claims.getSubject());
        System.out.println("IssuedAt = "+claims.getIssuedAt());
        System.out.println("Role = "+claims.get("Role"));
        System.out.println("Logo = "+claims.get("logo"));
        System.out.println("-------------------------------------------");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Issue Date = " + sdf.format(claims.getIssuedAt()));
        System.out.println("Expired Date = " + sdf.format(claims.getExpiration()));
        System.out.println("Current Date = " + sdf.format(new Date()));

    }
}
