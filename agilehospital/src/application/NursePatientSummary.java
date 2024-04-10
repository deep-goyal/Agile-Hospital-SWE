package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

	private static String patient_ID = "";
	private static String user_name = "...";
	
	private static NurseUtil utility = new NurseUtil();

	public static void display(Stage primaryStage, String patient_ID) throws IOException {
	        Pane root = new Pane();
	        
	        //Page title
	        Label title = new Label("Patient " + patient_ID + " Health Report");
	        title.setLayoutX(100);
	        title.setLayoutY(50);
	        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
	        
	        //Hello user
	        Text welcome = new Text("Hello" + user_name);
	        welcome.setLayoutX(810);
	        welcome.setLayoutY(100);
	        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
	        
	        
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
	        
	        
            HashMap<Integer, String> patientData = utility.getPatientReportData(patient_ID);
	        
	        Set<Integer> keySet = patientData.keySet();
	        Integer[] keysArray = keySet.toArray(new Integer[keySet.size()]);



	        ObservableList<Integer> options = FXCollections.observableArrayList(keysArray);
	        ListView<Integer> listView = new ListView<>(options);
	        
	        listView.setOnMouseClicked(event -> {
	            Integer selectedItem = listView.getSelectionModel().getSelectedItem();
	            if (selectedItem != 0) {
	            	String visit_summary = String.join("\n", patientData.get(selectedItem).split("#"));
	  
	            	visit_date.setVisible(true);
	                visit_date.setText(visit_summary);
	                
	                
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
        text.setWrappingWidth(200); 
        text.setTextAlignment(TextAlignment.CENTER); 
        text.setLineSpacing(10); 
        text.setTranslateX(10); 
        text.setTranslateY(10); 

		
		return text;
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
