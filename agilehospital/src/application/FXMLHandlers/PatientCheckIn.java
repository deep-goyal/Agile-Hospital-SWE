package application.FXMLHandlers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import application.serializedBackend.Patient;
import application.serializedBackend.Visit;
import application.serializedBackend.Vitals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PatientCheckIn {

    //fxml tags for all nodes
    @FXML
    private TextField patientID;
    @FXML
    private DatePicker date;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField temp;
    @FXML
    private TextField bp;
    @FXML
    private TextField allergies;
    @FXML
    private TextField concerns;
    @FXML
    private Button backbtn;
    @FXML
    private Button submitbtn;

    //back button action
    public void back() throws IOException {
        //redirect to NurseDash.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/NurseDash.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //submit button action
    public void submit() {
        //create a new visit object
        //capture data from textfields
        String dateText = this.date.getValue().toString();
        double heightText = Double.parseDouble(height.getText());
        double weightText = Double.parseDouble(weight.getText());
        double tempText = Double.parseDouble(temp.getText());
        double bpText = Double.parseDouble(bp.getText());
        Vitals vitals = new Vitals(heightText, weightText, tempText, bpText);
        String allergiesText = allergies.getText();
        String concernsText = concerns.getText();


        Visit visit = new Visit(dateText, vitals, allergiesText, concernsText);
        //add the visit to the patient's visits
        Patient patient = new Patient(patientID.getText());
        patient.visits.add(visit);

        //persistent storage by serialization
        try {
            FileOutputStream fileOut = new FileOutputStream(patientID.getText() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(patient);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in patient.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
}