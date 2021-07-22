package com.training.springbootbuyitem;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication()
@Configuration
public class BuyItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyItemApplication.class, args);
    }
    @Bean(name = "ClientInstance", destroyMethod = "shutdown")
    public HazelcastInstance createStorageNode() throws Exception {
        return Hazelcast.newHazelcastInstance();
    }
}
