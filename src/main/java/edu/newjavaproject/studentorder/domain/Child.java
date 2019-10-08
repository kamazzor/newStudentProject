package edu.newjavaproject.studentorder.domain;

import java.time.LocalDate;

/***
 * Domain class describe child from student order
 */
public class Child extends Person {
    private String certificateNumber;           //свидетельство о рождении
    private LocalDate issueDate;                //дата выдачи свидетельства
    private RegisterOffice issueDepartment;             //кто выдал свидетельство

    public Child(String surName, String givenName, String patronymic, LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
