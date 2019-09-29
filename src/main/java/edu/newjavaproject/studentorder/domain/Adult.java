package edu.newjavaproject.studentorder.domain;

import java.time.LocalDate;

/***
 * Describe adult from student order. Class extends Person class
 */
public class Adult extends Person {
    private String passportSeria;           //серия паспорта
    private String passportNumber;          //номер паспорта
    private LocalDate issueDate;            //дата выдачи
    private String issueDepartment;         //кто выдал паспорт
    private String university;              //университет, в котором учится студент

    @Override
    public String getPersonString(){
        return surName + " " + givenName + " " + passportNumber;
    }


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    private String studentId;               //номер студенческого билета

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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
