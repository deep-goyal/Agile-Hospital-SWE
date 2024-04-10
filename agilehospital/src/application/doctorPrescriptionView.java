package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class doctorPrescriptionView {

    private static int prescriptionCount = 0; // Static variable to keep track of the prescription number

    @FXML
    private TextField patient_id; // Patient ID
    @FXML
    private TextField date_TF; // Date prescribed
    @FXML
    private TextField medication_TF; // Medication
    @FXML
    private TextArea notes_TF; // Notes

    @FXML
    void confirm_clicked(ActionEvent event) {
    	
        prescriptionCount++;

        String patientName = patient_id.getText().trim(); // Assuming patient_id TextField contains the patient name
        String homeDirectory = System.getProperty("user.home");
        String patientsDirectory = homeDirectory + File.separator + "Patients";
        String patientDirectory = patientsDirectory + File.separator + sanitizeFileName(patientName);
        String visitsDirectory = patientDirectory + File.separator + "visits";

        new File(visitsDirectory).mkdirs();

        String filename = String.format("%d_%s_prescription.txt", prescriptionCount, patientName);
        File file = new File(visitsDirectory, filename);
        String content = String.format("%s\\\\\\%s\\\\\\%s", date_TF.getText(), medication_TF.getText(), notes_TF.getText());

        try {
        	
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
        	
            e.printStackTrace(); 
            
        }
    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
    
    @FXML
    void back_clicked(ActionEvent event) {
        try {
            Parent previousView = FXMLLoader.load(getClass().getResource("docView.fxml")); 
            Scene scene = new Scene(previousView);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
