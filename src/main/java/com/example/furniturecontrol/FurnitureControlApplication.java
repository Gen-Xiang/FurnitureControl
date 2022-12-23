package com.example.furniturecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FurnitureControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(FurnitureControlApplication.class, args);
    }

}
