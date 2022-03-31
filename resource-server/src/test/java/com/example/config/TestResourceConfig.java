package com.example.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@TestConfiguration
public class TestResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) {
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(false);
    }
}
