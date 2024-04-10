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
    private Button viewReport_button;
    
    public static String selectedPatientID;
    
    public void initialize() throws IOException {
        if (selectedPatientID != null) {
        	try {
                // Assuming getPatientReportData returns a string with newline characters for each report entry
                String patientHistory = getFormattedPatientHistory(selectedPatientID);
                patHistory_TA.setText(patientHistory);
                patOverview_label.setText("Patient Overview: " + selectedPatientID);
            } catch (Exception e) {
                e.printStackTrace();
                patHistory_TA.setText("Failed to load patient history.");
            }
        	// Further implementation for fetching and displaying patient's data goes here
        }
    }
    
    private String getFormattedPatientHistory(String patientID) throws IOException {
        // Utilize NurseUtil.getPatientReportData() or similar method to fetch patient history
        return NurseUtil.getPatientReportData(patientID).values().stream()
                .reduce("", (partialString, element) -> partialString + element + "\n\n");
    }

    @FXML
    void back_clicked(ActionEvent event) throws IOException {
        Parent windowLoader = FXMLLoader.load(getClass().getResource("docView.fxml"));
        Stage stage = (Stage) back_button.getScene().getWindow();
        stage.setScene(new Scene(windowLoader));
        stage.show();
    }

    @FXML
    void dashBoard_clicked(ActionEvent event) throws IOException {
        back_clicked(event); // Reuse back button functionality
    }

    @FXML
    void messages_clicked(ActionEvent event) throws IOException {
        Parent windowLoader = FXMLLoader.load(getClass().getResource("doctorMessages.fxml"));
        Stage stage = (Stage) dashboard_button.getScene().getWindow();
        stage.setScene(new Scene(windowLoader));
        stage.show();
    }

    @FXML
    void prescribeMeds_clicked(ActionEvent event) throws IOException {
        Parent windowLoader = FXMLLoader.load(getClass().getResource("doctorPrescription.fxml"));
        Stage stage = (Stage) dashboard_button.getScene().getWindow();
        stage.setScene(new Scene(windowLoader));
        stage.show();
    }

    @FXML
    void viewReport_clicked(ActionEvent event) {
        // Implementation for viewing reports needs to be added here
    }
}
