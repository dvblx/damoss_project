package com.example.lab3.controllers;

import com.example.lab3.MainApp;
import com.example.lab3.models.Blog;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

public class BlogController {
    @FXML
    private TableView<Blog> blogTable;
    @FXML
    private TableColumn<Blog, String> titleColumn;
    @FXML
    private Label titleLabel;
    @FXML
    private ScrollPane contentPane;
    @FXML
    private Label lastUpdateDateLabel;
    @FXML
    private Label creationDateLabel;
    private MainApp mainApp;

    public BlogController() {}
    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        showBlogDetails(null);

        blogTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue)-> showBlogDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        blogTable.setItems(mainApp.getBlogData());
    }
    private void showBlogDetails(Blog blog){
        if (blog != null){
            titleLabel.setText(blog.getTitle());
            contentPane.setContent(new Label(blog.getContent()));
            creationDateLabel.setText(blog.getCreationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            lastUpdateDateLabel.setText(blog.getLastUpdateDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        else{
            titleLabel.setText("");
            contentPane.setContent(new Label(""));
            lastUpdateDateLabel.setText("");
            creationDateLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteBlog() {
        int selectedIndex = blogTable.getSelectionModel().getSelectedIndex();
        try{
            blogTable.getItems().remove(selectedIndex);
        }
        catch (IndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Blog Selected");
            alert.setContentText("Please select an blog in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewBlog() {
        Blog tempBlog = new Blog("", "");
        boolean okClicked =  mainApp.showEditDialog(tempBlog);
        if (okClicked) {
            mainApp.getBlogData().add(tempBlog);
        }
    }

    @FXML
    private void handleEditBlog() {
        Blog selectedBlog = blogTable.getSelectionModel().getSelectedItem();
        if (selectedBlog != null) {
            boolean okClicked = mainApp.showEditDialog(selectedBlog);
            if (okClicked) {
                showBlogDetails(selectedBlog);
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
