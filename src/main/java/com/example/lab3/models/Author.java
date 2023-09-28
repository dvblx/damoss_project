package com.example.lab3.models;

import java.time.LocalDate;

public class Author {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public LocalDate getRegistrationDate() {return registrationDate;}

    public void setRegistrationDate(LocalDate registrationDate) {this.registrationDate = registrationDate;}
}
