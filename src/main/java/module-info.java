module com.example.lab3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.lab3 to javafx.fxml;
    exports com.example.lab3;
    exports com.example.lab3.controllers;
    opens com.example.lab3.controllers to javafx.fxml;
}