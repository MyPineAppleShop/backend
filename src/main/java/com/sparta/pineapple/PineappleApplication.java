package com.sparta.pineapple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PineappleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineappleApplication.class, args);
    }

}