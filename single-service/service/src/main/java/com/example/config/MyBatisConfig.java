package com.example.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.example.generator.mapper")
public class MyBatisConfig {
}
