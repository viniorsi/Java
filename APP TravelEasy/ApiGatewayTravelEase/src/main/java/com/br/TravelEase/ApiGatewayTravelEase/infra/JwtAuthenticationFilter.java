package com.br.TravelEase.ApiGatewayTravelEase.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    private static final String SECRET_KEY = "12345678"; // 🔴 Mesma chave usada no seu serviço de autenticação
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 🚨 Se não houver token, retorna erro 401 (Unauthorized)
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.replace(TOKEN_PREFIX, "");

        try {
            // ✅ Validar o token usando a mesma chave secreta
            JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer("API TravelEase")
                    .build()
                    .verify(token);

            return chain.filter(exchange); // Se o token for válido, segue com a requisição

        } catch (JWTVerificationException e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}

