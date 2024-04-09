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

public class doctorPatRecords_Controller {

    @FXML
    private Button back_button;

    @FXML
    private Button dashboard_button;

    @FXML
    private Button messages_button;

    @FXML
    private TextArea patHistory_TA;

    @FXML
    private Label patOverview_label;

    @FXML
    private Button prescribeMeds_button;

    @FXML
    private Button profile_button;

    @FXML
    private Button viewReport_button;

    @FXML
    void back_clicked(ActionEvent event) {
    	
    }

    @FXML
    void dashBoard_clicked(ActionEvent event) {
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
    void messages_clicked(ActionEvent event) {

    }

    @FXML
    void prescribeMeds_clicked(ActionEvent event) {

    }

    @FXML
    void profile_clicked(ActionEvent event) {

    }

    @FXML
    void viewReport_clicked(ActionEvent event) {

    }

}