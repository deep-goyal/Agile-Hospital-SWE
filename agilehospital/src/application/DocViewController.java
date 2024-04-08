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
import javafx.stage.Stage;

public class DocViewController {

    @FXML
    private Label docDash_label;

    @FXML
    private Button messages_button;

    @FXML
    private TextArea patInfo_textarea;

    @FXML
    private Label patQueue_label;

    @FXML
    private Button patX_button;

    @FXML
    private Button patY_button;

    @FXML
    private Button prescribeMeds_button;

    @FXML
    private Button profile_button;

    @FXML
    private Label timeX_label;

    @FXML
    private Label timeY_label;

    @FXML
    private Button viewRecord_button;

    @FXML
    void message_button_clicked(ActionEvent event) {
    	Parent windowLoader;
        try {
            windowLoader = FXMLLoader.load(getClass().getResource("doctorMessages.fxml"));
            Stage stage = (Stage) profile_button.getScene().getWindow();
            stage.setScene(new Scene(windowLoader));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void patX_button_clicked(ActionEvent event) {

    }

    @FXML
    void patY_button_clicked(ActionEvent event) {

    }

    @FXML
    void prescribeMeds_button_clicked(ActionEvent event) {
    	Parent windowLoader;
        try {
            windowLoader = FXMLLoader.load(getClass().getResource("doctorPrescription.fxml"));
            Stage stage = (Stage) profile_button.getScene().getWindow();
            stage.setScene(new Scene(windowLoader));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void profile_button_Clicked(ActionEvent event) {

    }

    @FXML
    void viewRecord_button_Clicked(ActionEvent event) {

    }

}
