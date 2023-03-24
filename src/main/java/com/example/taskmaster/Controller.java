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
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

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
        if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileReader.getFirstRow(user)[0])) {
            model.addAttribute("roomName", user.getRoomname());
            model.addAttribute("username", user.getUsername());

            model.addAttribute("tasks", FileReader.getTasks(user));

            return "Structure";
        } else {
            model.addAttribute("wrongpwd", "That is the wrong pwd!");
            return "Login";
        }
    }

    @PostMapping("/Login")
    public String backtoLogin(@ModelAttribute UserHandler user) throws IOException, NoSuchAlgorithmException {

        CreateUser.createRoom(user);
        if (CreateUser.createUserData(user) == false) {
            throw new IOException("Den User gibt es schon");
        }
        CreateUser.createUserData(user);

        return "Login";
    }
}
