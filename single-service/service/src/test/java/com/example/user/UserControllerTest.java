package com.example.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Resource
    MockMvc mockMvc;
    @MockBean
    UserService userService;

    @Test
    @WithMockUser
    void list() throws Exception {
        UserVO userVO = new UserVO();
        Mockito.doReturn(List.of(userVO)).when(userService).findAllFetchRoleAndResource();
        mockMvc.perform(get("/oauth/user"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
        ;
    }

    @Test
    void list_401() throws Exception {
        mockMvc.perform(get("/oauth/user"))
                .andExpect(status().isUnauthorized())
        ;
    }
}