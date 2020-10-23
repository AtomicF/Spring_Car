package com.example.demo;

import com.example.demo.security.AuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableJpaRepositories("com.example.demo.repository")
@SpringBootApplication
public class CarsApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenProvider authenProvider;

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .authenticationProvider(authenProvider)
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
