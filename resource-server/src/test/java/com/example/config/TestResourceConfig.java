package com.example.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@TestConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(false);
    }
}
