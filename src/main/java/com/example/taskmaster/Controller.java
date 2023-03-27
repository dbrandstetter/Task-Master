package com.example.taskmaster;

import org.apache.catalina.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

@org.springframework.stereotype.Controller
public class Controller {

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
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		if (Files.exists(fileLocation)) {
			if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileReader.getFirstRow(user)[0])) {
				model.addAttribute("roomName", user.getRoomname());
				model.addAttribute("username", user.getUsername());
				model.addAttribute("usernameLetter", user.getUsername().charAt(0));
				model.addAttribute("tasks", FileReader.getTasks(user));

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

	@PostMapping("/Room")
	public String addTask(Task task) {
		//TODO Da kommt eine add Methode hinein
        /*
            Sie soll einfach allen die in dem Raum chillen, die Aufgabe hineinschreiben

            Formatierung:

            Mathe-HÜ
            21.02.2023
            Buch S.125/1,2,3

         */
		return "Structure";
	}
}
