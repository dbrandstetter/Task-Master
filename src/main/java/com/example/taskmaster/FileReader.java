package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> getTasks(UserHandler user) throws IOException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
            String line;
            in.readLine();

            while ((line = in.readLine()) != null) {
                // Add a new Task to tasks that is filled with the title, deadline and info stored in the user file
                tasks.add(new Task(line, in.readLine(), in.readLine()));
            }
        }

        return tasks;
    }

    public static String[] getFirstRow(UserHandler user) throws IOException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

        try (BufferedReader reader = Files.newBufferedReader(fileLocation)) {
            return reader.readLine().split(";");
        }
    }
}
