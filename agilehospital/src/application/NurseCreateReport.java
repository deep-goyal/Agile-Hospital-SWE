package application;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NurseCreateReport {
	private static String user_name = "...";
	private static String patient_name = "...";
	private static String healthConcerns = "..";
	private static String allergies = "...";

	public static void display(Stage primaryStage) {
		Pane root = new Pane();
        
        //Page title
        Label title = new Label("Patient Physical");
        title.setLayoutX(100);
        title.setLayoutY(50);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        
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
        
        //Message button
        Button Messages = new Button();
        Messages.setText("Messages");
        Messages.setOnAction(event ->
        {
        	NurseMessages messagePage = new NurseMessages();
        	messagePage.display(primaryStage);
        }
        );
        Messages.setLayoutX(675);
        Messages.setLayoutY(50);
        Messages.setPrefSize(100,40);
	 
	    
        //Dashboard button
        Button Dashboard = new Button("Dashboard");
        Dashboard.setOnAction(event -> 
        {
        	NurseDashboard nursePage = new NurseDashboard();
            nursePage.display(primaryStage);	
        });
        
        Dashboard.setLayoutX(550);
        Dashboard.setLayoutY(50);
        Dashboard.setPrefSize(100,40);
        
        
      
        
        //data entry
        TextField textField_date = createTextField(25, 300);
        HBox hb_date = createTextBox("Date", textField_date, 150, 150, 85);
        textField_date.setOnAction(event -> {
            System.out.println("Selected option: " + textField_date.getText());
        });
        

        TextField textField_weight = createTextField(25, 300);
        HBox hb_weight = createTextBox("Weight", textField_weight, 150, 200, 73);
        textField_weight.setOnAction(event -> {
            System.out.println("Selected option: " + textField_weight.getText());
        });
        
        
        TextField textField_bodyTemp = createTextField(25, 300);
        HBox hb_bodyTemp = createTextBox("Body Temperature", textField_bodyTemp, 150, 250, 10);
        textField_bodyTemp.setOnAction(event -> {
            System.out.println("Selected option: " + textField_bodyTemp.getText());
        });
        
        
        TextField textField_bloodPres = createTextField(25, 300);
        HBox hb_bloodPressure = createTextBox("Blood Pressure", textField_bloodPres, 150, 300, 30);
        textField_bloodPres.setOnAction(event -> {
            System.out.println("Selected option: " + textField_bloodPres.getText());
        });
        
        
        TextArea textArea_allergies = createTextArea(75, 300);
        textArea_allergies.setWrapText(true);
        HBox hb_allergies = createTextArea("Allergies", textArea_allergies, 150, 350, 65);
        textArea_allergies.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // This code will be executed whenever the text in the TextArea changes
                //System.out.println("Text changed: " + newValue);
            	allergies = newValue;
                
            }
        });
        
        TextArea textArea_healthConcerns = createTextArea(75, 300);
        textArea_allergies.setWrapText(true);
        HBox hb_healthConcerns = createTextArea("Health Concerns", textArea_healthConcerns, 150, 450, 20);
        textArea_healthConcerns.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // This code will be executed whenever the text in the TextArea changes
                //System.out.println("Text changed: " + newValue);
                healthConcerns = newValue;
            }
        });
        
        
        //text to provide user with instructions or error messages
        Text messageText = new Text();
        messageText.setLayoutX(615);
        messageText.setLayoutY(470);
        messageText.setText("Please press 'enter' after typing in textbox to save");
        
        //Save button
        Button Save = new Button("Submit");
        Save.setOnAction(event -> 
        {
        	boolean requiredFieldsFilled = true;

            // Check each text field for content
            if (textField_date.getText().isEmpty() || textField_weight.getText().isEmpty() ||
                    textField_bodyTemp.getText().isEmpty() || textField_bloodPres.getText().isEmpty() 
            ) {
            	requiredFieldsFilled = false;
            }

            if (requiredFieldsFilled) {
                messageText.setText("Data Saved");
                messageText.setLayoutX(715);
                
            } else {
                messageText.setText("Fill in all required elements before submitting");
                messageText.setLayoutX(630);
                
            }
        	
        	System.out.println(healthConcerns);
        });
        
        Save.setLayoutX(700);
        Save.setLayoutY(400);
        Save.setPrefSize(100,40);
        
        root.getChildren().add(title);
        root.getChildren().addAll(welcome, messageText);
        root.getChildren().addAll(Messages, Dashboard, Save);
        root.getChildren().addAll(hb_date, hb_weight, hb_bodyTemp, hb_bloodPressure, hb_allergies, hb_healthConcerns);

        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.setTitle("Patient Physical Report");
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
	
	private static HBox createTextArea(String label, TextArea textArea, int X, int Y, int spacing) {
		Label label_name = new Label(label);
        HBox hb = new HBox();
        hb.getChildren().addAll(label_name, textArea);
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
	
	private static TextArea createTextArea(int height, int width) {
		TextArea textArea = new TextArea();
		textArea.setPrefHeight(height);
        textArea.setPrefWidth(width);
		return textArea;
	}
}

