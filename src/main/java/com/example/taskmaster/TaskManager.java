package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> getTasks(UserHandler user) throws IOException {
        tasks.clear();

        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".task");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
            String line;
            in.readLine();

            while ((line = in.readLine()) != null) {
                tasks.add(new Task(line, in.readLine(), in.readLine()));
            }
        }

        return tasks;
    }

    public static List<Task> getTasks(String roomname, String username) throws IOException {
        tasks.clear();

        Path fileLocation = Path.of("rooms/" + roomname + "/" + username + ".task");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
            String line;
            in.readLine();

            while ((line = in.readLine()) != null) {
                tasks.add(new Task(line, in.readLine(), in.readLine()));
            }
        }

        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static void writeTask(Task task, Path fileLocation) {
        try (BufferedWriter out = Files.newBufferedWriter(fileLocation, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            out.write(task.getTitle() + System.lineSeparator());
            out.write(task.getDeadline() + System.lineSeparator());
            out.write(task.getInfo() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
