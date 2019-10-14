package edu.newjavaproject.studentorder.domain;

import java.time.LocalDate;

/***
 * Domain class describe adult from student order.
 */
public class Adult extends Person {
    private String passportSeria;                   //серия паспорта
    private String passportNumber;                  //номер паспорта
    private LocalDate issueDate;                    //дата выдачи
    private PassportOffice issueDepartment;         //кто выдал паспорт
    private University university;                  //университет, в котором учится студент
    private String studentId;                       //номер студбилета

    public Adult(){
    }

    public Adult(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
