package com.example.configuration;

import com.example.config.ResourceServerConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器相关配置
 * <br>
 * 针对单元测试
 */
@TestConfiguration
@EnableResourceServer
public class TestResourceServerConfig extends ResourceServerConfig {
    @Override
    public void configure(HttpSecurity http) {
        // 继承非测试环境的配置，但不要复写，否则会重复
    }

    /**
     * 由于security-oauth2默认是无状态的，那么单元测试中就无法模拟用户及用户权限。
     * 所以在单元测试时，如果要模拟用户及用户权限，就要临时关闭无状态
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(false);
    }
}
