package com.capitole;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class InitCapitole {
    public static void main(String[] args) {
        SpringApplication.run(InitCapitole.class, args);
    }
}

