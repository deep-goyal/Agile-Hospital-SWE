package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class doctorInboxController {

    @FXML
    private Button back_button;

    @FXML
    private Button compose_button;

    @FXML
    private Button forward_button;

    @FXML
    private Label inbox_label;

    @FXML
    private Button markUnread_button;

    @FXML
    private TextField messageBody_TF;

    @FXML
    private TextField messageSubject_TF;

    @FXML
    private VBox messagebody_box;



    @FXML
    private TextArea recipientInfo_box;

    @FXML
    private Label recipient_label;

    @FXML
    private Button reply_button;
    
    @FXML
    private ListView<String> listView_inbox;

    
    public void initialize() {
        // Example messages; replace with actual logic to fetch messages
        List<String> messages = Arrays.asList("Message from Patient1", "Message from Patient2");
        listView_inbox.getItems().addAll(messages);
        
        // Add a listener to display the message details when a message is selected
        listView_inbox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayMessageDetails(newValue); // Implement this method to show message details
            }
        });
    }

    private void displayMessageDetails(String message) {
        // Logic to display the message details in messageBody_TF, messageSubject_TF, or recipientInfo_box
        messagebody_box.setVisible(true);
    }
    
    
    
    
    @FXML
    void back_clicked(ActionEvent event) {
    	Parent windowLoader;
        try {
            windowLoader = FXMLLoader.load(getClass().getResource("docView.fxml"));
            Stage stage = (Stage) back_button.getScene().getWindow();
            stage.setScene(new Scene(windowLoader));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void compose_clicked(ActionEvent event) {
    	messagebody_box.setVisible(true);

    }

    @FXML
    void forward_clicked(ActionEvent event) {

    }

    @FXML
    void markAsUnread_clicked(ActionEvent event) {

    }



    @FXML
    void reply_clicked(ActionEvent event) {

    }

}
