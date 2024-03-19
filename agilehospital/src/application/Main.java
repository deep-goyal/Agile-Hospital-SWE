package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    	//define the root
    	BorderPane root = new BorderPane();
    	
    	//title def
    	Label title = new Label("Agile Hospital");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        
        //center pane
        VBox vbox = new VBox();
        
        //username hbox
        HBox userName = new HBox();
        Text userLabel = new Text("Username: ");
        TextField userNameField = new TextField();
        
        //password hbox
        HBox passBox = new HBox();
        Text passLabel = new Text("Password: ");
        TextField passField = new TextField();
        
        //login button
        HBox loginBox = new HBox();
        Button loginButton = new Button("Login");
        
        //bottom pane
        
        //create acc instance
        Button createAcc = new Button("Create Account");
        createAcc.setOnAction(e -> switchToCreateAccount(primaryStage));
        
        //forgot pass
        Button forgotButton = new Button("Forgot Password");
        
        HBox bottomButtons = new HBox();
        
        
        
        //alignment
        userName.setAlignment(Pos.CENTER);
        userName.setSpacing(30);
        passBox.setAlignment(Pos.CENTER);
        passBox.setSpacing(31);
        root.setPadding(new Insets(50));
        vbox.setPadding(new Insets(50));
        vbox.setSpacing(15);
        loginBox.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(30);
        bottomButtons.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(vbox, Pos.CENTER);
        BorderPane.setAlignment(bottomButtons, Pos.TOP_CENTER);
        
        userName.getChildren().addAll(userLabel, userNameField);
        passBox.getChildren().addAll(passLabel, passField);
        loginBox.getChildren().add(loginButton);
        vbox.getChildren().addAll(userName, passBox, loginBox);
        bottomButtons.getChildren().addAll(forgotButton, createAcc);
        
        //set root's top to title
        root.setTop(title);
        //add button to the scene
        root.setCenter(vbox);
        //set bottom to the buttons
        root.setBottom(bottomButtons);
    	
    	Scene scene = new Scene(root, 500, 400);
    	
    	
        primaryStage.setTitle("Agile Hospital");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void switchToCreateAccount(Stage stage) {
    	CreateAccountView cav = new CreateAccountView();
    	Pane cavPane = cav.init();
    	Scene createAccScene = new Scene(cavPane, 1000, 700);
    	stage.setScene(createAccScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
