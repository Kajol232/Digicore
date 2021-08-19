package com.example.muhammad.amotul.digicore.security;

import com.example.muhammad.amotul.digicore.security.jwt.JWTAuthEntryPoint;
import com.example.muhammad.amotul.digicore.security.jwt.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTFilter filter;
    @Autowired
    private JWTAuthEntryPoint authEntryPoint;
    @Override
    protected void configure(HttpSecurity security) throws Exception{
        security.cors().and().httpBasic().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/accounts/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
