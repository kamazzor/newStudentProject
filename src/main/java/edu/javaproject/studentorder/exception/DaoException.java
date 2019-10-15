package edu.javaproject.studentorder.exception;

/***
 * Class are data access object (DAO) exception caused by some problems
 * when working with database
 */
public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
