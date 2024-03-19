package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    	//define the root
    	BorderPane root = new BorderPane();
    	
    	//title def
    	Label title = new Label("Agile Hospital");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        
        
        //create acc instance
        Button createAcc = new Button("Creater Account");
        createAcc.setOnAction(e -> switchToCreateAccount(primaryStage));
        
        
        //set root's top to title
        root.setTop(title);
        root.setPadding(new Insets(50));
        BorderPane.setAlignment(title, Pos.CENTER);
        
        //add button to the scene
        root.setCenter(createAcc);
    	
    	Scene scene = new Scene(root, 800, 500);
    	
    	
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
