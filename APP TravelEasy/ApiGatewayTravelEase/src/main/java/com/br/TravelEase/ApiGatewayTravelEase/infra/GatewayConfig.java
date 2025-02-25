package com.br.TravelEase.ApiGatewayTravelEase.infra;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public GatewayConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 🔓 Rota aberta (Login)
                .route("Auth", r -> r
                        .path("/login", "/user/codeVerification","/user/register")
                        .uri("http://localhost:8081"))
                .route("Auth", r -> r
                        .path("/login/teste")
                        .filters(f -> f.filter(jwtAuthenticationFilter)) // 🚨 Aplica o filtro
                        .uri("http://localhost:8081"))

                // 🔒 Rota protegida (Usuário precisa de um token para acessar)
                .route("Email", r -> r
                        .path("/email")
                        .filters(f -> f.filter(jwtAuthenticationFilter)) // 🚨 Aplica o filtro
                        .uri("http://localhost:8087"))

                .route("Tickets", r -> r
                        .path("/tickets/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)) // 🚨 Aplica o filtro
                        .uri("http://localhost:8083"))
                .build();
    }
}
