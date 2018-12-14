/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.boot.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Sharath.Kulal
 */
@Configuration
@Order(2)
public class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/user/**")
                .authorizeRequests().anyRequest().hasRole("USER")
                .and()
                // formLogin configuration
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(
                        loginUrlauthenticationEntryPointWithWarning(),
                        new AntPathRequestMatcher("/user/private/**"))
                .defaultAuthenticationEntryPointFor(
                        loginUrlauthenticationEntryPoint(),
                        new AntPathRequestMatcher("/user/general/**"));
    }

    @Bean
    public AuthenticationEntryPoint loginUrlauthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/userLogin");
    }

    @Bean
    public AuthenticationEntryPoint loginUrlauthenticationEntryPointWithWarning() {
        return new LoginUrlAuthenticationEntryPoint("/userLoginWithWarning");
    }

}
