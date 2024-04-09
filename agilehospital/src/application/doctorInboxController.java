package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button patientX_button;

    @FXML
    private Button profile_button;

    @FXML
    private TextArea recipientInfo_box;

    @FXML
    private Label recipient_label;

    @FXML
    private Button reply_button;

    @FXML
    void back_clicked(ActionEvent event) {
    	Parent windowLoader;
        try {
            windowLoader = FXMLLoader.load(getClass().getResource("docView.fxml"));
            Stage stage = (Stage) profile_button.getScene().getWindow();
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
    void patX_button_clicked(ActionEvent event) {

    }

    @FXML
    void profile_clicked(ActionEvent event) {

    }

    @FXML
    void reply_clicked(ActionEvent event) {

    }

}