package com.HelloWorld.Daily.jwtConfig;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.URLEncoder;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http, @Value("${jwt.exprired}") Long exprired) throws Exception {
        // 스프링부트 3.1.x~ 시큐리티 설정 방식이 변경됨. .and()를 사용하지 않음
        http
                .csrf().disable()  // CSRF 보호 비활성화
                .sessionManagement(configurer-> // 세션 사용안해서 STATELESS 상태로 설정
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize->
                        authorize
                                .requestMatchers("/login", "/signup", "/", "/error").permitAll() // 페이지
                                .requestMatchers("/login/**", "/signup/**").permitAll() // API
                                .requestMatchers("/**").permitAll() // CSS, JS 파일 허용
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login	// form 방식 로그인 사용
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .usernameParameter("userName")			// 아이디 파라미터명 설정
                        .passwordParameter("userPassword")			// 패스워드 파라미터명 설정
                        .successHandler(
                                (request, response, authentication) -> {
                                     JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

                                     String accessToken = jwtToken.getGrantType() + " " + jwtToken.getAccessToken();

                                     String utf8Cookie = URLEncoder.encode(accessToken, "utf-8");

                                     Cookie accessCookie = new Cookie("Authorization", utf8Cookie);
                                     accessCookie.setMaxAge(exprired.intValue());
                                     accessCookie.setPath("/");
                                     accessCookie.setDomain("localhost");
                                     accessCookie.setSecure(false);

                                     response.addCookie(accessCookie);

                                     response.sendRedirect("/");

                                })
                        .failureHandler(
                                (request, response, exception) -> {
                                    System.out.println("exception : " + exception.getMessage());
                                    System.out.println("exception : " + Arrays.toString(exception.getStackTrace()));
                                    response.sendRedirect("/login");
                                }
                        )
                        .permitAll()
                ).addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
