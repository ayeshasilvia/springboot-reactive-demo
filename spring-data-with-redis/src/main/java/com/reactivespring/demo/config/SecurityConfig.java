package com.reactivespring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public MapReactiveUserDetailsService authentication(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new MapReactiveUserDetailsService(User.withUsername("user").password(encoder.encode("pass")).roles("USER").build(),
                User.withUsername("admin").password(encoder.encode("pass")).roles("USER", "ADMIN").build());
    }

    @Bean
    public SecurityWebFilterChain authorization(ServerHttpSecurity security){
        return security
                .httpBasic()
                .and()
                .authorizeExchange()
                .anyExchange().hasRole("ADMIN")
                .and().build();

    }
}
