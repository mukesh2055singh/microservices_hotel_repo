package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	//@LoadBalanced
	/*@Bean
	public RestTemplate restTemplate() {
		System.out.println("bean method called");
		return new RestTemplate();
	}*/
	
	/*@Bean("key")
    public Key dynamicValue() {
		final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		System.out.println(secretKey);
        return secretKey; 
    }*/
	
}
