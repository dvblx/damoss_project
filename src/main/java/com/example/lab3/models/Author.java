package com.example.lab3.models;

import com.example.lab3.util.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Author {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> registrationDate;

    public Author(){this(null, null, null);}
    public Author(String firstName, String lastName, String email) {
        this.firstName = new SimpleStringProperty(firstName) ;
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.registrationDate = new SimpleObjectProperty<>(LocalDate.now());
    }

    public String getFirstName() {return firstName.get();}
    public void setFirstName(String firstName) {this.firstName.set(firstName);}
    public StringProperty firstNameProperty(){return firstName;}
    public String getLastName() {return lastName.get();}
    public void setLastName(String lastName) {this.lastName.set(lastName);}
    public StringProperty lastNameProperty(){return  lastName;}
    public String getEmail() {return email.get();}
    public void setEmail(String email) {this.email.set(email);}
    public StringProperty emailProperty(){return email;}
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getRegistrationDate() {return registrationDate.get();}
    public void setRegistrationDate(LocalDate registrationDate) {this.registrationDate.set(registrationDate);}
    public ObjectProperty<LocalDate> registrationDateProperty(){return registrationDate;}
}
