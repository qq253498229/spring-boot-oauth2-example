package com.example.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义注销相关处理逻辑
 */
@Service
public class CustomLogoutHandler implements LogoutHandler, LogoutSuccessHandler {
    @Resource
    TokenStore tokenStore;

    /**
     * 注销逻辑
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 从请求中获取token，这一步也可以根据自己实际使用场景来获取，比如在header中取等等
        String token = request.getParameter("token");
        if (token == null) {
            return;
        }
        // 在token存储器中查询token
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (oAuth2AccessToken == null) {
            return;
        }
        if (oAuth2AccessToken.getRefreshToken() != null) {
            // 在token存储中移除refresh_token
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
        // 在token存储中移除access_token
        tokenStore.removeAccessToken(oAuth2AccessToken);
    }

    /**
     * 注销成功后逻辑
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 在请求中获取重定向地址，这一步也可以根据自己实际使用场景来获取，比如在header中取等等
        String redirect = request.getParameter("redirect");
        // 重定向到新地址
        response.sendRedirect(redirect);
    }
}
