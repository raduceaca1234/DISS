package com.ubb.faculty_of_psychology.configuration;

import com.ubb.faculty_of_psychology.filters.AuthenticationFilter;
import com.ubb.faculty_of_psychology.filters.LoginFilter;
import com.ubb.faculty_of_psychology.service.AdminDetailsServiceImpl;
import com.ubb.faculty_of_psychology.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final AdminDetailsServiceImpl adminDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public SecurityConfiguration(AdminDetailsServiceImpl adminDetailsService, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService) {
        this.adminDetailsService = adminDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").access("hasRole('admin')")
                .antMatchers("/student/**").access("hasRole('student')")
                .antMatchers("/teacher/**").access("hasRole('teacher')")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/authenticate", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService).passwordEncoder(passwordEncoder);
    }
}
