package com.example.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/oauth/user")
    public List<UserVO> list() {
        return userService.findAllFetchRoleAndResource();
    }
}
