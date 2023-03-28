package com.example.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskMasterApplication {
	public static void main(String[] args) {
		// wenn man die Terminal Ausgabe in einer Datei haben will: TerminalReader.TerminalToFile("Terminal/out.txt");
		SpringApplication app = new SpringApplication(TaskMasterApplication.class);
		app.run(args);
	}
}
