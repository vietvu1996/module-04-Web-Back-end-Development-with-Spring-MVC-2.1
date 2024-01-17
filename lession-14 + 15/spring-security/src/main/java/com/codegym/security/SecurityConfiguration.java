package com.codegym.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}12345").roles("USER")
                .and().withUser("admin").password("{noop}12345").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/").permitAll()
                .requestMatchers("/user**").hasRole("USER")
                .requestMatchers("/admin**").hasRole("ADMIN")
                .and().formLogin().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
