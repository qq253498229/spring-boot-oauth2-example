package com.example.user;

import com.example.configuration.TestResourceServerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口测试
 */
@SpringBootTest
@Import(TestResourceServerConfig.class)
@AutoConfigureMockMvc
class UserControllerTest {
    public static final String USER_LIST = "/oauth/user";
    @Resource
    MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = {"showUserList"})
    void list() throws Exception {
        mockMvc.perform(get(USER_LIST))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
        ;
    }

    @Test
    @WithMockUser
    void list_403() throws Exception {
        mockMvc.perform(get(USER_LIST))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    void list_401() throws Exception {
        mockMvc.perform(get(USER_LIST))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    void showPersonalRole_401() throws Exception {
        mockMvc.perform(get("/oauth/user/role"))
                .andExpect(status().isUnauthorized())
        ;
    }

    @WithMockUser
    @Test
    void showPersonalRole_403() throws Exception {
        mockMvc.perform(get("/oauth/user/role"))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    @WithMockUser(authorities = {"showPersonalRole"})
    void showPersonalRole() throws Exception {
        mockMvc.perform(get("/oauth/user/role"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("普通用户"))
        ;
    }
}