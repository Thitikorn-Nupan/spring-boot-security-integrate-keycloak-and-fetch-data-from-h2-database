package com.ttknp.springbootcrudh2securitywithkeycloak.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;


/**
 * SecurityConfig class configures security settings for the application,
 * enabling security filters and setting up OAuth2 login and logout behavior.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Configures the security filter chain for handling HTTP requests, OAuth2 login, and logout.
     * @param http HttpSecurity object to define web-based security at the HTTP level
     * @return SecurityFilterChain for filtering and securing HTTP requests
     * @throws Exception in case of an error during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configures authorization rules for different endpoints
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/api/students/").authenticated() // Allows public access to the root URL
                        .requestMatchers(HttpMethod.GET,"/api/students").authenticated() // Allows public access to the root URL
                        .requestMatchers(HttpMethod.GET,"/api/students/whoami").authenticated() // Allows public access to the root URL
                        .anyRequest().authenticated() // Requires authentication for any other request
                )
                // Configures OAuth2 login settings
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/api/students/", true) // Redirects to "/***" after successful login
                )
                /*
                // Configures logout settings *** Issue logout didn't clear session
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/server") // Redirects to the root URL on successful logout
                        .invalidateHttpSession(true) // Invalidates session to clear session data
                        .clearAuthentication(true) // Clears authentication details
                        .deleteCookies("JSESSIONID") // Deletes the session cookie
                )*/
                // ** Allow to access ui h2 database
                .csrf().ignoringRequestMatchers("/console/**")
                .and()
                .headers().frameOptions().sameOrigin();
        return http
                .build();
    }



}
