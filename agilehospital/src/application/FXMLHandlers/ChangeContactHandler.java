package application.FXMLHandlers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import application.serializedBackend.SignUp;

import java.io.IOException;


public class ChangeContactHandler{
    private SignUp utility = new SignUp();

    @FXML
    private Button backbtn;

    @FXML
    private Button confirmbtn;

    @FXML
    private TextField patientID;

    @FXML
    private TextField phoneNumber;

    public void confirm() throws IOException {

        if (utility.setPhoneNumber(patientID.getText(),phoneNumber.getText())){
            System.out.println("Successful update");
        } else {
            System.out.println("Update failed");
        }

    }

    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/PatientDash.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}