package com.example.user;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * 用户接口测试
 */
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Resource
    MockMvc mockMvc;
}