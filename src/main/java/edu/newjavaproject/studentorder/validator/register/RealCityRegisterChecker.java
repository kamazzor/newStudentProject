package edu.newjavaproject.studentorder.validator.register;

import edu.newjavaproject.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.exception.CityRegisterException;

/***
 * Check if someone person from student order are registered in Spb +
 * what type of registration: permanent or temporary.
 * @return all info above into CityRegisterValidator class
 */

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        return null;
    }
}
