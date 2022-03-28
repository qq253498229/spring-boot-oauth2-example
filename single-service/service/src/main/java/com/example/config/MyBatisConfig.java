package com.example.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * MyBatis相关配置
 */
@Configuration
@MapperScan(basePackages = "com.example.generator.mapper")
public class MyBatisConfig {
}
