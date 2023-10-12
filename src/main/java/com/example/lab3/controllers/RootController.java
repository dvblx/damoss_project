package com.example.lab3.controllers;

import com.example.lab3.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;

import java.io.File;

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
    public void unselectedDatatypeMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Section unselected");
        alert.setHeaderText("Please select the section");
        alert.showAndWait();
    }
    @FXML
    private void handleNew() {
        String dataType = sectionChoiceBox.getValue();
        if (dataType == null){unselectedDatatypeMessage();}
        else{
            mainApp.getAuthorData().clear();
            mainApp.setFilePath(null);
        }

    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        String dataType = sectionChoiceBox.getValue();
        if (dataType == null){unselectedDatatypeMessage(); return;}
        if (file != null) {mainApp.loadDataFromFile(file, dataType);}
    }

    @FXML
    private void handleSave() {
        File personFile = mainApp.getFilePath();
        String dataType = sectionChoiceBox.getValue();
        if (dataType == null){unselectedDatatypeMessage(); return;}

        if (personFile != null) {mainApp.saveDataToFile(personFile, dataType);}

        else {handleSaveAs();}
    }

    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать файл, куда будут сохранены данные
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        String dataType = sectionChoiceBox.getValue();
        if (dataType == null){unselectedDatatypeMessage(); return;}

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveDataToFile(file, dataType);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
