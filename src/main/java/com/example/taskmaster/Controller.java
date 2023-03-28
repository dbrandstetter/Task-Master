package com.example.taskmaster;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;

@org.springframework.stereotype.Controller
public class Controller {
	private String roomname;
	private String username;
	private String permission;

	public static void writeToTxtFilesInFolder(String folderPath, String textToWrite) {
		File folder = new File(folderPath);
		File[] fileList = folder.listFiles();

		for (File file : fileList) {
			if (file.isFile() && file.getName().endsWith(".txt")) {
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
					writer.newLine();
					writer.write(textToWrite);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@GetMapping("/login")
	public String login() {
		return "Login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "SignUp";
	}

	@PostMapping("/room")
	public String room(@ModelAttribute UserHandler user, Model model) throws IOException, NoSuchAlgorithmException {
		System.out.println("user in room (/room) = " + user);
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		if (Files.exists(fileLocation)) {
			if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileReader.getFirstRow(user)[0])) {
				model.addAttribute("roomName", user.getRoomname());
				model.addAttribute("username", user.getUsername());
				model.addAttribute("usernameLetter", user.getUsername().charAt(0));
				model.addAttribute("tasks", FileReader.getTasks(user));
				model.addAttribute("remove", false);

				username = user.getUsername();
				roomname = user.getRoomname();

				return "Structure";
			} else {
				model.addAttribute("wronglogin", "Login data is not valid!");

				return "Login";
			}
		} else {
			model.addAttribute("wronglogin", "Login data is not valid!");

			return "Login";
		}
	}

	@PostMapping("/Login")
	public String backtoLogin(@ModelAttribute UserHandler user, Model model) throws IOException, NoSuchAlgorithmException {
		System.out.println("user in backToLogin (/Login) = " + user);
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		if (Files.exists(fileLocation)) {
			model.addAttribute("wrongsignup", "This User already exists!");

			return "SignUp";
		} else {
			CreateUser.createRoom(user);

			if (!CreateUser.createUserData(user)) {
				System.out.println("backToLogin(): An IOException got thrown.");
				throw new IOException("This User already exists!");
			}

			return "Login";
		}
	}

	@PostMapping("/update")
	public String addTask(Task task, Model model) throws IOException {
		Path fileLocation = Path.of("rooms/" + roomname + "/" + username + ".txt");
		System.out.println("task (in addTask)= " + task);

		writeTask(task, fileLocation);

		model.addAttribute("tasks", FileReader.getTasks(roomname, username));

		return "Structure";
	}

	private void writeTask(Task task, Path fileLocation) {
		System.out.println("task = " + task);
		try (BufferedWriter out = Files.newBufferedWriter(fileLocation, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
			out.write(task.getTitle() + System.lineSeparator());
			out.write(task.getDeadline() + System.lineSeparator());
			out.write(task.getInfo() + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
