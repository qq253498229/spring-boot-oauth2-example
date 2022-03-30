package com.example.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册用户时使用的vo
 */
@Data
public class UserRegisterVO {
    @NotBlank(message = "用户名不能为空")
    @ValidUsername
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
