package com.example.lab3.controllers;

import com.example.lab3.MainApp;
import com.example.lab3.models.Author;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.format.DateTimeFormatter;

public class AuthorController {
    @FXML
    private TableView<Author> authorTable;
    @FXML
    private TableColumn<Author, String> firstNameColumn;
    @FXML
    private TableColumn<Author, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label registrationDateLabel;
    private MainApp mainApp;

    public AuthorController() {}
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showAuthorDetails(null);

        authorTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue)-> showAuthorDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        authorTable.setItems(mainApp.getAuthorData());
    }
    private void showAuthorDetails(Author author){
        if (author != null){
            firstNameLabel.setText(author.getFirstName());
            lastNameLabel.setText(author.getLastName());
            emailLabel.setText(author.getEmail());
            registrationDateLabel.setText(author.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        else{
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailLabel.setText("");
            registrationDateLabel.setText("");
        }

    }

    @FXML
    private void handleDeleteAuthor() {
        int selectedIndex = authorTable.getSelectionModel().getSelectedIndex();
        try{
            authorTable.getItems().remove(selectedIndex);
        }
        catch (IndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Author Selected");
            alert.setContentText("Please select an author in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewAuthor() {
        Author tempAuthor = new Author("", "", "");
        boolean okClicked = mainApp.showEditDialog(tempAuthor);
        if (okClicked) {
            mainApp.getAuthorData().add(tempAuthor);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditAuthor() {
        Author selectedAuthor = authorTable.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            boolean okClicked = mainApp.showEditDialog(selectedAuthor);
            if (okClicked) {
                showAuthorDetails(selectedAuthor);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
