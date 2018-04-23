package com.cqut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.activiti.spring.boot.SecurityAutoConfiguration.class
})
public class NevisApplication {

    public static void main(String[] args) {
        SpringApplication.run(NevisApplication.class, args);
    }
}
