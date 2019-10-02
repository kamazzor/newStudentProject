package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;

/***
 * Describe requirements to one person registration checker in GRN
 */
public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson(Person person);
}
