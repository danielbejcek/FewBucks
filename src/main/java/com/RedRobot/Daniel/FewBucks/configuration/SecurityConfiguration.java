package com.RedRobot.Daniel.FewBucks.configuration;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                /*Setting up the access levels for USERS/GUESTS */
                .authorizeHttpRequests(registry -> {
            registry.requestMatchers("/","/register/**","/login/**").permitAll();
            registry.requestMatchers("/api/public/**").permitAll();
            registry.requestMatchers("/api/admin/**").hasRole("ADMIN");
            registry.requestMatchers("/api/auth/**").hasAnyRole("USER","ADMIN");
            registry.anyRequest().authenticated();

        })       /*Creates a Form-based authentication*/
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)

                /*Method that finalizes the security configuration and creates the SecurityFilterChain bean.*/
                .build();
    }

    /*Method responsible for authenticating a user within the Spring Security context.
    retrieves user details from a data source and verifies the user's credentials.
    When a user attempts to log in, DaoAuthenticationProvider calls UserDetailsService to load the user details
    and then uses PasswordEncoder to check the provided password against the stored one.*/
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        /*userDetailsService is ann instance of UserDetailsService that is defined in 'UserDetailService.java',
        which loads user details like username and password from postgreSQL databse.*/
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /*BCrypt is designed to incorporate a salt internally, and it generates a new salt each time new password is hashed.*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
