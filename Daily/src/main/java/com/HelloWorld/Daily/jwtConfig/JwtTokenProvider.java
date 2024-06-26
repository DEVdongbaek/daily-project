package com.HelloWorld.Daily.jwtConfig;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    private final Long exprired;

    // yml 설정 파일에서 secret 값 가져온 후 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String secretkey, @Value("${jwt.exprired}") Long exprired) {

        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.exprired = exprired;
    }

    // Member 정보를 통해 AccessToken을 생성하는 메서드
    public JwtToken generateToken(Authentication authentication) {

        // 권한 가져오기
        String authorities = getAuthorities(authentication);

        long now = (new Date()).getTime();

        // AccessToken 생성
        Date accessTokenExpiresIn = new Date(now + exprired);

        String accessToken = newAccessToken(authentication.getName(), authorities, accessTokenExpiresIn);

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    // Jwt 토큰을 복호화 하여서 토큰에 있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {

        // JWT 토큰 복호화
        Claims claims = parseClaims(accessToken);

        // 권한들을 하나의 객체 Array로 추출
        Collection<? extends GrantedAuthority> authorities = getAuthoritiesFromJWT(claims);

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

    // Token 정보를 검증하는 메서드
    public boolean validateToken(HttpServletRequest request, String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {

            log.info("Expired JWT Token", e);

            Cookie[] list = request.getCookies();

            for(Cookie cookie:list) {
                if(cookie.getName().equals("Authorization")) {
                    cookie.setMaxAge(0);
                }
            }

        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // JWT 토근에서 권한 추출
    private Collection<? extends GrantedAuthority> getAuthoritiesFromJWT(Claims claims) {

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        return Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    // AccessToken을 생성
    private String newAccessToken(String subject, String authorities, Date accessTokenExpiresIn){
        return Jwts.builder()
                .setSubject(subject)
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // 권한들을 String으로 추출하는 메서드
    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
    }


    // AccessToken의 Body를 가져오는 메서드
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
