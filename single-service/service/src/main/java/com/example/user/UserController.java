package com.example.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserService userService;

    /**
     * 查看用户列表
     */
    @GetMapping("/oauth/user")
    @PreAuthorize("hasAnyAuthority('showUserList')")
    public List<UserVO> list() {
        return userService.findAllFetchRoleAndResource();
    }

    /**
     * 查看个人角色
     */
    @GetMapping("/oauth/user/role")
    @PreAuthorize("hasAnyAuthority('showPersonalRole')")
    public List<String> showPersonalRole(Principal principal) {
        String username = principal.getName();
        return userService.showPersonalRole(username);
    }

    /**
     * 用户注册
     */
    @PostMapping("/oauth/register")
    public void register(@RequestBody @Validated UserRegisterVO userRegisterVO) {
        userService.register(userRegisterVO);
    }
}
