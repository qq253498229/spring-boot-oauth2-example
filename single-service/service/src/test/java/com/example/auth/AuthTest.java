package com.example.auth;

import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONObject;
import com.example.utils.TestRequestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.oauth2.common.util.OAuth2Utils.*;

/**
 * 权限测试
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit-test")
public class AuthTest {
    @Resource
    TestRestTemplate testRestTemplate;
    public static final String TEST_CLIENT = "client";
    public static final String TEST_SECRET = "secret";
    public static final String TEST_USERNAME = "user";
    public static final String TEST_PASSWORD = "user";

    /**
     * password方式获取token
     */
    @Test
    void getToken_password() {
        HttpEntity<MultiValueMap<String, String>> entity = TestRequestUtils.urlEncodedRequest()
                .put(GRANT_TYPE, "password")
                .put("username", TEST_USERNAME)
                .put("password", TEST_PASSWORD)
                .build();
        ResponseEntity<JSONObject> exchange = testRestTemplate.withBasicAuth(TEST_CLIENT, TEST_SECRET)
                .exchange("/oauth/token", HttpMethod.POST, entity, JSONObject.class);

        assertTokenResult(exchange);

        assertNotNull(exchange.getBody());
        String token = exchange.getBody().getStr("access_token");

        // jwt单体服务模式一般不会用到，在资源服务器常用
        HttpEntity<MultiValueMap<String, String>> entity1 = TestRequestUtils.urlEncodedRequest()
                .put("token", token).build();
        ResponseEntity<JSONObject> exchange1 = testRestTemplate
                .withBasicAuth(TEST_CLIENT, TEST_SECRET)
                .exchange("/oauth/check_token", HttpMethod.POST, entity1, JSONObject.class);

        JSONObject body1 = exchange1.getBody();

        assertEquals(200, exchange1.getStatusCodeValue());
        assertNotNull(body1);
        assertEquals(TEST_USERNAME, body1.getStr("user_name"));
        assertTrue(body1.getBool("active"));
        assertEquals(2, body1.getJSONArray("authorities").size());
        assertEquals(TEST_CLIENT, body1.getStr("client_id"));
        assertEquals(1, body1.getJSONArray("scope").size());
        assertNotNull(body1.getInt("exp"));
        assertNotNull(body1.getStr("jti"));

        String refreshToken = exchange.getBody().getStr("refresh_token");

        HttpEntity<MultiValueMap<String, String>> entity2 = TestRequestUtils.urlEncodedRequest()
                .put(GRANT_TYPE, "refresh_token")
                .put("refresh_token", refreshToken)
                .build();
        ResponseEntity<JSONObject> exchange2 = testRestTemplate.withBasicAuth(TEST_CLIENT, TEST_SECRET)
                .exchange("/oauth/token", HttpMethod.POST, entity2, JSONObject.class);

        assertTokenResult(exchange2);
    }

    private void assertTokenResult(ResponseEntity<JSONObject> exchange) {
        JSONObject body = exchange.getBody();

        assertEquals(200, exchange.getStatusCodeValue());
        assertNotNull(body);
        assertNotNull(body.get("token_type"));
        assertNotNull(body.get("access_token"));
        assertNotNull(body.get("refresh_token"));
        assertNotNull(body.get("scope"));
        assertNotNull(body.get("expires_in"));
        assertNotNull(body.get("jti"));
    }

    /**
     * code方式获取token
     */
    @Test
    void getToken_code() {
        String redirectUri = "http://localhost:4200/login";
        HttpEntity<MultiValueMap<String, String>> entity = TestRequestUtils.urlEncodedRequest()
                .put(RESPONSE_TYPE, "code")
                .put(REDIRECT_URI, redirectUri)
                .put(CLIENT_ID, TEST_CLIENT)
                .build();

        ResponseEntity<String> exchange = testRestTemplate.withBasicAuth(TEST_USERNAME, TEST_PASSWORD)
                .exchange("/oauth/authorize", HttpMethod.POST, entity, String.class);

        assertTrue(exchange.getStatusCodeValue() == 303 || exchange.getStatusCodeValue() == 302);
        assertNotNull(exchange.getHeaders().getLocation());
        String query = exchange.getHeaders().getLocation().getQuery();
        assertTrue(ReUtil.contains("code=", query));

        String code = ReUtil.findAllGroup0("(?<=code=)\\S+", query).get(0);

        HttpEntity<MultiValueMap<String, String>> entity1 = TestRequestUtils.urlEncodedRequest()
                .put(GRANT_TYPE, "authorization_code")
                .put(REDIRECT_URI, redirectUri)
                .put("code", code)
                .build();
        ResponseEntity<JSONObject> exchange1 = testRestTemplate.withBasicAuth(TEST_CLIENT, TEST_SECRET)
                .exchange("/oauth/token", HttpMethod.POST, entity1, JSONObject.class);

        assertTokenResult(exchange1);
    }

    /**
     * 注册
     */
    @Test
    void register() {
        HttpEntity<JSONObject> entity = TestRequestUtils.jsonRequest().set("username", "testUsername").build();
        ResponseEntity<JSONObject> exchange = testRestTemplate.exchange("/oauth/register", HttpMethod.POST, entity, JSONObject.class);

        assertEquals(400, exchange.getStatusCodeValue());
        JSONObject body = exchange.getBody();
        assertNotNull(body);
        assertEquals("密码不能为空", body.getByPath("errors[0].defaultMessage"));

        entity = TestRequestUtils.jsonRequest().set("password", "testPassword").build();
        exchange = testRestTemplate.exchange("/oauth/register", HttpMethod.POST, entity, JSONObject.class);

        assertEquals(400, exchange.getStatusCodeValue());
        body = exchange.getBody();
        assertNotNull(body);
        assertEquals("用户名不能为空", body.getByPath("errors[0].defaultMessage"));

        entity = TestRequestUtils.jsonRequest()
                .set("username", "testUsername")
                .set("password", "testPassword").build();
        exchange = testRestTemplate.exchange("/oauth/register", HttpMethod.POST, entity, JSONObject.class);

        assertEquals(200, exchange.getStatusCodeValue());

        HttpEntity<MultiValueMap<String, String>> entity1 = TestRequestUtils.urlEncodedRequest()
                .put(GRANT_TYPE, "password")
                .put("username", "testUsername")
                .put("password", "testPassword")
                .build();
        exchange = testRestTemplate.withBasicAuth(TEST_CLIENT, TEST_SECRET)
                .exchange("/oauth/token", HttpMethod.POST, entity1, JSONObject.class);

        assertTokenResult(exchange);

        exchange = testRestTemplate.exchange("/oauth/register", HttpMethod.POST, entity, JSONObject.class);

        assertEquals(400, exchange.getStatusCodeValue());
        body = exchange.getBody();
        assertNotNull(body);
        assertEquals("用户名已存在", body.getByPath("errors[0].defaultMessage"));
    }

    /**
     * 注销
     */
    @Test
    void logout() {

    }
}
