package com.example.taskmaster;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.util.Collections;

@SpringBootApplication
public class TaskMasterApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(TaskMasterApplication.class);
        app.run(args);

    }

}
