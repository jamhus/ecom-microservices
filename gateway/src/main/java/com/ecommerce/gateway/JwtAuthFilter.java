package com.ecommerce.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements WebFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest()
                .getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    logger.info(
            "Authorization header: {}", authHeader
    );
//        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
//           exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
//           return exchange.getResponse().setComplete();
//        }

        return chain.filter(exchange);
    }
}
