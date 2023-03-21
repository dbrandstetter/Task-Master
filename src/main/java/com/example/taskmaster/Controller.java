package com.example.taskmaster;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    public static String roomName;

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "SignUp";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task, Model model) throws IOException {
        try (BufferedWriter out = Files.newBufferedWriter(Path.of("rooms/" + roomName + "/Generell.txt"))) {
            out.write(task.getTitle() + System.lineSeparator());
            out.write(task.getDeadline() + System.lineSeparator());
            out.write(task.getInfo() + System.lineSeparator());
        }

        return "Structure";
    }

    @PostMapping("/room")
    public String room(@ModelAttribute UserHandler user, Model model) throws IOException, NoSuchAlgorithmException {
        if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileReader.getFirstRow(user)[0])) {
            model.addAttribute("roomName", user.getRoomname());
            roomName = user.getRoomname();
            model.addAttribute("username", user.getUsername());
            model.addAttribute("tasks", FileReader.getTasks(user));
            return "Structure";
        } else {
            model.addAttribute("wrongpwd", "That is the worng pwd!");
            return "Login";
        }
    }

    @PostMapping("/rooms")
    public String rooms(@ModelAttribute UserHandler user, Model model) throws IOException, NoSuchAlgorithmException {
        if (!Files.isDirectory(Path.of("rooms/" + user.getRoomname()))) {
            Files.createDirectory(Path.of("rooms/" + user.getRoomname()));
        }
        if (!Files.exists(Path.of("rooms/" + user.getRoomname() + "/Generell.txt"))) {
            Files.createFile(Path.of("rooms/" + user.getRoomname() + "/Generell.txt"));
        }
        Files.createFile(Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt"));

        try (BufferedWriter out = Files.newBufferedWriter(Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt")); BufferedReader in = Files.newBufferedReader(Path.of("rooms/" + user.getRoomname() + "/Generell.txt"))) {
            out.write(PasswordEncryptor.encrypt(user.getPassword()) + ";" + user.getPermission() + System.lineSeparator());
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line + System.lineSeparator());
            }

        }

        return room(user, model);
    }
}
