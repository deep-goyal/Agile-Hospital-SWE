package application.FXMLHandlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PatientDashHandler {
    //fxml tags for all nodes
    @FXML
    private Button viewReports;
    @FXML
    private Button changeInfo;
    @FXML
    private Button viewInbox;

    //view reports button action
    public void viewReportsFunc() {
        //redirect to PatientReports.fxml
        System.out.print("View Reports");
    }

    //change info button action
    public void changeInfoFunc() {
        //redirect to PatientChangeInfo.fxml
    }

    //view inbox button action
    public void viewInboxFunc() {
        //redirect to PatientInbox.fxml
    }

}
