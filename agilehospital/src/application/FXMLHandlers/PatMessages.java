package application.FXMLHandlers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PatMessages {

    @FXML
    private TableView<String> message_table; // TableView holding message thread names

    @FXML
    private TextArea allmessages_textarea; // TextArea for displaying a message thread's content

    @FXML
    public void initialize() {
        loadMessageFiles();
        setupMessageSelectionListener();
    }

    private void loadMessageFiles() {
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

    private void setupMessageSelectionListener() {
        // Add a listener to detect changes in selection within the TableView
        message_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayMessageContent(newSelection);
            }
        });
    }

    private void displayMessageContent(String fileName) {
        try {
            String messageDirPath = "userData" + File.separator + "messages" + File.separator + fileName;
            String content = new String(Files.readAllBytes(Paths.get(messageDirPath)));
            allmessages_textarea.setText(content); // Update TextArea with file content
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load message content: " + e.getMessage());
        }
    }
}
