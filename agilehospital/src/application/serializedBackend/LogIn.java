package application.serializedBackend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LogIn {
   
    public static boolean validateLoginInfo(String username, String password) {
        String userFilePath = System.getProperty("user.dir") + File.separator + "userData" + File.separator + username + ".json";
        boolean userFileExists = Files.exists(Paths.get(userFilePath));
        String userPasswordOnFile = "";

        if (!userFileExists) {
            System.out.println("User does not exist");
            return false;
        }

        try {
            userPasswordOnFile = getPasswordOnFile(userFilePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        if (password.equals(userPasswordOnFile)) {
            System.out.println("Successful login");
            return true;
        }

        // handling invalid outputs
        if (userPasswordOnFile.equals("Password not found")) {
            System.out.println("User data corrupted. Create new user please.");
            return false;
        }

        System.out.println("Incorrect password");
        return false;
    }

    public static String getPasswordOnFile(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        String targetKey = "\"password\":\"";
        int passwordStartIndex = fileContent.indexOf(targetKey) + targetKey.length();

        // No key found
        if (passwordStartIndex == targetKey.length() - 1) {
            return "Password not found";
        }

        int passwordEndIndex = fileContent.indexOf("\"", passwordStartIndex);

        // Invalid JSON structure
        if (passwordEndIndex == -1) {
            return "Password not found";
        }

        return fileContent.substring(passwordStartIndex, passwordEndIndex);
    }

    public static int returnUserType(String fileName) {
        char fileSuffix = fileName.charAt(0);
        switch(fileSuffix) {
            case 'p':
                return 0;
            case 'n':
                return 1;
            case 'd':
                return 2;
            default:
                return -1;
        }
    }
}
