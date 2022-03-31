package com.example.user.valid;

import com.example.generator.model.User;
import com.example.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import static java.lang.annotation.ElementType.*;

/**
 * 校验UserId是否是自己的，在修改用户信息之前使用
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidUserId.UserIdValidator.class)
public @interface ValidUserId {
    String message() default "用户id不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UserIdValidator implements ConstraintValidator<ValidUserId, Integer> {
        @Resource
        UserService userService;

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserByUsername(username);
            return Objects.equals(user.getId(), value);
        }
    }
}
