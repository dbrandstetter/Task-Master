package com.example.taskmaster;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static String[] getFirstRow(UserHandler user) throws IOException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".task");

        try (BufferedReader reader = Files.newBufferedReader(fileLocation)) {
            return reader.readLine().split(";");
        }
    }

    public static String[] getFirstRowAdd(Path fileLocation) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(fileLocation)) {
            return reader.readLine().split(";");
        }
    }

    public static int countLines(String filename) {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (reader.readLine() != null) {
                if (!(reader.equals(""))) {
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void createRoom(UserHandler user) throws IOException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname());

        if (!(Files.exists(fileLocation))) {
            Files.createDirectory(fileLocation);
            Files.createFile(Path.of("rooms/" + user.getRoomname() + "/general.rtf"));
        }
    }

    public static boolean createUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".task");

        if (!Files.exists(fileLocation)) {
            Files.createFile(fileLocation);
            writeUserData(user);
            CopyToNewUser(user);
            return true;
        } else {
            return false;
        }
    }

    private static void writeUserData(UserHandler user) throws IOException, NoSuchAlgorithmException {
        Path fileLocation = Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".task");

        try (BufferedWriter out = Files.newBufferedWriter(fileLocation)) {
            out.write(PasswordEncryptor.encrypt(user.getPassword()) + ";" + user.getPermission() + System.lineSeparator());
        }
    }

    public static void deleteEmptyLines(String fileName) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.task");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().equals("")) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            boolean successful = tempFile.renameTo(inputFile);
            if (!successful) {
                throw new IOException("Could not rename " + tempFile.getAbsolutePath() + " to " + inputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> findTxtFiles(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        List<File> txtFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".task")) {
                txtFiles.add(file);
            }
        }

        return txtFiles;
    }

    public static void CopyToNewUser(UserHandler user) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(Path.of("rooms/" + user.getRoomname() + "/general.rtf")); BufferedWriter out = Files.newBufferedWriter(Path.of("rooms/" + user.getRoomname() + "/" + user.getUsername() + ".task"), StandardOpenOption.APPEND)) {
            String line;
            while ((line = in.readLine()) != null) {

                out.write(line + System.lineSeparator());

            }
        }
    }
}


