package edu.javaproject.studentorder.exception;

/***
 * Class are city register exception caused by not good status of city register check of person in GRN.
 */
public class CityRegisterException extends Exception {
    private String code;

    public String getCode() {
        return code;
    }

    public CityRegisterException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CityRegisterException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
