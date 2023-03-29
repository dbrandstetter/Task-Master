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

public class TodoManager {

    public static List<Todo> todos = new ArrayList<>();

    public static List<Todo> getTodos(UserHandler user) throws IOException {
        todos.clear();

        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".todo");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
            String line;

            while ((line = in.readLine()) != null) {
                todos.add(new Todo(line, in.readLine(), in.readLine()));
            }
        }

        return todos;
    }

    public static List<Todo> getTodos(String username, String roomname) throws IOException {
        todos.clear();

        Path fileLocation = Path.of("rooms/" + roomname + "/" + username + ".todo");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
            String line;

            while ((line = in.readLine()) != null) {
                todos.add(new Todo(line, in.readLine(), in.readLine()));
            }
        }

        return todos;
    }

    public static void addTodo(Todo todo) {
        todos.add(todo);
    }

    public static void writeTodo(Todo todo, Path fileLocation) {
        try (BufferedWriter out = Files.newBufferedWriter(fileLocation, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            out.write(todo.getTitle() + System.lineSeparator());
            out.write(todo.getDeadline() + System.lineSeparator());
            out.write(todo.getInfo() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
