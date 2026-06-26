package com.example.apigateway.filter;

import com.example.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        // public endpoints
        String path = request.getURI().getPath();

        if (path.contains("/login") || path.contains("/register")) {
            return chain.filter(exchange);
        }

        // check header
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new RuntimeException("Missing token");
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }

        String token = authHeader.substring(7);

        // validate token
        if (!jwtUtil.isValid(token)) {
            throw new RuntimeException("Token expired or invalid");
        }

        // extract data
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        // mutate request
        ServerHttpRequest modifiedRequest = request.mutate()
                .header("X-user-email", email)
                .header("X-user-role", role)
                .build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }
}