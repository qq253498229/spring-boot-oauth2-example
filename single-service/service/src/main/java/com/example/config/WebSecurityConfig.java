package com.example.config;

import com.example.user.CustomLogoutHandler;
import com.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Web安全相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    UserService userService;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    CustomLogoutHandler customLogoutHandler;

    /**
     * 为密码方式提供认证管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 构建认证管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 提供用户详情实现
                .userDetailsService(userService)
                // 指定加密方式
                .passwordEncoder(passwordEncoder);
    }

    /**
     * http安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 注册接口允许未登录用户访问
                .antMatchers("/oauth/register").anonymous()
                // 其余未指定的所有请求都需要授权
                .anyRequest().authenticated();
        http
                // 注销相关逻辑配置
                .logout()
                // 注销接口的url
                .logoutUrl("/oauth/logout")
                // 添加注销处理逻辑
                .addLogoutHandler(customLogoutHandler)
                // 注销之后的逻辑
                .logoutSuccessHandler(customLogoutHandler);
        // 关闭csrf校验
        http.csrf().disable();
        // 开启登录页面支持
        http.formLogin();
        // 开启http basic认证方式
        http.httpBasic();
    }
}
