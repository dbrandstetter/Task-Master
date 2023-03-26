package com.example.taskmaster;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

	public static String encrypt(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(password.getBytes());
		byte[] bytes = md.digest();

		StringBuilder sb = new StringBuilder();

		for (byte octet : bytes) {
			sb.append(Integer.toString((octet & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}