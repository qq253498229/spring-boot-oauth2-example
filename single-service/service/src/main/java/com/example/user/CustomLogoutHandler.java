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

@Service
public class CustomLogoutHandler implements LogoutHandler, LogoutSuccessHandler {
    @Resource
    TokenStore tokenStore;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getParameter("token");
        if (token == null) {
            return;
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if (oAuth2AccessToken == null) {
            return;
        }
        if (oAuth2AccessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
        tokenStore.removeAccessToken(oAuth2AccessToken);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String redirect = request.getParameter("redirect");
        response.sendRedirect(redirect);
    }
}
