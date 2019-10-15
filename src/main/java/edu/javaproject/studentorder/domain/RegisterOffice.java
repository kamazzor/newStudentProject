package edu.javaproject.studentorder.domain;
/***
 * Domain class describe register office (ZAGS) where
 * {@link Adult}'s were married and/or {@link Child} was registered
 */
public class RegisterOffice {
    private long officeId;
    private String officeAreaId;
    private String officeName;

    public RegisterOffice() {
    }

    public RegisterOffice(long officeId, String officeAreaId, String officeName) {
        this.officeId = officeId;
        this.officeAreaId = officeAreaId;
        this.officeName = officeName;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
