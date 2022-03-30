package com.example.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 查看用户列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('showUserList')")
    public List<UserVO> userList() {
        return userService.userList();
    }

    /**
     * 查看用户详情
     */
    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('showUserDetail')")
    public UserVO showUserDetail(@PathVariable Integer id) {
        return userService.showUserDetail(id);
    }
}
