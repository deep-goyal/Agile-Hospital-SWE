package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class NursePatientSummary {

	private static String patient_name = "...";
	private static String user_name = "...";

	public static void display(Stage primaryStage) {
	        Pane root = new Pane();
	        
	        //Page title
	        Label title = new Label("Patient " + patient_name + " Health Report");
	        title.setLayoutX(100);
	        title.setLayoutY(50);
	        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
	        
	        //Hello user
	        Text welcome = new Text("Hello" + user_name);
	        welcome.setLayoutX(810);
	        welcome.setLayoutY(100);
	        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
	        
	        // Load the image using the class loader
	        /*
	        Image image = new Image("/image.png");
	        ImageView imageView = new ImageView(image);
	        */
	        
	        //Message button
	        Button Messages = createButton("Messages", 675, 50, 100, 40);
	        Messages.setOnAction(event ->
	        {
	        	NurseMessages messagePage = new NurseMessages();
	        	messagePage.display(primaryStage);
	        });
	       
		    
	        //Dashboard button
	        Button Dashboard = createButton("Dashboard", 550, 50, 100, 40);
	        Dashboard.setOnAction(event -> 
	        {
	        	NurseDashboard nursePage = new NurseDashboard();
	            nursePage.display(primaryStage);	
	        });
	        
	        
	      //message Inbox
	        Text visits = new Text("Visits");
	        visits.setLayoutX(50);
	        visits.setLayoutY(215);
	        visits.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
	     
	        
	        
	        Text visit_date = createPrivateText();
	        Text visit_reason = createPrivateText();
	        
	        
	        TextFlow textFlow = new TextFlow(visit_date, visit_reason);
	        textFlow.setLineSpacing(10); // Set line spacing between text nodes
	        
	        
	        String[] stringsArray = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
	                "Option 6", "Option 7", "Option 8", "Option 9", "Option 10", "Option 11", "Option 12", 
	                "Option 13", "Option 14", "Option 15"}; // Your array of strings
	        

	        ObservableList<String> options = FXCollections.observableArrayList(stringsArray);
	        ListView<String> listView = new ListView<>(options);
	        
	        listView.setOnMouseClicked(event -> {
	            String selectedItem = listView.getSelectionModel().getSelectedItem();
	            if (selectedItem != null) {
	                visit_date.setVisible(true);
	                visit_date.setText("Date: " + selectedItem + "\n");
	                
	                visit_reason.setVisible(true);
	                visit_reason.setText("Reason: " + selectedItem + "\n");
	                
	                
	            	
	            	/* Perform action when an item is clicked
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Item Clicked");
	                alert.setHeaderText("You clicked: " + selectedItem);
	                alert.showAndWait();
	                */
	                
	                
	            }
	        });

	        ScrollPane scrollPane = new ScrollPane(listView);
	        scrollPane.setFitToWidth(true); // Expand the content horizontally to fill the scroll pane
	        scrollPane.setFitToHeight(true); // Expand the content vertically to fill the scroll pane
	        scrollPane.setPrefViewportHeight(200); // Set the preferred height of the scroll pane
	        scrollPane.setPrefViewportWidth(200); // Set the preferred width of the scroll pane

	        scrollPane.setLayoutX(50); // Position the scroll pane within the pane
	        scrollPane.setLayoutY(250);
	        
	        Rectangle background = new Rectangle();
	        background.setWidth(500); // Add some padding
	        background.setHeight(300); // Add some padding
	        background.setFill(Color.WHITE); // Set the background color
	        background.setX(400);
	        background.setY(200);
	        background.setStroke(Color.BLACK); // Set border color here
	        background.setStrokeWidth(2); // Set border width
	        
	        StackPane stackPane = new StackPane();
	        stackPane.getChildren().addAll(background, textFlow);
	        stackPane.setLayoutX(400);
	        stackPane.setLayoutY(200);
	        
	        root.getChildren().add(title);
	        root.getChildren().addAll(welcome, visits);
	        root.getChildren().addAll(Messages, Dashboard);
	        root.getChildren().add(scrollPane);
	        root.getChildren().add(stackPane);

	        primaryStage.setScene(new Scene(root, 950, 600));
	        primaryStage.setTitle("Patient Report Summary");
	        primaryStage.show();
	    }
	
	private static Button createButton (String label, int X, int Y, int height, int width) {
		Button button = new Button(label);
		button.setLayoutX(X);
		button.setLayoutY(Y);
		button.setPrefSize(height,width);
		return button;
	}
	
	private static Text createPrivateText() {
		Text text = new Text();
		text.setVisible(false);
		text.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		text.setFill(javafx.scene.paint.Color.BLACK);
		
		
        // Set padding for each text node
        text.setWrappingWidth(200); // Set the width for wrapping
        text.setTextAlignment(TextAlignment.CENTER); // Center the text
        text.setLineSpacing(10); // Set line spacing
        text.setTranslateX(10); // Left padding
        text.setTranslateY(10); // Top padding

		
		return text;
	}
}
