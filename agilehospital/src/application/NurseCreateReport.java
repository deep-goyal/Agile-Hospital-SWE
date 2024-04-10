package application;


import java.io.IOException;

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
	private static String patient_ID = "...";
	private static String weight = "";
	private static String height = "";
	private static String body_temp = "";
	private static String blood_pressure = "";
	private static String healthConcerns = "";
	private static String allergies = "";
	private static String immunization = "";
	
	private static NurseUtil utility = new NurseUtil();

	public static void display(Stage primaryStage, String patient_ID) {
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
        TextField textField_height = createTextField(25, 300);
        HBox hb_height = createTextBox("Height", textField_height, 150, 150, 75);
        textField_height.setOnAction(event -> {
            height = "Height: " + textField_height.getText();
        	System.out.println("Selected option: " + textField_height.getText());
        });
        

        TextField textField_weight = createTextField(25, 300);
        HBox hb_weight = createTextBox("Weight", textField_weight, 150, 190, 73);
        textField_weight.setOnAction(event -> {
        	weight = "Weight: " + textField_weight.getText();
            System.out.println("Selected option: " + textField_weight.getText());
        });
        
        
        TextField textField_bodyTemp = createTextField(25, 300);
        HBox hb_bodyTemp = createTextBox("Body Temperature", textField_bodyTemp, 150, 230, 10);
        textField_bodyTemp.setOnAction(event -> {
        	body_temp = "Body Tempurature: " + textField_bodyTemp.getText();
        	System.out.println("Selected option: " + textField_bodyTemp.getText());
        });
        
        
        TextField textField_bloodPres = createTextField(25, 300);
        HBox hb_bloodPressure = createTextBox("Blood Pressure", textField_bloodPres, 150, 270, 30);
        textField_bloodPres.setOnAction(event -> {
        	blood_pressure = "Blood Pressure: " + textField_bodyTemp.getText();
        	System.out.println("Selected option: " + textField_bloodPres.getText());
        });
        
        
        TextArea textArea_allergies = createTextArea(65, 300);
        textArea_allergies.setWrapText(true);
        HBox hb_allergies = createTextArea("Allergies", textArea_allergies, 150, 310, 65);
        textArea_allergies.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // This code will be executed whenever the text in the TextArea changes
                //System.out.println("Text changed: " + newValue);
            	
            	allergies =  "Allergies : " + newValue;
            }
        });
        
        TextArea textArea_healthConcerns = createTextArea(65, 300);
        textArea_allergies.setWrapText(true);
        HBox hb_healthConcerns = createTextArea("Health Concerns", textArea_healthConcerns, 150, 390, 20);
        textArea_healthConcerns.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // This code will be executed whenever the text in the TextArea changes
                //System.out.println("Text changed: " + newValue);
                healthConcerns = "Health Concerns: " + newValue;
            }
        });
        
        TextField textField_immunization = createTextField(25, 300);
        HBox hb_Immunization = createTextBox("Immunization", textField_immunization, 150, 470, 37);
        textField_immunization.setOnAction(event -> {
        	immunization = "Immunization : " + textField_immunization.getText();
        	System.out.println("Selected option: " + textField_immunization.getText());
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
            if (textField_height.getText().isEmpty() || textField_weight.getText().isEmpty() ||
                    textField_bodyTemp.getText().isEmpty() || textField_bloodPres.getText().isEmpty() 
            ) {
            	requiredFieldsFilled = false;
            }

            if (requiredFieldsFilled) {
                messageText.setText("Data Saved");
                messageText.setLayoutX(715);
                
                String data = "\"" + weight + "#" + 
                		height + "#" + 
                		body_temp + "#" + 
                		blood_pressure + "#" + 
                		healthConcerns.replace("\n", "") + 
                		"#" + allergies.replace("\n", "") + 
                		"#" + immunization + "\"";
                try {
					utility.writePatientReportToFile(patient_ID, data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
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
        root.getChildren().addAll(hb_height, hb_weight, hb_bodyTemp, hb_bloodPressure,
        		hb_allergies, hb_healthConcerns, hb_Immunization);

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

