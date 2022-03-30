package com.example.user;

import cn.hutool.json.JSONUtil;
import com.example.config.TestResourceConfig;
import com.example.user.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ActiveProfiles("unit-test")
@Import(TestResourceConfig.class)
class UserControllerTest {
    @Resource
    MockMvc mockMvc;
    @MockBean
    UserService userService;

    /**
     * 用户列表
     */
    @Test
    @WithMockUser(authorities = {"showUserList"})
    void userList() throws Exception {
        Mockito.when(userService.userList()).thenReturn(Arrays.asList(new UserVO(), new UserVO()));
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print())
        ;
    }

    @Test
    void userList_401() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser
    void userList_403() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    void showUserDetail_401() throws Exception {
        mockMvc.perform(get("/user/details/{id}", 1))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser
    void showUserDetail_403() throws Exception {
        mockMvc.perform(get("/user/details/{id}", 1))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    @WithMockUser(authorities = {"showUserDetail"})
    void showUserDetail() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setUsername("test");
        userVO.setAge(12);
        userVO.setEmail("test@test.com");
        Mockito.when(userService.showUserDetail(1)).thenReturn(userVO);
        mockMvc.perform(get("/user/details/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.age").value(12))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andDo(print())
        ;
    }

    @Test
    @WithMockUser(authorities = {"resetUserPassword"})
    void resetUserPassword() throws Exception {
        String content = JSONUtil.createObj()
                .set("username", "username")
                .set("password", "password")
                .toString();
        mockMvc.perform(post("/user/resetUserPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test
    void resetUserPassword_401() throws Exception {
        String content = JSONUtil.createObj()
                .set("username", "username")
                .set("password", "password")
                .toString();
        mockMvc.perform(post("/user/resetUserPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser
    void resetUserPassword_403() throws Exception {
        String content = JSONUtil.createObj()
                .set("username", "username")
                .set("password", "password")
                .toString();
        mockMvc.perform(post("/user/resetUserPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isForbidden())
        ;
    }
}