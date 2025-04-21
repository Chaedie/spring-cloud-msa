package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final Environment env;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/health_check", "/actuator/**", "/h2-console/**").permitAll()
                        .requestMatchers("/users", "POST").permitAll()
                        // .requestMatchers("/**")
                        // .access(new WebExpressionAuthorizationManager(
                        //         "hasIpAddress('127.0.0.1') or hasIpAddress('::1') or " +
                        //                 "hasIpAddress('172.30.1.44') or hasIpAddress('172.30.1.44/32')")) // host pc ip address


                        // .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
                        .anyRequest().permitAll()
                )
        // .formLogin(form -> form
        //         .loginPage("/login")
        //         .permitAll())
        ;

        http.addFilter(getAuthenticationFilter(authenticationManager));

        return http.build();
    }


    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(env, userService);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/login");

        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        return builder.build();
    }
}
