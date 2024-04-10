package application.FXMLHandlers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import application.serializedBackend.Patient;
import application.serializedBackend.Visit;
import application.serializedBackend.Vitals;



public class VisitHistoryHandler{
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
    private void initialize() {
        // Initialize your controller

    }

    @FXML
    private void populateVBoxWithButtons(Patient patient) {
        // Create buttons and add them to the VBox
        ArrayList<Visit> patient_visits = patient.visits;


        for (int i = 0; i < patient_visits.size(); i++) {
            Button button = new Button(patient_visits[i].date);
            button.setOnAction(event -> handleButtonClick(patient_visits[i]));
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
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/NurseDash.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}