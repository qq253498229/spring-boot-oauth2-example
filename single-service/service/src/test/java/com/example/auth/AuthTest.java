package com.example.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.security.oauth2.common.util.OAuth2Utils.GRANT_TYPE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
public class AuthTest {
    @Resource
    MockMvc mockMvc;
    public static final String CLIENT_ID = "client";
    public static final String CLIENT_SECRET = "secret";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "user";

    @Test
    void getToken_password() throws Exception {
        mockMvc.perform(
                post("/oauth/token")
                        .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                        .param(GRANT_TYPE, "password")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
        )
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test
    void getToken_code() {

    }

    @Test
    void register() {

    }


}
