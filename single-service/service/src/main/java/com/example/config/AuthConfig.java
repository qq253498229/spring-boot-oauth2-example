package com.example.config;

import com.example.client.ClientService;
import com.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * 授权服务器相关配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    ClientService clientService;
    @Resource
    UserService userService;
    @Resource
    AuthenticationManager authenticationManager;

    /**
     * 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt类型token转换器
     */
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

    /**
     * token存储方式
     * fixme 测试时用内存级，生产以及多实例环境应该使用redis等方式
     */
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * 授权服务器安全相关配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 配置加密方式
                .passwordEncoder(passwordEncoder())
                // jwt单体服务模式一般不会用到，在资源服务器常用
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 客户端相关配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端详情实现类
        clients.withClientDetails(clientService);
    }

    /**
     * 授权服务器默认接口配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 配置使用jwt进行token转换
                .accessTokenConverter(accessTokenConverter())
                // 由于要使用password方式获取token，所以需要authenticationManager
                .authenticationManager(authenticationManager)
                // 配置用户详情实现类
                .userDetailsService(userService)
                // 配置token存储方式
                .tokenStore(tokenStore())
        ;
    }
}
