package com.devxminds.donpipe.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private static final String LOGIN_PAGE_URL = "/login";
    private static final String LOGIN_PROCESSING_URL = "/auth";

    @Bean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(authz -> authz
                            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // Allow access to static resources
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/index").authenticated()
                            .anyRequest().authenticated())
                    .formLogin(form -> form
                            .loginPage(LOGIN_PAGE_URL)
                            .loginProcessingUrl(LOGIN_PROCESSING_URL)
                            .defaultSuccessUrl("/index",true)
                            .permitAll()
                    );
            return http.build();
    }
}


