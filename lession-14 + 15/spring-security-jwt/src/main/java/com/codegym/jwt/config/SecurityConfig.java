package com.codegym.jwt.config;

import com.codegym.jwt.CustomAccessDeniedHandle;
import com.codegym.jwt.JwtAuthenticationTokenFilter;
import com.codegym.jwt.RestAuthenticationEntryPoint;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandle customAccessDeniedHandler() {
        return new CustomAccessDeniedHandle();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/api/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeHttpRequests()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(new String[]{"ADMIN", "USER"})
                .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
