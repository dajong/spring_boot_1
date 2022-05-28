package com.example.superDuperDrive.config;

import com.example.superDuperDrive.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private AuthenticationService authenticationService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider(this.authenticationService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
        .antMatchers("/signup", "/css/**", "/js/**").permitAll()
        .anyRequest().authenticated();

    http.formLogin()
        .loginPage("/login")
        .permitAll();

    http.formLogin()
        .defaultSuccessUrl("/home", true);
  }
}