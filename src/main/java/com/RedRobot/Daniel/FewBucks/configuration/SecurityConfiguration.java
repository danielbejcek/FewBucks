package com.RedRobot.Daniel.FewBucks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterhain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
            registry.requestMatchers("/").permitAll();
            registry.requestMatchers("/register").permitAll();
            registry.requestMatchers("/login").permitAll();
            registry.requestMatchers("/api/admin/**").hasRole("ADMIN");
            registry.requestMatchers("/api/auth/**").hasRole("USER");
            registry.requestMatchers("/api/public/**").permitAll();
            registry.anyRequest().authenticated();
        }).build();
    }


    /*Access levels needs to be implemented*/
}
