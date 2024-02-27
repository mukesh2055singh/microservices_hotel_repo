package com.gatewayAuthentication;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Service
public class GatewayAuthFilter implements GlobalFilter {
	private final WebClient webClient;

    public GatewayAuthFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085").build();
    }
	@Override
	public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		String requestPath = exchange.getRequest().getURI().getPath();
		System.out.println("requestPath-----:"+requestPath);
		if(requestPath.equals("/user/doLogin")) {
			System.out.println("requestPath----pass mapping-:");
			return chain.filter(exchange).then(Mono.empty());
		}else {
			String originalUri = exchange.getRequest().getURI().toString();
	        HttpMethod originalMethod = exchange.getRequest().getMethod();
	        HttpHeaders originalHeaders = exchange.getRequest().getHeaders();

	        // Send request to security service for JWT token validation
	        // Include the captured information in the request
	        String validationUri = "/user/apiGatewayAuthentication";
	        Mono<Void> validationResult = webClient.get()
	                .uri(validationUri)
	                .header("X-Original-Uri", originalUri)
	                .header("X-Original-Method", originalMethod.name())
	                .headers(headers -> headers.putAll(originalHeaders))
	                .exchange()
	                .flatMap(clientResponse -> {
	                    if (clientResponse.statusCode().is2xxSuccessful()) {
	                        // JWT token is valid, continue processing
	                        return chain.filter(exchange);
	                    } else {
	                        // JWT token validation failed, handle accordingly
	                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                        return exchange.getResponse().setComplete();
	                    }
	                });

	        return validationResult;
		}
	}
}
