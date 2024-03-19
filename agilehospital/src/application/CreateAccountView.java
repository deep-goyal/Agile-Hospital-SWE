package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
	
    public Pane init() {
        Label title = new Label("Create Account");
        title.setLayoutX(450);
        title.setLayoutY(50);
        /*
        Image image = new Image(getClass().getResourceAsStream(""));
        title.setGraphic(new ImageView(image));
        */

        ToggleGroup User_group = new ToggleGroup();
        RadioButton button_patient = createRadioButton("Patient", User_group);
        RadioButton button_doctor = createRadioButton("Doctor", User_group);
        RadioButton button_nurse = createRadioButton("Nurse", User_group);
        HBox hb_UserGroup = createHBox(275, 150, 70);
        Label label_ug = new Label("User Type");
        hb_UserGroup.getChildren().addAll(label_ug, button_patient, button_doctor, button_nurse);


        HBox hb_FirstName = createTextBox("First Name", 275, 200, 70);
        HBox hb_LastName = createTextBox("Last Name", 275, 250, 70);


        Integer year[] = {
                2010, 2011, 2012, 2013, 2014, 2015, 2016,
                2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024
        };
        ChoiceBox cb_year = new ChoiceBox(FXCollections.observableArrayList(year));

        String month[] = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        ChoiceBox cb_month = new ChoiceBox(FXCollections.observableArrayList(month));

        Integer day[] = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
        };
        ChoiceBox cb_day = new ChoiceBox(FXCollections.observableArrayList(day));

        HBox hb_DOB = createHBox(275, 300, 60);
        Label label_dob = new Label("Date of Birth");
        hb_DOB.getChildren().addAll(label_dob, cb_year, cb_month, cb_day);


        ToggleGroup Gender_group = new ToggleGroup();
        RadioButton button_Male = createRadioButton("Male", Gender_group);
        RadioButton button_Female = createRadioButton("Female", Gender_group);
        HBox hb_GenderGroup = createHBox(275, 350, 80);
        Label label_gg = new Label("Gender");
        hb_GenderGroup.getChildren().addAll(label_gg, button_Male, button_Female);


        HBox hb_Password = createTextBox("Password", 275, 400, 70);

        String sq[] = {
                "What was the name of your first pet?",
                "What was the name of your first school?",
                "What year did you enter college?",
                "What is your mother's maiden name?",
                "What is the name of the manufacturer of your first car?",
                "What is your favorite sport?"
        };
        ChoiceBox cb_security_question = new ChoiceBox(FXCollections.observableArrayList(sq));

        HBox hb_Security_Question = createHBox(275, 450, 30);
        Label label_sq = new Label("Security Question");
        hb_Security_Question.getChildren().addAll(label_sq, cb_security_question);

        HBox hb_Security_Answer = createTextBox("Security Answer", 275, 500, 35);


        Button Submit = new Button();
        Submit.setText("Sign-Up");

        /* Event handler code */

        Submit.setLayoutX(725);
        Submit.setLayoutY(555);
        Submit.setPrefSize(150,40);

        Pane root = new Pane();
        root.getChildren().add(title);
        root.getChildren().addAll(hb_UserGroup, hb_GenderGroup);
        root.getChildren().addAll(hb_DOB, hb_Security_Question);
        root.getChildren().addAll(hb_FirstName, hb_LastName, hb_Password, hb_Security_Answer);
        root.getChildren().addAll(Submit);

      
        
        return root;
        
        
    }

    public static HBox createTextBox(String label, int X, int Y, int spacing){
        Label label_name = new Label(label);
        TextField textField = new TextField ();
        textField.setPrefWidth(300);
        textField.setMaxWidth(300);
        HBox hb = new HBox();
        hb.getChildren().addAll(label_name, textField);
        hb.setSpacing(spacing);
        hb.setLayoutX(X);
        hb.setLayoutY(Y);
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