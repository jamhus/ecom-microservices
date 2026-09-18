package com.ecommerce.uereka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UerekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UerekaApplication.class, args);
    }

}
