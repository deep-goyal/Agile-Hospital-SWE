package application.FXMLHandlers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class DoctorDashHandler {
    //fxml tags for all nodes
    @FXML
    private Button examinePatient;
    @FXML
    private Button viewMedHistory;
    @FXML
    private Button viewInbox;

    //view reports button action
    public void examinePatientFunc() throws IOException {
        //redirect to ExaminePatient.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/ExaminePatient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //change info button action
    public void viewMedHistoryFunc() throws IOException {
        //redirect to PatientChangeInfo.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/DocVisitHistory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //view inbox button action TODO-----------
    public void viewInboxFunc() throws IOException {
        //redirect to PatientInbox.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/DocMessages.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
