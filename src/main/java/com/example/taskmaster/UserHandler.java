package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserHandler {

    private String roomname;
    private String username;
    private String password;

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

    public String getPermission() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("rooms/"+roomname+"/"+username+".txt"));
        String firstLine = reader.readLine();
        reader.close();
        return firstLine.split(";")[1];

    }

    @Override
    public String toString() {

        try {
            return "["+ roomname+"] ["+username+"] ["+password+"] ["+getPermission()+"]";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
