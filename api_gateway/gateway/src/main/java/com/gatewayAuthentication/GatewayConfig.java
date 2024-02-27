package com.gatewayAuthentication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder,GatewayAuthFilter gatewayAuthFilter) {
		System.out.println("gateway config callled");
		return builder.routes()
            .route("user-app", r -> r
                .path("/user/**")
                //.filters(f -> f.filter(gatewayAuthFilter))
                .uri("http://localhost:8085")) // Replace with your authentication service URL
            .route("hotel-app",r -> r
                .path("/hotel/**")
                //.filters(f -> f.filter(gatewayAuthFilter))
                .uri("http://localhost:8086"))  // Replace with your other microservice URL
            .route("ratings-app", r -> r
	             .path("/**")
	             .uri("http://localhost:8087"))
            .build();
	}
	
}
