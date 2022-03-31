package com.example.user;

import com.example.config.TestResourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
@Import(TestResourceConfig.class)
public class UserProcessTest {
    @Resource
    MockMvc mockMvc;

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
}
