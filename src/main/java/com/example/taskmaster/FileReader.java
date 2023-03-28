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
				tasks.add(new Task(line, in.readLine(), in.readLine()));
			}
		}

		return tasks;
	}

	public static List<Task> getTasks(String roomname, String username) throws IOException {
		tasks.clear();

		Path fileLocation = Path.of("rooms/" + roomname + "/" + username + ".txt");

		try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)) {
			String line;
			in.readLine();

			while ((line = in.readLine()) != null) {
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

	public static void addTask(Task task) {
		tasks.add(task);
	}

	public static String[] getFirstRowAdd(Path fileLocation) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(fileLocation)) {
			return reader.readLine().split(";");
		}
	}
}
