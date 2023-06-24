package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .addFilterAfter(new CSRFTokenGeneratorFilter(), CsrfFilter.class)
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/tasks/**").permitAll()
                .requestMatchers("/tasks/all").permitAll()
                .requestMatchers("/user/create").permitAll()
                .requestMatchers("/user/all").permitAll()
                .requestMatchers("/user/login").permitAll()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
