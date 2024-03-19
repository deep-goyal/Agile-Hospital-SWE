package com.example.hospitalui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class SignUp {

    // Backend endpoint that creates a user file
    public static String registerUser(int userType, String firstName, String lastName, LocalDate dateOfBirth, int gender, String password, int securityQuestion, String securityAnswer) throws IOException {
        String username = generateUsername(userType, firstName, lastName, dateOfBirth);

        if (createUserFile(username, userType, firstName, lastName, dateOfBirth, gender, password, securityQuestion, securityAnswer)) {
            return username;
        } else {
            return "-1";        // Handle this accordingly
        }
    }

    // Backend endpoint to validate user's sign up info
    public static boolean validateUserInput(int userType, String firstName, LocalDate dateOfBirth, int gender, String password, int securityQuestion, String securityAnswer) {
        boolean userTypeIsValid;
        boolean firstNameIsValid;
        boolean dateOfBirthIsValid;
        boolean genderIsValid;
        boolean passwordIsValid;
        boolean securityQuestionIsValid;
        boolean securityAnswerIsValid;

        // helper variable(s)
        String regex = "^"                  // Start of the string
                + "(?=.*[0-9])"             // a number should occur at least once
                + "(?=.*[a-z])"             // a lower case letter should occur at least once
                + "(?=.*[A-Z])"             // an upper case letter should occur at least once
                + "(?=.*[@#$%^&+=])"        // a special character should occur at least once
                + "(?=\\S+$)"               // no whitespace allowed in the entire string
                + ".{8,}"                   // should be at least 8 places long
                + "$";                      // End of the string


        // Checking if user inputs are valid
        userTypeIsValid = ((userType >= 1) && (userType <= 3));
        firstNameIsValid = !firstName.isEmpty();
        dateOfBirthIsValid = !dateOfBirth.equals(LocalDate.of(1000, 10, 10));
        genderIsValid = ((gender == 1) || (gender == 2));
        passwordIsValid = password.matches(regex);
        securityQuestionIsValid = ((securityQuestion >= 1) && (securityQuestion <= 6));
        securityAnswerIsValid = !securityAnswer.isEmpty();


        return (userTypeIsValid && firstNameIsValid && dateOfBirthIsValid && genderIsValid && passwordIsValid && securityQuestionIsValid && securityAnswerIsValid);
    }

    
    // Internal methods
    private static String generateUsername(int userType, String firstName, String lastName, LocalDate dateOfBirth) throws IOException {
        String baseUsername = "";
        String uniqueUsername;
        int day;
        int counter = 0;

        // no default because validation will deal with it
        switch (userType) {
            case 1:
                baseUsername += "p";
                break;
            case 2:
                baseUsername += "d";
                break;
            case 3:
                baseUsername += "n";
                break;
        }

        // name processing: if no lastName -> use firstName    else -> fistName[0]+lastName
        if (lastName.isEmpty()) {
            baseUsername += firstName.toLowerCase();
        } else {
            baseUsername += Character.toLowerCase(firstName.charAt(0));
            baseUsername += lastName.toLowerCase();
        }

        // date processing: appending different int if username already exists
        day = Integer.parseInt(dateOfBirth.toString().split("-")[2]);
        uniqueUsername = baseUsername + String.format("%02d", day);

        // incrementing day number till unique is found
        while (Files.exists(Paths.get("userData" + File.separator + uniqueUsername + ".json")) && counter < 100) {
            day = (day + 1) % 100;      // Loops back to 0 after 99
            uniqueUsername = baseUsername + String.format("%02d", day);
            counter++;
        }

        if (counter >= 100) {
            System.out.println("Database overload. Cannot accept more users with the same name");
            return null;
        }

        return uniqueUsername;
    }

    private static String securityQuestionList(int index) {
        return switch (index) {
            case 1 -> "What was the name of your first pet?";
            case 2 -> "What was the name of your first school?";
            case 3 -> "What year did you enter college?";
            case 4 -> "What is your mother's maiden name?";
            case 5 -> "What is the name of the manufacturer of your first car?";
            case 6 -> "What is your favorite sport?";
            default -> "Invalid index.";
        };
    }

    private static String toJSON(String username, int userType, String firstName, String lastName, LocalDate dateOfBirth, int gender, String password, int securityQuestion, String securityAnswer) {
        String userTypeString = switch (userType) {
            case 1 -> "Patient";
            case 2 -> "Doctor";
            case 3 -> "Nurse";
            default -> "Invalid Input";
        };

        String[] dateOfBirthArr = dateOfBirth.toString().split("-");
        // to maintain valid JSON format: 01 is invalid, 1 is valid
        dateOfBirthArr[1] = String.valueOf(Integer.parseInt(dateOfBirthArr[1]));
        dateOfBirthArr[2] = String.valueOf(Integer.parseInt(dateOfBirthArr[2]));

        String genderString = switch (gender) {
            case 1 -> "Male";
            case 2 -> "Female";
            default -> "Invalid Input";
        };

        String securityQuestionString = securityQuestionList(securityQuestion);

        return "{\n" +
                "\"username\":\"" + username + "\",\n" +
                "\"userType\":\"" + userTypeString + "\",\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"dob\":{\n" +
                "\"year\":" + dateOfBirthArr[0] + ",\n" +
                "\"month\":" + dateOfBirthArr[1] + ",\n" +
                "\"day\":" + dateOfBirthArr[2] + "\n" +
                "},\n" +
                "\"gender\":\"" + genderString + "\",\n" +
                "\"password\":\"" + password + "\",\n" +
                "\"securityQuestion\":\"" + securityQuestionString + "\",\n" +
                "\"securityAnswer\":\"" + securityAnswer + "\"\n" +
                "}";
    }

    private static boolean createUserFile(String username, int userType, String firstName, String lastName, LocalDate dateOfBirth, int gender, String password, int securityQuestion, String securityAnswer) {
        // Final format: userData/username.json
        String fileName = "userData" + File.separator + username + ".json";
        boolean fileCreated = false;

        // creating the file
        try {
            File makeFile = new File(fileName);
            if (makeFile.createNewFile()) {
                fileCreated = true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        // writing to the file
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(toJSON(username, userType, firstName, lastName, dateOfBirth, gender, password, securityQuestion, securityAnswer));
            myWriter.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
