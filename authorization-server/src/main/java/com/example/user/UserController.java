package com.example.user;

import com.example.user.bean.UserRegisterVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/oauth/register")
    public void register(@RequestBody @Validated UserRegisterVO userRegisterVO) {
        userService.register(userRegisterVO);
    }
}
