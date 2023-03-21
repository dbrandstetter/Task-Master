package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserHandler {

    private String roomname;
    private String username;
    private String password;
    private String permission;

    public void setRoomname(String roomname) {

        this.roomname = roomname;

    }

    public String getRoomname() {

        return roomname;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getPermission(){

        return permission;

    }

    public void setPermission(String permission) {

        this.permission = permission;

    }

    @Override
    public String toString() {
            return "[" + roomname + "] [" + username + "] [" + password + "] [" + permission + "]";
    }

}
