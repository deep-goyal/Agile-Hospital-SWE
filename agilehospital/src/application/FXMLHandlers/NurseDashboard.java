package application.FXMLHandlers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NurseDashboard {

    @FXML
    private Button backbtn;

    @FXML
    private Button createvitalsbtn;

    @FXML
    private Button viewhistorybtn;

    @FXML
    private Button viewinboxbtn;

    @FXML
    public void createvitals() throws IOException {
        //redirect to CreateVitals.fxml
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/CreateVitals.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void viewhistory() throws IOException{
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/VisitHistory.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void viewinbox() throws IOException{
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/PatientMessages.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void back() throws IOException {
        //redirect to NurseDash.fxml
        Stage stage = (Stage) backbtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLFiles/NurseDash.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}