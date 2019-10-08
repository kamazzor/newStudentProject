package edu.newjavaproject.studentorder.validator.register;

import edu.newjavaproject.studentorder.domain.register.CityRegisterResponse;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.exception.CityRegisterException;
import edu.newjavaproject.studentorder.exception.TransportException;

/***
 * Check if someone person from student order are registered in Spb +
 * what type of registration: permanent or temporary.
 * Return all info above into CityRegisterValidator class
 */

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        return null;
    }
}