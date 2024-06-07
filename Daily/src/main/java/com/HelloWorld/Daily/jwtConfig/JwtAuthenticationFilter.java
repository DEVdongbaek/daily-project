package com.HelloWorld.Daily.jwtConfig;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) servletRequest);

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {

            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    // Request Hedaer에서 JWT 정보 추출
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = "";

        Cookie[] list = request.getCookies();

        for(Cookie cookie:list) {
            if(cookie.getName().equals("Authorization")) {
                bearerToken = cookie.getValue();
                logger.info(cookie.getValue());
            }
        }

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
