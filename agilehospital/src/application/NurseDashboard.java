package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NurseDashboard {

	private static String user_name = "..";
	private static String patient_ID = "";
	
	 public static void display(Stage primaryStage) {
	        
	    Label title = new Label("Nurse Dashboard");
        title.setLayoutX(100);
        title.setLayoutY(50);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        
        //Appointments
        Text appointments = new Text("User Appointments");
        appointments.setLayoutX(575);
        appointments.setLayoutY(150);
        appointments.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        
        //Create buttons
        Button Record = new Button();
        Record.setVisible(false);
        Button View = new Button();
        View.setVisible(false);
        Button Messages = new Button();
        Button backButton = new Button("Log out");
        
       
        String[] stringsArray = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
                "Option 6", "Option 7", "Option 8", "Option 9", "Option 10", "Option 11", "Option 12", 
                "Option 13", "Option 14", "Option 15"}; // Your array of strings
        

        ObservableList<String> data = FXCollections.observableArrayList(stringsArray);
        ListView<String> listView = new ListView<>(data);
        
        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Perform action when an item is clicked
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Clicked");
                alert.setHeaderText("You clicked: " + selectedItem);
                alert.showAndWait();
            }
        });

        ScrollPane scrollPane = new ScrollPane(listView);
        scrollPane.setFitToWidth(true); // Expand the content horizontally to fill the scroll pane
        scrollPane.setFitToHeight(true); // Expand the content vertically to fill the scroll pane
        scrollPane.setPrefViewportHeight(200); // Set the preferred height of the scroll pane
        scrollPane.setPrefViewportWidth(300); // Set the preferred width of the scroll pane

        scrollPane.setLayoutX(570); // Position the scroll pane within the pane
        scrollPane.setLayoutY(170);
        
        //Hello user
        Text welcome = new Text("Hello" + user_name);
        welcome.setLayoutX(800);
        welcome.setLayoutY(100);
        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
        
        
        TextField textField_id = createTextField(25, 100);
        HBox hb_ID = createTextBox("Enter Patient ID to continue:", textField_id, 150, 175, 20);
        textField_id.setOnAction(event -> {
            patient_ID = textField_id.getText();
            Record.setVisible(true);
            View.setVisible(true);      
        });
        
        
        //User Options
        Text options = new Text("What would you like to do?");
        options.setLayoutX(150);
        options.setLayoutY(150);
        options.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        
        
        Record.setText("Record Vitals");
        Record.setOnAction(event ->
        {
        	NurseCreateReport reportPage = new NurseCreateReport();
        	reportPage.display(primaryStage, patient_ID);
        }
        );
        
	    Record.setLayoutX(150);
        Record.setLayoutY(225);
        Record.setPrefSize(300,40);
     
        
        View.setText("View Patient Summary");
        View.setOnAction(event ->
        {
        	NursePatientSummary summaryPage = new NursePatientSummary();
        	try {
				summaryPage.display(primaryStage, patient_ID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        );

        View.setLayoutX(150);
        View.setLayoutY(275);
        View.setPrefSize(300,40);
        
	 
        //Menu
        
        Messages.setText("Messages");
        Messages.setOnAction(event ->
        {
        	NurseMessages messagePage = new NurseMessages();
        	messagePage.display(primaryStage);
        }
        );
        
        Messages.setLayoutX(650);
        Messages.setLayoutY(50);
        Messages.setPrefSize(100,40);
	    
        //Home Button
        
        backButton.setOnAction(event -> 
        {
        	Main mainPage = new Main();
            try {
				mainPage.start(primaryStage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        });
        backButton.setLayoutX(800);
        backButton.setLayoutY(525);
        backButton.setPrefSize(100,40);
        
        
        
        Pane root = new Pane();
        //root.setCenter(vboxOptions);
        root.getChildren().add(title);
        root.getChildren().addAll(options, appointments, welcome);
        root.getChildren().addAll(Record, View, backButton, Messages);
        root.getChildren().add(scrollPane);
        root.getChildren().add(hb_ID);
        
        // Add controls and TableView to the Pane
        //root.getChildren().addAll(controlBox, tableView);

        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.setTitle("Nurse Page");
        primaryStage.show();
    }
	 
	 private static HBox createTextBox(String label, TextField textField, int X, int Y, int spacing){
        Label label_name = new Label(label);
        HBox hb = new HBox();
        hb.getChildren().addAll(label_name, textField);
        hb.setSpacing(spacing);
        hb.setLayoutX(X);
        hb.setLayoutY(Y);
        return hb;     
	}
	
	private static TextField createTextField(int height, int width){
        TextField textField = new TextField ();
        textField.setPrefHeight(height);
        textField.setPrefWidth(width);
        return textField;
    }

}