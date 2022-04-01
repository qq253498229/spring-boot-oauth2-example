package com.example.user;

import com.example.user.vo.ResetUserPasswordVO;
import com.example.user.vo.UserDetailVO;
import com.example.user.vo.UserVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('showUserList')")
    public List<UserVO> userList() {
        return userService.userList();
    }

    /**
     * 查看用户详情
     */
    @GetMapping("/user/details/{id}")
    @PreAuthorize("hasAuthority('showUserDetail')")
    public UserVO showUserDetail(@PathVariable Integer id) {
        return userService.showUserDetail(id);
    }

    /**
     * 重置用户密码
     */
    @PostMapping("/user/resetUserPassword")
    @PreAuthorize("hasAuthority('resetUserPassword')")
    public void resetUserPassword(@RequestBody @Validated ResetUserPasswordVO resetUserPasswordVO) {
        userService.resetUserPassword(resetUserPasswordVO);
    }

    /**
     * 查看个人角色
     */
    @GetMapping("/user/showPersonalRole")
    @PreAuthorize("hasAuthority('showPersonalRole')")
    public List<String> showPersonalRole(Principal principal) {
        String username = principal.getName();
        return userService.showPersonalRole(username);
    }

    /**
     * 查看个人信息
     */
    @GetMapping("/user/showPersonalDetail")
    @PreAuthorize("hasAuthority('showPersonalDetail')")
    public UserDetailVO showPersonalDetail(Principal principal) {
        String username = principal.getName();
        return userService.showPersonalDetail(username);
    }

    /**
     * 编辑个人信息
     */
    @PostMapping("/user/updatePersonalDetail")
    @PreAuthorize("hasAuthority('updatePersonalDetail')")
    public void updatePersonalDetail(@RequestBody UserDetailVO userDetailVO, Principal principal) {
        String username = principal.getName();
        userService.updatePersonalDetail(userDetailVO, username);
    }
}
