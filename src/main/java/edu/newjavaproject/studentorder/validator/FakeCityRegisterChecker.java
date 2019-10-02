package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;

/***
 * Not real checker (stub) if someone people from student order are registered in SPb.
 */
public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person){

        return null;
    }
}
