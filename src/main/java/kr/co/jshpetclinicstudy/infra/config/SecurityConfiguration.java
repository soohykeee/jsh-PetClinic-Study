package kr.co.jshpetclinicstudy.infra.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jshpetclinicstudy.infra.jwt.JwtAuthenticationFilter;
import kr.co.jshpetclinicstudy.infra.jwt.JwtProvider;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Authorization (인가)
        http
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/register", "/api/v1/login", "api/v1/refresh").permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/members/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll();

        // Authentication (인증)
        http
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                // 권한 없을 때 오류 발생 - 요청한 경로의 USER_ROLE이 적합하지 않을 때
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setStatus(ResponseStatus.FAIL_FORBIDDEN.getStatusCode().value());
                        response.getWriter().write(ResponseStatus.FAIL_FORBIDDEN.getMessage());
                    }
                })
                // 인증이 올바르지 않을 때 오류 발생 - token 오류
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setStatus(ResponseStatus.FAIL_UNAUTHORIZED.getStatusCode().value());
                        response.getWriter().write(ResponseStatus.FAIL_UNAUTHORIZED.getMessage());;
                    }
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
