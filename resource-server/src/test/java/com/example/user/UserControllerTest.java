package com.example.user;

import com.example.config.TestResourceConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}