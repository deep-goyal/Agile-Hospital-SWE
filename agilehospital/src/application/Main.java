package application;
	
import application.serializedBackend.LogIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	String userNameText = "";
	String passText = "";
	boolean success;

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
        userNameField.setPromptText("Required");
        
        //password hbox
        HBox passBox = new HBox();
        Text passLabel = new Text("Password: ");
        TextField passField = new TextField();
        passField.setPromptText("Required");
        
        //login button
        HBox loginBox = new HBox();
        Button loginButton = new Button("Login");
        
        //success message
        Text successMessage = new Text("");
        
        //bottom pane
        
        //create acc instance
        Button createAcc = new Button("Create Account");
        createAcc.setOnAction(e -> CreateAccountView.display(primaryStage));

        
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
        
      //capture texts after button pressed (EVENT HANDLER)
        EventHandler<ActionEvent> loginHandler = new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		if (e.getSource() instanceof Button) {
        			Button srcbtn = (Button) e.getSource();
        			
        			if (srcbtn.getText().equals("Login")) {
		        		userNameText = userNameField.getText();
		        		passText = passField.getText();
		        		success = LogIn.validateLoginInfo(userNameText, passText);
		        		
		        		if (success) {
		                	//successMessage.setText("Success, you are logged in");

                            //----------------TO-DO--------------------------
                            //get the type of user and redirect them to their respective view
                            try {
                                switch (userNameText.charAt(0)) {
                                    case 'p' :
                                        switchToPatientView(userNameText);
                                        break;
                                    case 'n' :
                                        switchToNurseView(userNameText);
                                        break;
                                    case 'd':
                                        switchToDoctorView(userNameText);
                                        break;
                                    default:
                                        System.out.println("Error. Login Redirection failed.");
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

		        			char role = userNameText.charAt(0);
		        			
		        			if (role == 'n') {
		        				NurseDashboard.display(primaryStage);//add
		        				
		        			} else if (role == 'p') {
		        				System.out.println("Enter patient view");
		        				
		        			} else if (role ==  'd') {
		        				System.out.println("Enter doctor view");
		        			}
		        			
		        			successMessage.setText("Success, you are logged in");
		                } else {
		                	successMessage.setText("Login failed! Retry!!");
		                }
		        		
		        		vbox.getChildren().add(successMessage);
        			}
        		}
        	}
        };
        
        loginButton.setOnAction(loginHandler);
        
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

    //completed
    private void switchToCreateAccount(Stage stage) {
    	CreateAccountView cav = new CreateAccountView();
    	Pane cavPane = cav.init();
    	Scene createAccScene = new Scene(cavPane, 1000, 700);
    	stage.setScene(createAccScene);
    }

    //----------------TO-DO--------------------------
    private void switchToDoctorView(String userName) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorView.fxml"));
//        Parent root = loader.load();
//        DoctorController controller = loader.getController();
//        controller.setUserName(userName);
//        primaryStage.setScene(new Scene(root));
    }

    //----------------TO-DO--------------------------
    private void switchToNurseView(String userName) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("NurseView.fxml"));
//        Parent root = loader.load();
//        NurseController controller = loader.getController();
//        controller.setUserName(userName);
//        primaryStage.setScene(new Scene(root));
    }

    //----------------TO-DO--------------------------
    private void switchToPatientView(String userName) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));
//        Parent root = loader.load();
//        PatientController controller = loader.getController();
//        controller.setUserName(userName);
//        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

