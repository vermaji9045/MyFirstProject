package com.School.SchoolValleyProject.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/course").permitAll()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                 .mvcMatchers("/courses").permitAll()
                 .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/login").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("User")
                .and()
                .withUser("admin").password("123456").roles("User","Admin")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}