package edu.newjavaproject.studentorder.domain.register;

/***
 * Domain class contains check status from GRN if someone person from student order
 * are registered in Spb + what type of registration: permanent or temporary.
 * Return all info above into CityRegisterValidator class
 */
public class CityRegisterResponse {
    private boolean existing;                           //есть регистрация или нет
    private Boolean temporal;                           //тип регистрации


    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public Boolean getTemporal() {
        return temporal;
    }

    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "existing=" + existing +
                ", temporal=" + temporal +
                '}';
    }
}
