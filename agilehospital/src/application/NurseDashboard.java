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
        
        // Load the image using the class loader
        /*
        Image image = new Image("/image.png");
        ImageView imageView = new ImageView(image);
        */
        
        
        //Calendar
        Text calendar = new Text("Calendar");
        calendar.setLayoutX(150);
        calendar.setLayoutY(325);
        calendar.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        
        //User Options
        Text options = new Text("What would you like to do?");
        options.setLayoutX(150);
        options.setLayoutY(150);
        options.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        
        Button Record = new Button();
        Record.setText("Record Vitals");
        Record.setOnAction(event ->
        {
        	NurseCreateReport reportPage = new NurseCreateReport();
        	reportPage.display(primaryStage);
        }
        );
        
	    Record.setLayoutX(150);
        Record.setLayoutY(175);
        Record.setPrefSize(300,40);
     
        Button View = new Button();
        View.setText("View Patient Summary");
        View.setOnAction(event ->
        {
        	NursePatientSummary summaryPage = new NursePatientSummary();
        	summaryPage.display(primaryStage);
        }
        );

        View.setLayoutX(150);
        View.setLayoutY(225);
        View.setPrefSize(300,40);
        
	 
        //Menu
        Button Messages = new Button();
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
        Button backButton = new Button("Log out");
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
        
        
        /*
        // Create a DatePicker for selecting dates
        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(10);
        datePicker.setLayoutY(10);

        // Create a TableView for displaying days in a month
        TableView<String> tableView = new TableView<>();
        tableView.setLayoutX(10);
        tableView.setLayoutY(40);

        // Create columns for days in a week
        TableColumn<String, String> sunCol = new TableColumn<>("Sun");
        TableColumn<String, String> monCol = new TableColumn<>("Mon");
        TableColumn<String, String> tueCol = new TableColumn<>("Tue");
        TableColumn<String, String> wedCol = new TableColumn<>("Wed");
        TableColumn<String, String> thuCol = new TableColumn<>("Thu");
        TableColumn<String, String> friCol = new TableColumn<>("Fri");
        TableColumn<String, String> satCol = new TableColumn<>("Sat");

        // Add columns to the TableView
        tableView.getColumns().addAll(sunCol, monCol, tueCol, wedCol, thuCol, friCol, satCol);

        // Create a label for displaying the selected date
        Label selectedDateLabel = new Label("Selected Date:");
        selectedDateLabel.setLayoutX(10);
        selectedDateLabel.setLayoutY(350);

        // Create an HBox for the controls
        HBox controlBox = new HBox(10);
        controlBox.getChildren().addAll(datePicker, selectedDateLabel);
        controlBox.setLayoutX(10);
        controlBox.setLayoutY(380);
		*/
        

        
        Pane root = new Pane();
        //root.setCenter(vboxOptions);
        root.getChildren().add(title);
        root.getChildren().addAll(options, appointments, calendar, welcome);
        root.getChildren().addAll(Record, View, backButton, Messages);
        root.getChildren().add(scrollPane);
        
        // Add controls and TableView to the Pane
        //root.getChildren().addAll(controlBox, tableView);

        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.setTitle("Nurse Page");
        primaryStage.show();
    }
}