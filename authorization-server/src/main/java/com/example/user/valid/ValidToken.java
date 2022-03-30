package com.example.user.valid;

import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 校验token是否存在，在修改密码之前使用
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidToken.TokenValidator.class)
public @interface ValidToken {
    String message() default "token无法识别";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class TokenValidator implements ConstraintValidator<ValidToken, String> {
        @Resource
        TokenStore tokenStore;

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return tokenStore.readAuthentication(value) != null;
        }
    }
}
