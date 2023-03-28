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
		CustomLogger.logCustomInfo(user.getUsername()+" just logged in!");
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		if (Files.exists(fileLocation)) {
			if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileManager.getFirstRow(user)[0])) {
				model.addAttribute("roomName", user.getRoomname());
				model.addAttribute("username", user.getUsername());
				model.addAttribute("usernameLetter", user.getUsername().charAt(0));
				model.addAttribute("tasks", FileManager.getTasks(user));
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
		CustomLogger.logCustomInfo(user.getUsername()+ " just signed up!");
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
		CustomLogger.logCustomInfo(username + " just posted a new Task("+task.toString()+")!");
		FileManager.deleteEmptyLines("rooms/" + roomname + "/" + username + ".txt");
		Path fileLocation = Path.of("rooms/" + roomname + "/" + username + ".txt");
		FileManager.writeTask(task, fileLocation);
		model.addAttribute("roomName", roomname);
		model.addAttribute("username", username);
		model.addAttribute("usernameLetter", username.charAt(0));
		model.addAttribute("tasks", FileManager.getTasks(roomname, username));
		return "Structure";
	}
}
