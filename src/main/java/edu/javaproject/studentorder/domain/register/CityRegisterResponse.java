package edu.javaproject.studentorder.domain.register;

/***
 * Domain class contains check status from GRN if someone person from student order
 * are registered in Spb + what type of registration: permanent or temporary.
 * Return all info above into CityRegisterValidator class
 */
public class CityRegisterResponse {
    private boolean registered;                           //есть регистрация или нет
    private boolean temporal;                           //тип регистрации


    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "existing=" + registered +
                ", temporal=" + temporal +
                '}';
    }
}
