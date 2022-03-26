package com.example.auth;

import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.oauth2.common.util.OAuth2Utils.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 权限测试
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
public class AuthTest {
    @Resource
    MockMvc mockMvc;
    public static final String TEST_CLIENT = "client";
    public static final String TEST_SECRET = "secret";
    public static final String TEST_USERNAME = "user";
    public static final String TEST_PASSWORD = "user";

    /**
     * password方式获取token
     */
    @Test
    void getToken_password() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/oauth/token")
                        .with(httpBasic(TEST_CLIENT, TEST_SECRET))
                        .param(GRANT_TYPE, "password")
                        .param("username", TEST_USERNAME)
                        .param("password", TEST_PASSWORD)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", notNullValue()))
                .andExpect(jsonPath("$.token_type", notNullValue()))
                .andExpect(jsonPath("$.refresh_token", notNullValue()))
                .andExpect(jsonPath("$.expires_in", notNullValue()))
                .andExpect(jsonPath("$.scope", notNullValue()))
                .andExpect(jsonPath("$.jti", notNullValue()))
                .andReturn();

        JSONObject jsonObject = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString());
        String token = jsonObject.getStr("access_token");

        // jwt模式一般不会用到
        mockMvc.perform(
                post("/oauth/check_token")
                        .with(httpBasic(TEST_CLIENT, TEST_SECRET))
                        .param("token", token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.exp").isNotEmpty())
                .andExpect(jsonPath("$.exp").isNumber())
                .andExpect(jsonPath("$.user_name").isNotEmpty())
                .andExpect(jsonPath("$.user_name").value(TEST_USERNAME))
                .andExpect(jsonPath("$.jti").isNotEmpty())
                .andExpect(jsonPath("$.client_id").value(TEST_CLIENT))
                .andExpect(jsonPath("$.scope").isNotEmpty())
                .andExpect(jsonPath("$.scope").isArray())
                .andExpect(jsonPath("$.authorities").isNotEmpty())
                .andExpect(jsonPath("$.authorities").isArray())
        ;

        String refreshToken = jsonObject.getStr("refresh_token");
        mockMvc.perform(
                post("/oauth/token")
                        .with(httpBasic(TEST_CLIENT, TEST_SECRET))
                        .param(GRANT_TYPE, "refresh_token")
                        .param("refresh_token", refreshToken)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", notNullValue()))
                .andExpect(jsonPath("$.token_type", notNullValue()))
                .andExpect(jsonPath("$.refresh_token", notNullValue()))
                .andExpect(jsonPath("$.expires_in", notNullValue()))
                .andExpect(jsonPath("$.scope", notNullValue()))
                .andExpect(jsonPath("$.jti", notNullValue()))
        ;
    }

    /**
     * code方式获取token
     */
    @Test
    void getToken_code() throws Exception {
        String redirectUri = "http://localhost:4200/login";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/oauth/authorize")
                        .with(httpBasic(TEST_USERNAME, TEST_PASSWORD))
                        .param(RESPONSE_TYPE, "code")
                        .param(REDIRECT_URI, redirectUri)
                        .param(CLIENT_ID, TEST_CLIENT)
        )
                .andExpect(status().is3xxRedirection())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");
        assertTrue(ReUtil.contains("code=", location));
        String code = ReUtil.findAllGroup0("(?<=code=)\\S+", location).get(0);

        mockMvc.perform(
                post("/oauth/token")
                        .with(httpBasic(TEST_CLIENT, TEST_SECRET))
                        .param(GRANT_TYPE, "authorization_code")
                        .param(REDIRECT_URI, redirectUri)
                        .param("code", code)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token", notNullValue()))
                .andExpect(jsonPath("$.token_type", notNullValue()))
                .andExpect(jsonPath("$.refresh_token", notNullValue()))
                .andExpect(jsonPath("$.expires_in", notNullValue()))
                .andExpect(jsonPath("$.scope", notNullValue()))
                .andExpect(jsonPath("$.jti", notNullValue()))
        ;
    }

    /**
     * 注册
     */
    @Test
    void register() {

    }

    /**
     * 注销
     */
    @Test
    void logout() {

    }
}
