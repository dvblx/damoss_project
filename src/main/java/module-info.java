module com.example.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.prefs;

    requires com.dlsc.formsfx;

    opens com.example.lab3 to javafx.fxml, java.xml.bind, com.sun.xml.bind;
    opens com.example.lab3.models to java.xml.bind, com.sun.xml.bind;
    opens com.example.lab3.util;
    exports com.example.lab3;
    exports com.example.lab3.controllers;
    opens com.example.lab3.controllers to javafx.fxml;
}