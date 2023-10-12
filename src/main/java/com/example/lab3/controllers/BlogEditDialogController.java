package com.example.lab3.controllers;

import com.example.lab3.models.Blog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class BlogEditDialogController {
    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentTextArea;

    private Stage dialogStage;
    private Blog blog;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;

        titleField.setText(blog.getTitle());
        contentTextArea.setText(blog.getContent());
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            blog.setTitle(titleField.getText());
            blog.setContent(contentTextArea.getText());
            blog.setLastUpdateDate(LocalDate.now());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid title!\n";
        }
        if (contentTextArea.getText() == null || contentTextArea.getText().length() == 0) {
            errorMessage += "No valid content!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
