package com.example.lab3.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Blog {
    private final StringProperty title;
    private final StringProperty content;
    private final ObjectProperty<LocalDate> creationDate;
    private final ObjectProperty<LocalDate> lastUpdateDate;

    public Blog(String title, String content) {
        this.title = new SimpleStringProperty(title) ;
        this.content = new SimpleStringProperty(content);
        this.creationDate = new SimpleObjectProperty<>(LocalDate.of(2023, 10, 3)); // LocalDate.now()
        this.lastUpdateDate = new SimpleObjectProperty<>(LocalDate.of(2023, 10, 3)); // LocalDate.now()
    }

    public String getTitle() {return title.get();}

    public void setTitle(String title) {this.title.set(title);}
    public StringProperty titleProperty(){return this.title;}
    public String getContent() {return content.get();}
    public void setContent(String content) {this.content.set(content);}
    public StringProperty contentProperty(){return this.content;}
    public LocalDate getCreationDate() {return creationDate.get();}
    public ObjectProperty<LocalDate> creationDateProperty(){return creationDate;}
    public LocalDate getLastUpdateDate() {return lastUpdateDate.get();}
    public void setLastUpdateDate(LocalDate lastUpdateDate) {this.lastUpdateDate.set(lastUpdateDate);}
    public ObjectProperty<LocalDate> lastUpdateDateProperty(){return lastUpdateDate;}
}
