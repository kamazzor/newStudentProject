package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;

/***
 * Check if someone person from student order are registered in Spb +
 * what type of registration: permanent or temporary.
 * @return all info above into CityRegisterValidator class
 */

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person){
        return null;
    }
}
