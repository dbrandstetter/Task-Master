package com.example.taskmaster;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

public class CreateUser {

    public static void createRoom(UserHandler user) throws IOException {
        if (!(Files.exists(Path.of("rooms/" + user.getRoomname())))) Files.createDirectory(Path.of("rooms/" + user.getRoomname()));
    }

    public static boolean createUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
        if (!Files.exists(Path.of("rooms/"+user.getRoomname()+"/"+user.getUsername()+".txt"))) {
            Files.createFile(Path.of("rooms/"+user.getRoomname()+"/"+user.getUsername()+".txt"));
            writeUserData(user);
            return true;
        } else {
            return false;
        }
    }

    private static void writeUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
        try (BufferedWriter out = Files.newBufferedWriter(Path.of("rooms/"+user.getRoomname()+"/"+user.getUsername()+".txt"))) {
            out.write(PasswordEncryptor.encrypt(user.getPassword())+";"+user.getPermission());
        }
    }

}
