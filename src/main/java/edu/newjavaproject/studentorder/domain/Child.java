package edu.newjavaproject.studentorder.domain;

import java.time.LocalDate;

public class Child extends Person {
    private String certificateNumber;           //свидетельство о рождении
    private LocalDate issueDate;                //дата выдачи свидетельства
    private String issueDepartment;             //кто выдал свидетельство

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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
