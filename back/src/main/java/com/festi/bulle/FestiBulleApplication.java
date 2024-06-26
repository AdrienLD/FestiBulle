package com.festi.bulle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FestiBulleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FestiBulleApplication.class, args);
    }
}
