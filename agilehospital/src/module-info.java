module agilehospital {
    exports application;
    exports application.serializedBackend;
    exports application.FXMLHandlers to javafx.fxml;

    opens application.FXMLHandlers to javafx.fxml;

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
}
