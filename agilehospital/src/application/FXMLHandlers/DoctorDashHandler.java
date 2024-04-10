package application.FXMLHandlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DoctorDashHandler {
    //fxml tags for all nodes
    @FXML
    private Button examinePatient;
    @FXML
    private Button viewMedHistory;
    @FXML
    private Button viewInbox;

    //view reports button action
    public void examinePatientFunc() {
        //redirect to PatientReports.fxml
        System.out.print("View Reports");
    }

    //change info button action
    public void viewMedHistoryFunc() {
        //redirect to PatientChangeInfo.fxml
    }

    //view inbox button action
    public void viewInboxFunc() {
        //redirect to PatientInbox.fxml
    }

}
