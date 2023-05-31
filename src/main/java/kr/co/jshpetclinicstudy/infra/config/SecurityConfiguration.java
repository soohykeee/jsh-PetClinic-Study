package kr.co.jshpetclinicstudy.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // PostMan 사용을 위해 사용 -> 로그인 기능 구현 시 제거 예정
        http.httpBasic();

        // jwt 사용을 해줄 것 이기에 csrf 비활성화
        http
                .csrf().disable()
                .cors().disable();

        // Authorization (인가)
        http
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated();

        // Authentication (인증)
        http
                .formLogin().permitAll();

        return http.build();
    }

}
