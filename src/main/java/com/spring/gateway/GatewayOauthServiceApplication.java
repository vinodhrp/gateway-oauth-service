package com.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaServer
public class GatewayOauthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayOauthServiceApplication.class, args);
	}

}
