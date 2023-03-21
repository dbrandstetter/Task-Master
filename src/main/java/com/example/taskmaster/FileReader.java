package com.example.taskmaster;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private List<List<String>> taskData = new ArrayList<>();

    public static List<List<String>> getHomeworks(UserHandler user) throws IOException{
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".txt");

        try (BufferedReader in = Files.newBufferedReader(fileLocation, StandardCharsets.UTF_8)){
            String line = in.readLine();

            while ((line = in.readLine()) != null) {
                taskData.add(List.of(line.split(";")[0],line.split(";")[1],line.split(";")[2]));
            }
        }

        return taskData;
    }

    public String[] getFirstRow(UserHandler user) throws IOException {

        BufferedReader reader = new BufferedReader(new java.io.FileReader("rooms/"+user.getRoomname()+"/"+user.getUsername()+".txt"));
        String firstLine = reader.readLine();
        reader.close();
        return firstLine.split(";");

    }

}
