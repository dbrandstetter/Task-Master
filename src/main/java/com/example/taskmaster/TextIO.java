package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextIO {

    private static List<List<String>> information = new ArrayList<>();

    public static List<List<String>> getHomeworks(UserHandler user) throws IOException{

        try (BufferedReader in = Files.newBufferedReader(Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt"))){
            String line = in.readLine();

            while ((line = in.readLine()) != null) {

                information.add(List.of(line.split(";")[0],line.split(";")[1],line.split(";")[2]));

            }
        }

        return information;
    }

    public static String[] getFirstRow(UserHandler user) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("rooms/"+user.getRoomname()+"/"+user.getUsername()+".txt"));
        String firstLine = reader.readLine();
        reader.close();
        return firstLine.split(";");

    }

}
