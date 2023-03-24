package com.example.taskmaster;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

@org.springframework.stereotype.Controller
public class Controller {
    //TODO sucht dir einen von den Bannern aus (banner.txt/banner2.txt)

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
        if (Files.exists(Path.of("rooms/" + user.getRoomname()+"/"+user.getUsername()+".txt"))) {
            if (PasswordEncryptor.encrypt(user.getPassword()).equals(FileReader.getFirstRow(user)[0])) {
                model.addAttribute("roomName", user.getRoomname());
                model.addAttribute("username", user.getUsername());

                model.addAttribute("tasks", FileReader.getTasks(user));

                return "Structure";
            }else {
                model.addAttribute("wronglogin", "Login data is not valid!");
                return "Login";
            }
        }else {
            model.addAttribute("wronglogin", "Login data is not valid!");
            return "Login";
        }
    }

    @PostMapping("/Login")
    public String backtoLogin(@ModelAttribute UserHandler user,Model model) throws IOException, NoSuchAlgorithmException {
        if (Files.exists(Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt"))) {
            model.addAttribute("wrongsignup","This User already exists!");
            return "SignUp";
        } else {
            CreateUser.createRoom(user);
            if (!CreateUser.createUserData(user)) {
                System.out.println("Es wurde eine IO-Exception geworfen");
                throw new IOException("Den User gibt es schon");
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
