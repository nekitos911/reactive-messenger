package ru7.hw.messaging.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ru7.hw.messaging.security.MD5PasswordEncoder;
import ru7.hw.messaging.service.UserDetailService;
import ru7.hw.messaging.utils.UserUtils;

@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserUtils userUtils;
    private final UserDetailService userDetailService;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return (username) -> userDetailService.getUserByUsername(username).doOnSuccess((user) -> userUtils.setUsername(user.getUsername()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
    }

}
