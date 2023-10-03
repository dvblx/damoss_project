package com.example.lab3.controllers;

import com.example.lab3.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class RootController {
    @FXML
    public ChoiceBox<String> sectionChoiceBox;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        sectionChoiceBox.setOnAction(event -> mainApp.showOverview(sectionChoiceBox.getValue()));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
