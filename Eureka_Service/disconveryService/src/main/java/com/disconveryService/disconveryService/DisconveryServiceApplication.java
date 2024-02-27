package com.disconveryService.disconveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DisconveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisconveryServiceApplication.class, args);
	}

}
