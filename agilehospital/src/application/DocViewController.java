package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Button prescribeMeds_button;
    
    @FXML 
    private Label timeX_label;
    
    @FXML 
    private Label timeY_label;
    
    @FXML 
    private Button viewRecord_button;
    
    @FXML 
    private ListView<String> listView_patQueue;

    public void initialize() throws IOException {
        List<String> patientIDs = Arrays.asList("Patient1", "Patient2", "Patient3"); // Placeholder for actual patient ID fetching logic
        listView_patQueue.getItems().addAll(patientIDs);

        displayMostRecentPatientDetails();
    }

    private void displayMostRecentPatientDetails() throws IOException {
        String mostRecentFilePath = Files.walk(Paths.get(NurseUtil.REPORTDIRECTORY))
            .filter(f -> f.toString().endsWith("_VITALS.txt"))
            .max(Comparator.comparingLong(f -> f.toFile().lastModified()))
            .map(f -> f.toString())
            .orElse(null);
        
        if (mostRecentFilePath != null) {
            String details = new String(Files.readAllBytes(Paths.get(mostRecentFilePath)));
            patInfo_textarea.setText(details);
        }
    }

    @FXML
    void message_button_clicked(ActionEvent event) throws IOException {
        Parent windowLoader = FXMLLoader.load(getClass().getResource("doctorMessages.fxml"));
        Stage stage = (Stage) messages_button.getScene().getWindow();
        stage.setScene(new Scene(windowLoader));
        stage.show();
    }

    @FXML
    void prescribeMeds_button_clicked(ActionEvent event) throws IOException {
        Parent windowLoader = FXMLLoader.load(getClass().getResource("doctorPrescription.fxml"));
        Stage stage = (Stage) messages_button.getScene().getWindow();
        stage.setScene(new Scene(windowLoader));
        stage.show();
    }

    @FXML
    void viewRecord_button_Clicked(ActionEvent event) throws IOException {
        String selectedPatientID = listView_patQueue.getSelectionModel().getSelectedItem();
        if (selectedPatientID != null) {
            doctorPatRecords_Controller.selectedPatientID = selectedPatientID;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorPatientRecords.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) viewRecord_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("No patient selected");
        }
    }
}
