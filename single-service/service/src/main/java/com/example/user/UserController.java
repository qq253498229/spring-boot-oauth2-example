package com.example.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @PostMapping("/oauth/register")
    public void register(@RequestBody @Validated UserRegisterVO userRegisterVO) {
        userService.register(userRegisterVO);
    }
}
