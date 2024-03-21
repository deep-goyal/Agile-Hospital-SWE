package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccountView {
    protected int userType;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int gender;
    private String password;
    private int securityQuestion;
    private String securityAnswer;

    public Pane init() {

        Label title = new Label("Create Account");
        title.setLayoutX(450);
        title.setLayoutY(50);

        ToggleGroup User_group = new ToggleGroup();
        RadioButton button_patient = createRadioButton("Patient", User_group);
        RadioButton button_doctor = createRadioButton("Doctor", User_group);
        RadioButton button_nurse = createRadioButton("Nurse", User_group);

        HBox hb_UserGroup = createHBox(275, 150, 70);
        Label label_ug = new Label("User Type");
        hb_UserGroup.getChildren().addAll(label_ug, button_patient, button_doctor, button_nurse);

        button_patient.setOnAction(event -> {
            if (button_patient.isSelected()) {
                userType = 1;
            }
        });
        button_doctor.setOnAction(event -> {
            if (button_doctor.isSelected()) {
                userType = 2;
            }
        });
        button_nurse.setOnAction(event -> {
            if (button_nurse.isSelected()) {
                userType = 3;
            }
        });

        TextField textField_firstName = createTextField(300, 300);
        HBox hb_FirstName = createTextBox("First Name",textField_firstName, 275, 200, 70);

        TextField textField_lastName = createTextField(300, 300);
        HBox hb_LastName = createTextBox("Last Name", textField_lastName,275, 250, 70);

        ObservableList<LocalDate> dateOptions = FXCollections.observableArrayList();

        LocalDate currentDate = LocalDate.now();
        for (int i = 0; i <= 3650; i++) { // Loop for 10 years (3650 days)
            LocalDate date = currentDate.minusDays(i);
            dateOptions.add(date);
        }

        ChoiceBox<LocalDate> dateChoiceBox = new ChoiceBox<>(dateOptions);

        HBox hb_DOB = createHBox(275, 300, 60);
        Label label_dob = new Label("Date of Birth");
        hb_DOB.getChildren().addAll(label_dob, dateChoiceBox);

        dateChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected option: " + newValue);
            dateOfBirth = newValue;
        });

        ToggleGroup Gender_group = new ToggleGroup();
        RadioButton button_Male = createRadioButton("Male", Gender_group);
        RadioButton button_Female = createRadioButton("Female", Gender_group);
        HBox hb_GenderGroup = createHBox(275, 350, 80);
        Label label_gg = new Label("Gender");
        hb_GenderGroup.getChildren().addAll(label_gg, button_Male, button_Female);

        button_Male.setOnAction(event -> {
            if (button_Male.isSelected()) {
                gender = 1;
            }
        });
        button_Female.setOnAction(event -> {
            if (button_Female.isSelected()) {
                gender = 2;
            }
        });

        TextField textField_pass = createTextField(300, 300);
        HBox hb_Password = createTextBox("Password", textField_pass,275, 400, 70);

        String sq[] = {
                "What was the name of your first pet?",
                "What was the name of your first school?",
                "What year did you enter college?",
                "What is your mother's maiden name?",
                "What is the name of the manufacturer of your first car?",
                "What is your favorite sport?"
        };

		ChoiceBox cb_security_question = new ChoiceBox(FXCollections.observableArrayList(sq));

        cb_security_question.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected option: " + newValue);

            if (newValue == "What was the name of your first pet?"){
                securityQuestion = 1;
            } else if (newValue == "What was the name of your first school?"){
                securityQuestion = 2;
            } else if (newValue == "What year did you enter college?"){
                securityQuestion = 3;
            } else if (newValue == "What is the name of the manufacturer of your first car?"){
                securityQuestion = 4;
            } else if (newValue == "What is your favorite sport?"){
                securityQuestion = 5;
            }
        });

        HBox hb_Security_Question = createHBox(275, 450, 30);
        Label label_sq = new Label("Security Question");
        hb_Security_Question.getChildren().addAll(label_sq, cb_security_question);

        TextField textField_SA = createTextField(300, 300);
        HBox hb_Security_Answer = createTextBox("Security Answer",textField_SA, 275, 500, 35);


        Button Back = new Button();
        Back.setText("Back");

        Back.setLayoutX(275);
        Back.setLayoutY(575);
        Back.setPrefSize(150,40);

        Button Submit = new Button();
        Submit.setText("Sign-Up");

        Submit.setLayoutX(725);
        Submit.setLayoutY(575);
        Submit.setPrefSize(150,40);



        Text messageText = new Text();
        messageText.setLayoutX(275);
        messageText.setLayoutY(560);

        Submit.setOnAction((ActionEvent event) -> {
            System.out.println("Button clicked!");
            SignUp signupInterface = new SignUp();

            if (signupInterface.validateUserInput(userType, firstName, dateOfBirth, gender,
                    password, securityQuestion, securityAnswer)){
                System.out.println("This works");

                try {
                    String new_username = signupInterface.registerUser(userType, firstName, lastName, dateOfBirth, gender,
                            password, securityQuestion, securityAnswer);

                    if (new_username == "-1") {
                        messageText.setText("User name could not be generated... Try Again");
                    } else {
                        messageText.setText("Your username is : " + new_username);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //clear all textbox, buttons, and choicebox
                User_group.selectToggle(null);
                Gender_group.selectToggle(null);
                cb_security_question.setValue(null);
                dateChoiceBox.setValue(null);
                textField_firstName.clear();
                textField_lastName.clear();
                textField_pass.clear();
                textField_SA.clear();
                //write error message to screen
                messageText.setText("Input was invalid... Try Again");
            }
        });

//

        Pane root = new Pane();
        root.getChildren().add(title);
        root.getChildren().addAll(hb_UserGroup, hb_GenderGroup);
        root.getChildren().addAll(hb_DOB, hb_Security_Question);
        root.getChildren().addAll(hb_FirstName, hb_LastName, hb_Password, hb_Security_Answer);
        root.getChildren().addAll(Submit, Back, messageText);

        return root;
    }

    public static TextField createTextField(int X, int Y){
        TextField textField = new TextField ();
        textField.setPrefWidth(X);
        textField.setMaxWidth(Y);
        return textField;
    }

    public HBox createTextBox(String label, TextField textField, int X, int Y, int spacing){
        Label label_name = new Label(label);
        HBox hb = new HBox();
        hb.getChildren().addAll(label_name, textField);
        hb.setSpacing(spacing);
        hb.setLayoutX(X);
        hb.setLayoutY(Y);

        if (label == "First Name"){
            textField.setOnAction(event -> {
                System.out.println("Selected option: " + textField.getText());
                firstName = textField.getText();
            });
        } else if (label == "Last Name"){
            textField.setOnAction(event -> {
                System.out.println("Selected option: " + textField.getText());
                lastName = textField.getText();
            });
        } else if (label == "Password"){
            textField.setOnAction(event -> {
                System.out.println("Selected option: " + textField.getText());
                password = textField.getText();
            });
        } else if (label == "Security Answer"){
            textField.setOnAction(event -> {
                System.out.println("Selected option: " + textField.getText());
                securityAnswer = textField.getText();
            });
        }

        return hb;
    }

    public static RadioButton createRadioButton(String label, ToggleGroup group){
        RadioButton Rb = new RadioButton(label);
        Rb.setToggleGroup(group);
        return Rb;
    }

    public static HBox createHBox(int X, int Y, int spacing){
        HBox hb = new HBox();
        hb.setSpacing(spacing);
        hb.setLayoutX(X);
        hb.setLayoutY(Y);
        return hb;
    }


}