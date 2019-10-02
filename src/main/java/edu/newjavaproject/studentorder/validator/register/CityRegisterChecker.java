package edu.newjavaproject.studentorder.validator.register;

import edu.newjavaproject.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.exception.CityRegisterException;

/***
 * Describe requirements to one person registration checker in GRN
 */
public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException;
}
