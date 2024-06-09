package com.mymusic.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfiguration {

    // @Bean
    // public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
    //     httpSecurity
    //             .authorizeExchange(exchange -> {
    //                 exchange.pathMatchers("/auth/**").permitAll()
    //                         .anyExchange().authenticated();
    //             })
    //             .oauth2ResourceServer(oauth2 -> {
    //                 oauth2.jwt(jwt -> {
    //                     jwt.
    //                 });
    //             });
    // }
}
