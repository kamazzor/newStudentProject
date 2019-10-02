package edu.newjavaproject.studentorder.domain;

/***
 * Domain class contains check info if someone person from student order
 * are registered in Spb + what type of registration: permanent or temporary.
 * @return all info above into CityRegisterValidator class
 */
public class CityRegisterCheckerResponse {
    private boolean existing;                           //есть регистрация или нет
    private Boolean temporal = null;                    //тип регистрации

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
}
