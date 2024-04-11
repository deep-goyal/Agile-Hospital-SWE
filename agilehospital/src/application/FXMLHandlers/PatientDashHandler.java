package application.FXMLHandlers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class PatientDashHandler {
    //fxml tags for all nodes
    @FXML
    private Button viewReports;
    @FXML
    private Button changeInfo;
    @FXML
    private Button viewInbox;

    //view reports button action
    public void viewReportsFunc() throws IOException {
        //redirect to PatientReports.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/VisitHistory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //change info button action
    public void changeInfoFunc() throws IOException {
        //redirect to PatientChangeInfo.fxml TODO-------------------
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/ChangeContact.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //view inbox button action
    public void viewInboxFunc() throws IOException{
        //redirect to PatientInbox.fxml todo--------------
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/ChangeContact.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
