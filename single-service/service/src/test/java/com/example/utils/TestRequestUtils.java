package com.example.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 测试时构建请求参数
 */
public class TestRequestUtils {
    public static JsonRequestBuilder jsonRequest() {
        JsonRequestBuilder builder = new JsonRequestBuilder();
        builder.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return builder;
    }

    public static UrlEncodedRequestBuilder urlEncodedRequest() {
        UrlEncodedRequestBuilder builder = new UrlEncodedRequestBuilder();
        builder.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return builder;
    }

    @Data
    public static class UrlEncodedRequestBuilder {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();

        public UrlEncodedRequestBuilder put(String key, String value) {
            this.getBody().add(key, value);
            return this;
        }

        public HttpEntity<MultiValueMap<String, String>> build() {
            return new HttpEntity<>(body, headers);
        }
    }

    @Data
    public static class JsonRequestBuilder {
        HttpHeaders headers = new HttpHeaders();
        JSONObject body = JSONUtil.createObj();

        public JsonRequestBuilder set(String key, String value) {
            this.getBody().set(key, value);
            return this;
        }

        public HttpEntity<JSONObject> build() {
            return new HttpEntity<>(body, headers);
        }
    }
}
