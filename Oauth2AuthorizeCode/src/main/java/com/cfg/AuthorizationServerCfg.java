package com.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerCfg extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.inMemory()
                //配置client-id
                .withClient("admin")
                //配置client-secret
                .secret(passwordEncoder.encode("123"))
                //配置token 有效期 3600 秒
                .accessTokenValiditySeconds(3600)
                //配置redirect_URL,用于授权成功后跳转
                .redirectUris("http://www.baidu.com")
                //配置申请request的权限范围
                .scopes("all")
                //表示授权码模式
                .authorizedGrantTypes("authorization_code");
    }
}
