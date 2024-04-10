import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessagesBackend {
    public static boolean sendMessage(String firstUser, String secondUser, String message, String senderID) {
        String elevatedUser;
        String secondaryUser;

        if (determineUserType(firstUser) == -1 || determineUserType(secondUser) == -1) {
            return false;
        }

        if (determineUserType(firstUser) > determineUserType(secondUser)) {
            elevatedUser = firstUser;
            secondaryUser = secondUser;
        } else {
            secondaryUser = firstUser;
            elevatedUser = secondUser;
        }

        String fileName = elevatedUser + "_" + secondaryUser + "_messages.txt";
        String messageFilePath = "userData" + File.separator + "messages" + File.separator + fileName;          // userData/messages/elevatedUser_secondaryUser_messages.txt

        if (!Files.exists(Paths.get(messageFilePath))) {            // File does not exist
            boolean fileCreated;
            fileCreated = createMessageFile(messageFilePath);
            if (!fileCreated) {
                return false;
            }
        }

        String writeMessage = senderID + ": " + message + "\n";

        try {
            FileWriter myWriter = new FileWriter(messageFilePath);
            myWriter.write(writeMessage);
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static int determineUserType(String userID) {
        char userPrefix = userID.charAt(0);
        return switch (userPrefix) {
            case 'p' -> 0;
            case 'n' -> 1;
            case 'd' -> 2;
            default -> -1;
        };
    }

    private static boolean createMessageFile(String filePath) {
        try {
            File makeFile = new File(filePath);
            return makeFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

}
