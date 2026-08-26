package com.smartrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SmartRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRecipeApplication.class, args);
    }
}
