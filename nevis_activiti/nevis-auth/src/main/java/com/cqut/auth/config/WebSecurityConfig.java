package com.cqut.auth.config;

import com.cqut.auth.auth.HeaderTokenWebFilter;
import com.cqut.auth.auth.JWTAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HeaderTokenWebFilter headerTokenWebFilter;

    @Autowired
    private JWTAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .addFilterBefore(headerTokenWebFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .and().authorizeRequests()
                .antMatchers("**").permitAll()
                .anyRequest().fullyAuthenticated();
    }
}
