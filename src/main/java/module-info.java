module com.example.hospitalui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospitalui to javafx.fxml;
    exports com.example.hospitalui;
}