package application.FXMLHandlers;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NurseDashHandler {
    @FXML
    private Button createvitalsbtn;
    @FXML
    private Button viewhistorybtn;
    @FXML
    private Button viewinboxbtn;

    public void createvitals() throws IOException {
        // Correctly reference the path from the class's location
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/CreateVitals.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public void viewhistory() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/NurseVisitHistory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    public void viewinbox() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXMLFiles/NurseMessages.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }


}