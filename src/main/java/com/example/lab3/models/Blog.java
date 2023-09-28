package com.example.lab3.models;

import java.time.LocalDate;

public class Blog {
    private String title;
    private String content;
    private LocalDate creationDate;
    private LocalDate lastUpdateDate;

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDate getCreationDate() {return creationDate;}

    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    public LocalDate getLastUpdateDate() {return lastUpdateDate;}

    public void setLastUpdateDate(LocalDate lastUpdateDate) {this.lastUpdateDate = lastUpdateDate;}
}
