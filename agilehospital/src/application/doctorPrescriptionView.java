package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class doctorPrescriptionView {

    @FXML
    private Button back_Button;

    @FXML
    private Button confirm_button;

    @FXML
    private TextField medication_TF;

    @FXML
    private Label medication_label;

    @FXML
    private TextField notes_TF;

    @FXML
    private Label notes_label;

    @FXML
    private Label orderPrescription_label;

    @FXML
    private Label patientName_label;

    @FXML
    private Button profile_button;

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
    void confirm_clicked(ActionEvent event) {

    }

    @FXML
    void profile_clicked(ActionEvent event) {

    }

}
