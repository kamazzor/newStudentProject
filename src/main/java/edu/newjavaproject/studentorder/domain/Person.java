package edu.newjavaproject.studentorder.domain;

import java.time.LocalDate;
/***
 * Describe person from student order.
 */
public class Person {
    private String surName;             //фамилия
    private String givenName;           //имя
    private String patronymic;          //отчество
    private LocalDate dateOfBirth;      //дата рождения

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
