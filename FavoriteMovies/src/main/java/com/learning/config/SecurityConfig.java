package com.learning.config;

import com.learning.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//Чтобы не делать дополнительный проект для различных типов аутентификации
//будем перенастраивать SecurityConfig

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).
                withUser("admin").password(passwordEncoder.encode("admin123")).
                roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Конфигурация с custom login формой
//        http.authorizeRequests().antMatchers("/",
//                "/index", "/login").permitAll().
//                antMatchers("/**").hasAnyRole("ADMIN").
//                and().formLogin().loginPage("/login").permitAll().
//                failureHandler(new CustomAuthenticationFailureHandler()).
//                and().logout().logoutSuccessUrl("/welcome").permitAll().
//                and().csrf().disable();

        // Конфигурация с BasicHttp аунтефикацией
        http.authorizeRequests().antMatchers("/",
                "/index", "/login").permitAll().
                antMatchers("/**").hasAnyRole("ADMIN").
                and().httpBasic().
                and().logout().logoutSuccessUrl("/welcome").permitAll().
                and().csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
