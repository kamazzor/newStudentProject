package edu.newjavaproject.studentorder.exception;

/***
 * Class are city register exception
 */
public class CityRegisterException extends Exception {
    public CityRegisterException() {
    }

    public CityRegisterException(String message) {
        super(message);
    }

    public CityRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
