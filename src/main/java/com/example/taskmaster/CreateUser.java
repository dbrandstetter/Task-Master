package com.example.taskmaster;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

public class CreateUser {

	public static void createRoom(UserHandler user) throws IOException {
		Path fileLocation = Path.of("rooms/" + user.getRoomname());

		if (!(Files.exists(fileLocation))) {
			Files.createDirectory(fileLocation);
		}
	}

	public static boolean createUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		if (!Files.exists(fileLocation)) {
			Files.createFile(fileLocation);
			writeUserData(user);

			return true;
		} else {
			return false;
		}
	}

	private static void writeUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
		Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

		try (BufferedWriter out = Files.newBufferedWriter(fileLocation)) {
			out.write(PasswordEncryptor.encrypt(user.getPassword()) + ";" + user.getPermission() + System.lineSeparator());
		}
	}

}
