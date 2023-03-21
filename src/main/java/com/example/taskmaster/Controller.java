package com.example.taskmaster;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
            model.addAttribute("wrongpwd", "That is the worng pwd!");
            return "Login";
        }
    }

    @PostMapping("/rooms")
    public String rooms(@ModelAttribute UserHandler user, Model model) throws IOException, NoSuchAlgorithmException {

        //TODO erzeugt User mit Raum

        return room(user,model);
    }
}
