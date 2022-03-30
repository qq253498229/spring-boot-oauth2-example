package com.example.user.bean;

import com.example.user.valid.ValidToken;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserChangePasswordVO {
    @NotBlank(message = "token不能为空")
    @ValidToken
    private String token;
    @NotBlank(message = "密码不能为空")
    private String password;
}
