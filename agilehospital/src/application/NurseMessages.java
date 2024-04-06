package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class NurseMessages {
	private static String user_name = "...";

	public static void display(Stage primaryStage) {
        Pane root = new Pane();
        
        //Page title
        Label title = new Label("Inbox");
        title.setLayoutX(100);
        title.setLayoutY(50);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        
        //Hello user
        Text welcome = new Text("Hello" + user_name);
        welcome.setLayoutX(800);
        welcome.setLayoutY(75);
        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
        
       
//Creating all page interactive elements
        
   //Received Messages:
        
        //white background for text 
        Rectangle inbox_sender = createRectangle(350, 25, 300, 145);
        Rectangle inbox_topic = createRectangle(350, 25, 300, 180);
        Rectangle inbox_message = createRectangle(350, 100, 300, 215);
        
        //actual text to be displayed
        TextFlow textFlowSender = new TextFlow();
        textFlowSender.setPadding(new Insets(10, 10, 10, 10));
        TextFlow textFlowTopic = new TextFlow();
        textFlowTopic.setPadding(new Insets(10, 10, 10, 10));
        TextFlow textFlowMessage = new TextFlow();
        textFlowMessage.setPadding(new Insets(10, 10, 10, 10));
        
        
        //Create pane to hold text and rectangle background
        StackPane stackPaneSender = new StackPane();
        stackPaneSender.setLayoutX(300);
        stackPaneSender.setLayoutY(145);
        stackPaneSender.setVisible(false);
        
        StackPane stackPaneTopic = new StackPane();
        stackPaneTopic.setLayoutX(300);
        stackPaneTopic.setLayoutY(180);
        stackPaneTopic.setVisible(false);
        
        StackPane stackPaneMessage = new StackPane();
        stackPaneMessage.setLayoutX(300);
        stackPaneMessage.setLayoutY(215);
        stackPaneMessage.setVisible(false);
        
        
        //Create text area to respond to message
        TextArea reply_message = new TextArea();
        reply_message.setPrefHeight(100);
        reply_message.setPrefWidth(350);
        reply_message.setLayoutX(300);
        reply_message.setLayoutY(350);
        reply_message.setPromptText("Message....");
        reply_message.setVisible(false);
        
        
        //Send the user's response message
        Button Send_Reply = createButton("Send Reply", 300, 470, 100, 40);
        Send_Reply.setOnAction(event -> 
        {
        	//TO DO
        	
        });
        Send_Reply.setVisible(false);
        
        
        //Display controls to respond to message
        Button Reply = createButton("Reply", 300, 325, 100, 20);
        Reply.setOnAction(event -> 
        {
        	Reply.setVisible(false);
        	reply_message.setVisible(true);
        	reply_message.clear(); //make sure text area is empty
        	Send_Reply.setVisible(true);
        	// TO DO
        	
        });
        Reply.setVisible(false);
        
        
    //Composing Message:
        
        //Textfields for composing messages
        TextField compose_recipient = new TextField();
        compose_recipient.setLayoutX(325);
        compose_recipient.setLayoutY(150);
        compose_recipient.setPrefWidth(350);
        compose_recipient.setPromptText("To....");
        compose_recipient.setVisible(false);
        
        TextField compose_subject = new TextField();
        compose_subject.setLayoutX(325);
        compose_subject.setLayoutY(190);
        compose_subject.setPrefWidth(350);
        compose_subject.setPromptText("Subject....");
        compose_subject.setVisible(false);
        
        TextArea compose_message = new TextArea();
        compose_message.setLayoutX(325);
        compose_message.setLayoutY(230);
        reply_message.setPrefHeight(100);
        reply_message.setPrefWidth(350);
        compose_message.setPromptText("Message....");
        compose_message.setVisible(false);
        
        
        Button Send = createButton("Send", 735, 450, 100, 20);
        Send.setOnAction(event -> 
        {
        	//TO DO
        	
        	
        });
        Send.setVisible(false);
        
      
        //Compose button
        Button Compose = createButton("Compose", 50, 140, 100, 20);
        Compose.setOnAction(event -> 
        {
        	//Display compose controls
        	Send.setVisible(true);
        	compose_recipient.setVisible(true);
        	compose_subject.setVisible(true);
        	compose_message.setVisible(true);
        	
        	//Hide received message controls
        	Reply.setVisible(false);
            reply_message.setVisible(false);
            Send_Reply.setVisible(false);
            stackPaneSender.setVisible(false);
            stackPaneTopic.setVisible(false);
            stackPaneMessage.setVisible(false);
        	
        });
        
        
        
//Create object to display and respond to received messages
        
        //message Inbox
        Text messages = new Text("Messages");
        messages.setLayoutX(50);
        messages.setLayoutY(215);
        messages.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        
        
        //Fill with data from back-end
        String[] stringsArray = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
                "Option 6", "Option 7", "Option 8", "Option 9", "Option 10", "Option 11", "Option 12", 
                "Option 13", "Option 14", "Option 15"}; // Your array of strings
        

        ObservableList<String> options = FXCollections.observableArrayList(stringsArray);
        //arraylist with message data
        ListView<String> listView = new ListView<>(options);
        
        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
            	//clear all previous data
            	textFlowSender.getChildren().clear();
            	stackPaneSender.getChildren().clear();
            	
            	textFlowTopic.getChildren().clear();
            	stackPaneTopic.getChildren().clear();
            	
            	textFlowMessage.getChildren().clear();
            	stackPaneMessage.getChildren().clear();
            	
            	//Display new message information
            	textFlowSender.getChildren().add(new Text(selectedItem));
                stackPaneSender.getChildren().addAll(inbox_sender, textFlowSender);
                
                textFlowTopic.getChildren().add(new Text(selectedItem));
                stackPaneTopic.getChildren().addAll(inbox_topic, textFlowTopic);
                
                textFlowMessage.getChildren().add(new Text(selectedItem));
                stackPaneMessage.getChildren().addAll(inbox_message, textFlowMessage);
            	
                
                //show received message controls
            	Reply.setVisible(true);
                stackPaneSender.setVisible(true);
                stackPaneTopic.setVisible(true);
                stackPaneMessage.setVisible(true);
                
                //Hide reply to message controls
                reply_message.setVisible(false);
                Send_Reply.setVisible(false);  
                
                //Hide Compose controls
                compose_recipient.setVisible(false);
            	compose_subject.setVisible(false);
            	compose_message.setVisible(false);
                Send.setVisible(false);
            }
        });

        //Box to scroll through all received messages
        ScrollPane scrollPane = new ScrollPane(listView);
        scrollPane.setFitToWidth(true); 
        scrollPane.setFitToHeight(true); 
        scrollPane.setPrefViewportHeight(200); 
        scrollPane.setPrefViewportWidth(200); 

        scrollPane.setLayoutX(50); 
        scrollPane.setLayoutY(250);
        
        
	    
        //back to Dashboard button
        Button Dashboard = new Button("Dashboard");
        Dashboard.setOnAction(event -> 
        {
        	NurseDashboard nursePage = new NurseDashboard();
            nursePage.display(primaryStage);	
        });
        
        Dashboard.setLayoutX(650);
        Dashboard.setLayoutY(50);
        Dashboard.setPrefSize(100,40);
         
        
        
        root.getChildren().add(title);
        root.getChildren().addAll(welcome, messages);
        root.getChildren().addAll(Dashboard, Compose, Send, Reply, Send_Reply);
        root.getChildren().addAll(stackPaneSender, stackPaneTopic, stackPaneMessage);
        root.getChildren().addAll(compose_subject, compose_recipient);
        root.getChildren().addAll(reply_message, compose_message);
        root.getChildren().add(scrollPane);

        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.setTitle("Messages");
        primaryStage.show();
    }
	
	
	private static Button createButton (String label, int X, int Y, int height, int width) {
		Button button = new Button(label);
		button.setLayoutX(X);
		button.setLayoutY(Y);
		button.setPrefSize(height,width);
		return button;
	}
	
	private static Rectangle createRectangle(int width, int height, int x, int y) {
		Rectangle rect = new Rectangle();
		rect.setWidth(width); // Add some padding
		rect.setHeight(height); // Add some padding
		rect.setFill(Color.WHITE); // Set the background color
        rect.setX(x);
        rect.setY(y);
        
        return rect;
	}
	
}

