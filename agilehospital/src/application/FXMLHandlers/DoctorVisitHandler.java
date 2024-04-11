package application.FXMLHandlers;
import application.Main;
import application.serializedBackend.Prescription;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import application.serializedBackend.Patient;
import application.serializedBackend.Visit;
import application.serializedBackend.Vitals;

import java.io.IOException;
import java.util.ArrayList;


public class DoctorVisitHandler {
    @FXML
    private Button backbtn;

    @FXML
    private Button enterbtn;

    @FXML
    private TextField patientID;

    @FXML
    private VBox leftVBox;

    @FXML
    private TextFlow rightTextFlow;



    @FXML
    private void populateVBoxWithButtons(Patient patient) {
        // Create buttons and add them to the VBox
        ArrayList<Visit> patient_visits = patient.visits;


        for (int i = 0; i < patient_visits.size(); i++) {
            Button button = new Button(patient_visits.get(i).date);
            Visit visit = patient_visits.get(i);
            button.setOnAction(event -> handleButtonClick(visit));
            leftVBox.getChildren().add(button); // Add the button to the VBox
        }
    }


    @FXML
    private void handleButtonClick(Visit visit) {
        Prescription prescription = visit.prescription;
        String[] prescription_Data = prescription.getPrescription();
        Text text = new Text(visit.concerns + "\n" + prescription_Data[0] + "\n" + prescription_Data[1] + "\n");
        rightTextFlow.getChildren().add(text);
    }

    @FXML
    public void enter() throws IOException {
        Patient patient = new Patient(patientID.getText());
        populateVBoxWithButtons(patient);
    }

    @FXML
    public void back() throws IOException {
        //redirect to NurseDash.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/DoctorDash.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
