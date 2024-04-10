package application.FXMLHandlers;

import application.serializedBackend.Patient;
import application.serializedBackend.Prescription;
import application.serializedBackend.Visit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.*;

public class PatientExamination {
    //fxml tags for all nodes
    @FXML
    private Button backbtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private TextField patientID;
    @FXML
    private TextField date;
    @FXML
    private TextField medication;
    @FXML
    private TextArea notes;

    //back button action
    public void back() throws IOException {
        //redirect to DoctorDash.fxml
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/DoctorDash.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //confirm button action
    public void confirm() {
        try {
            // Find the patientID.ser file and deserialize it
            FileInputStream fileIn = new FileInputStream(patientID.getText() + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Patient patient = (Patient) in.readObject();
            in.close();
            fileIn.close();

            // Modify the last visit of the patient
            Visit lastVisit = patient.visits.get(patient.visits.size() - 1);
            lastVisit.setPrescription(new Prescription(date.getText(), medication.getText(), notes.getText()));

            // Serialize the modified patient object and save it in patientID.ser file
            FileOutputStream fileOut = new FileOutputStream(patientID.getText() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(patient);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Patient class not found");
            c.printStackTrace();
        }

    }
}
