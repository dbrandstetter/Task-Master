package com.example.taskmaster;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/login")
    public String index() {

        return "Login";

    }

    @PostMapping("/room")
    public String userRegister(@ModelAttribute UserHandler user, Model model) throws IOException {


        model.addAttribute("roomName",user.getRoomname());
        model.addAttribute("username",user.getUsername());

        model.addAttribute("information",FileReader.getTasks(user));
        System.out.println(model.getAttribute("information"));

        if (user.getPassword().equals(FileReader.getFirstRow(user)[0])){

            return "Structure";

        }else {

            model.addAttribute("wrongpwd","That is the wrong password!");
            return "Login";

        }

    }
}
