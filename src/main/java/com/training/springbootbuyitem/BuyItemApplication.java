package com.training.springbootbuyitem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication()
public class BuyItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyItemApplication.class, args);
    }

}
