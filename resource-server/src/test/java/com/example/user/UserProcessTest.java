package com.example.user;

import cn.hutool.json.JSONUtil;
import com.example.config.TestResourceConfig;
import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import com.example.user.vo.ResetUserPasswordVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.security.Principal;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口测试
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
@Import(TestResourceConfig.class)
public class UserProcessTest {
    @Resource
    MockMvc mockMvc;
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    /**
     * {@link UserController#userList()}
     */
    @Test
    @WithMockUser(authorities = {"showUserList"})
    void userList() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].name").value("测试用户"))
                .andExpect(jsonPath("$[0].age").value(12))
                .andExpect(jsonPath("$[0].email").value("test@test.com"))
                .andExpect(jsonPath("$[0].gender").value(1))
                .andExpect(jsonPath("$[0].username").value("user"))

                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].userId").value(2))
                .andExpect(jsonPath("$[1].name").value("管理员用户"))
                .andExpect(jsonPath("$[1].age").value(66))
                .andExpect(jsonPath("$[1].email").value("admin@test.com"))
                .andExpect(jsonPath("$[1].gender").value(2))
                .andExpect(jsonPath("$[1].username").value("admin"))
                .andDo(print())
        ;
    }

    /**
     * {@link UserController#showUserDetail(Integer)}
     */
    @Test
    @WithMockUser(authorities = {"showUserDetail"})
    void showUserDetail() throws Exception {
        mockMvc.perform(get("/user/details/{id}", 1))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("测试用户"))
                .andExpect(jsonPath("$.age").value(12))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.gender").value(1))
                .andExpect(jsonPath("$.username").value("user"))
        ;
        mockMvc.perform(get("/user/details/{id}", 2))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("管理员用户"))
                .andExpect(jsonPath("$.age").value(66))
                .andExpect(jsonPath("$.email").value("admin@test.com"))
                .andExpect(jsonPath("$.gender").value(2))
                .andExpect(jsonPath("$.username").value("admin"))
        ;
    }

    /**
     * {@link UserController#resetUserPassword(ResetUserPasswordVO)}
     */
    @Test
    @WithMockUser(authorities = {"resetUserPassword"})
    void resetUserPassword() throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        assertTrue(passwordEncoder.matches("user", user.getPassword()));
        String content = JSONUtil.createObj()
                .set("username", "user")
                .set("password", "user1")
                .toString();
        mockMvc.perform(post("/user/resetUserPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isOk())
                .andDo(print())
        ;
        User user1 = userMapper.selectByPrimaryKey(1);
        assertTrue(passwordEncoder.matches("user1", user1.getPassword()));
    }

    /**
     * {@link UserController#showPersonalRole(Principal)}
     */
    @Test
    @WithMockUser(authorities = {"showPersonalRole"})
    void showPersonalRole() throws Exception {
        mockMvc.perform(get("/user/showPersonalRole"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").value("普通用户"))
                .andDo(print())
        ;
    }

    /**
     * {@link UserController#showPersonalDetail(Principal)}
     */
    @Test
    @WithMockUser(username = "user", authorities = {"showPersonalDetail"})
    void showPersonalDetail() throws Exception {
        mockMvc.perform(get("/user/showPersonalDetail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("测试用户"))
                .andExpect(jsonPath("$.age").value(12))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.gender").value(1))
                .andDo(print())
        ;
    }

    /**
     * {@link UserController#showPersonalDetail(Principal)}
     */
    @Test
    @WithMockUser(username = "admin", authorities = {"showPersonalDetail"})
    void showPersonalDetail_1() throws Exception {
        mockMvc.perform(get("/user/showPersonalDetail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("管理员用户"))
                .andExpect(jsonPath("$.age").value(66))
                .andExpect(jsonPath("$.email").value("admin@test.com"))
                .andExpect(jsonPath("$.gender").value(2))
                .andDo(print())
        ;
    }
}
