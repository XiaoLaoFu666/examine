package com.huang.examine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExamineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamineApplication.class, args);
    }

}
