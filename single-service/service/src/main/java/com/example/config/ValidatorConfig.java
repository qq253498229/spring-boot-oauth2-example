package com.example.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 校验相关配置
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator(AutowireCapableBeanFactory autowireCapableBeanFactory) {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                // 在ConstraintValidator实现类中允许注入spring bean
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
                // 在校验到错误后立刻返回错误信息，而不是全部都进行校验
                .failFast(true)
                .buildValidatorFactory().getValidator();
    }
}
