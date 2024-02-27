package com.gatewayAuthentication;

import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CorsConfig implements WebFluxConfigurer {
	@Bean
	public CorsWebFilter corsWebFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:3000");  // Allow all origins (for testing)
	    config.addAllowedMethod("*");
	    config.addAllowedHeader("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsWebFilter(source);
	}
   
	/*@Bean
    public GlobalCorsProperties globalCorsProperties() {
        GlobalCorsProperties globalCorsProperties = new GlobalCorsProperties();
        // Configure CORS settings for "myCorsConfig"
        globalCorsProperties.getCorsConfigurations().put("myCorsConfig",configureCors());
        return globalCorsProperties;
    }

    private CorsConfiguration configureCors() {
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        // Add more configuration as needed
        corsConfiguration.setMaxAge(606000l); // Example: set the maximum age of the CORS preflight request in seconds
        corsConfiguration.setAllowCredentials(true); // Example: allow credentials (cookies) to be sent with the request
        return corsConfiguration;
    }*/
}
