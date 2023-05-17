package com.aldiichsantkj3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseApplication.class, args);
    }
}
