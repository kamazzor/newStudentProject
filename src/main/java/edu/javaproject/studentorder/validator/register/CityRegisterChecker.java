package edu.javaproject.studentorder.validator.register;

import edu.javaproject.studentorder.domain.Person;
import edu.javaproject.studentorder.domain.register.CityRegisterResponse;
import edu.javaproject.studentorder.exception.CityRegisterException;
import edu.javaproject.studentorder.exception.TransportException;

/***
 * Describe requirements to one person registration checker in GRN
 */
public interface CityRegisterChecker {
    CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException;
}
