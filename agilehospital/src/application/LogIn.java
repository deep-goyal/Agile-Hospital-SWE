package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LogIn {
    public static void main(String[] args) {
        landingPage();
    }

    public static void landingPage() {
        // user info variables
        String username, password;

        // internal use variable(s)
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("User Log-In Menu:");
            System.out.println("==================================");

            System.out.print("Username: ");
            username = input.nextLine();

            System.out.print("Password: ");
            password = input.nextLine();
        } while (!validateLoginInfo(username, password));
    }

    private static boolean validateLoginInfo(String username, String password) {
        String userFilePath = "userData" + File.separator + username + ".json";
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

    private static String getPasswordOnFile(String filePath) throws IOException {
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
}
