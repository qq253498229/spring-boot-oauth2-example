package com.example.user.valid;

import com.example.user.UserService;

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
 * 校验用户名是否存在，在注册用户之前使用
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidUsername.UsernameValidator.class)
public @interface ValidUsername {
    String message() default "用户名已存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
        @Resource
        UserService userService;

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return userService.selectUserByUsername(value) == null;
        }
    }
}
