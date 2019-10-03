package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.Child;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.domain.register.AnswerCityRegister;
import edu.newjavaproject.studentorder.domain.register.AnswerCityRegisterItem;
import edu.newjavaproject.studentorder.domain.register.CityRegisterResponse;
import edu.newjavaproject.studentorder.domain.StudentOrder;
import edu.newjavaproject.studentorder.exception.CityRegisterException;
import edu.newjavaproject.studentorder.validator.register.CityRegisterChecker;
import edu.newjavaproject.studentorder.validator.register.FakeCityRegisterChecker;

import java.util.Iterator;
import java.util.List;

/***
 * Check if all people from student order (including children) have city register in SPb
 */
public class CityRegisterValidator {

    public String hostName;
    protected int port;
    private String login;
    public String password;

    /**
     * Example of personChecker check person registration in GRN.
     */
    private CityRegisterChecker personChecker;

    public CityRegisterValidator(){
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();

        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));

        for (Child child : so.getChildren()) {
            ans.addItem(checkPerson(child));
        }
        return ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person){
        try {
            CityRegisterResponse cans = personChecker.checkPerson(person);
        } catch (CityRegisterException ex){
            ex.printStackTrace();
        }
        // TODO: 10/4/2019 исправь заглушку
        return null;
    }

    }
