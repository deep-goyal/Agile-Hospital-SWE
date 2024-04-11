package application.FXMLHandlers;
import application.Main;
import application.serializedBackend.MessagesBackend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class NurseMessageHandler {

    @FXML
    private TableView<String> message_table;

    @FXML
    private TextArea allmessages_textarea;

    @FXML
    private TextField newmessage_user_field;

    @FXML
    private TextField inputmessage_TF;

    @FXML
    private Button newmessage_button;

    @FXML
    private Button send_button;

    @FXML
    private Button backbtn;

    // Placeholder for the global username variable
    private String globalUsername = "placeholder_for_global_username";

    @FXML
    public void initialize() {
        loadMessageFiles();
        setupMessageSelectionListener();
        setupNewMessageButton();
        setupSendButton();
    }

    public void setupSendButton() {
        send_button.setOnAction(event -> sendMessage());
    }

    public void sendMessage() {
        String message = inputmessage_TF.getText().trim();
        if (message.isEmpty()) {
            System.err.println("Message is empty.");
            return;
        }

        String selectedThread = message_table.getSelectionModel().getSelectedItem();
        if (selectedThread == null) {
            System.err.println("No message thread selected.");
            return;
        }

        // Assuming the thread name is the file name. Adjust if your file naming scheme is different.
        boolean result = MessagesBackend.sendMessage(globalUsername, "receiver_username_placeholder", message, globalUsername);

        if (result) {
            System.out.println("Message sent successfully.");
            displayMessageContent(selectedThread); // Refresh the TextArea display
            inputmessage_TF.clear(); // Clear the input field
        } else {
            System.err.println("Failed to send message.");
        }
    }

    public void loadMessageFiles() {
        try {
            String messageDirPath = "userData" + File.separator + "messages";
            File messageDir = new File(messageDirPath);

            if (!messageDir.exists() || !messageDir.isDirectory()) {
                System.err.println("Message directory does not exist or is not a directory.");
                return;
            }

            List<String> messageFiles = Files.walk(Paths.get(messageDirPath))
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());

            message_table.getItems().clear();
            message_table.getItems().addAll(messageFiles);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load message files: " + e.getMessage());
        }
    }

    public void setupMessageSelectionListener() {
        message_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayMessageContent(newSelection);
            }
        });
    }

    public void displayMessageContent(String fileName) {
        try {
            String messageDirPath = "userData" + File.separator + "messages" + File.separator + fileName;
            String content = new String(Files.readAllBytes(Paths.get(messageDirPath)));
            allmessages_textarea.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load message content: " + e.getMessage());
        }
    }

    public void setupNewMessageButton() {

        newmessage_button.setOnAction(event -> createNewMessage());

    }

    public void createNewMessage() {
        String message = newmessage_user_field.getText();
        if (message.isEmpty()) {
            System.err.println("No message entered.");
            return;
        }

        // Call your backend function here to create a new message file
        // Replace "sendMessage" with the actual method name and adjust parameters as necessary
        // The "globalUsername" variable is used as a placeholder for the actual sender's username
        boolean result = MessagesBackend.sendMessage(globalUsername, "receiver_username_placeholder", message, globalUsername);

        if (result) {
            System.out.println("Message successfully saved.");
            newmessage_user_field.clear();
            loadMessageFiles();
        } else {
            System.err.println("Failed to save message.");
        }
    }

    public void back() throws IOException {
        //redirect to NurseDash.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/NurseDash.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
