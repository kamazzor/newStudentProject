package edu.newjavaproject.studentorder.validator.register;

import edu.newjavaproject.studentorder.domain.register.CityRegisterResponse;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.exception.CityRegisterException;
import edu.newjavaproject.studentorder.exception.TransportException;

/***
 * Describe requirements to one person registration checker in GRN
 */
public interface CityRegisterChecker {
    CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException;
}
