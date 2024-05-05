package com.devxminds.donpipe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    private static final String LOGIN_PAGE_URL = "/login";
    private static final String LOGIN_PROCESSING_URL = "/auth/login";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // Allow access to static resources
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .anyRequest().permitAll())

                .formLogin(form -> form
                            .loginPage(LOGIN_PAGE_URL).permitAll()
                            .defaultSuccessUrl("/index"))
                .build();
    }

}


