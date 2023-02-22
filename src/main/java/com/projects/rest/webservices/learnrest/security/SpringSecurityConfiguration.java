package com.projects.rest.webservices.learnrest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
            1. All requests should be authenticated
            2. If a request is not authenticated, a web page is shown
            3. CSRF -> POST, PUT
         */

        // #1. All requests should be authenticated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // #2. If a request is not authenticated, by default a web page is shown. Instead, we want basic authentication
        http.httpBasic(Customizer.withDefaults());

        // #3. CSRF -> POST, PUT
        // CSRF => Cross Site Request Forgery
        http.csrf().disable();

        return http.build();
    }
}
