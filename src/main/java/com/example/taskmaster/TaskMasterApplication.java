package com.example.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TaskMasterApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(TaskMasterApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "1234"));
        app.run(args);

    }

}
