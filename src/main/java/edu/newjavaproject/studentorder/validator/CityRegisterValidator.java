package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.Child;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.domain.register.AnswerCityRegister;
import edu.newjavaproject.studentorder.domain.register.AnswerCityRegisterItem;
import edu.newjavaproject.studentorder.domain.register.CityRegisterResponse;
import edu.newjavaproject.studentorder.domain.StudentOrder;
import edu.newjavaproject.studentorder.exception.CityRegisterException;
import edu.newjavaproject.studentorder.exception.TransportException;
import edu.newjavaproject.studentorder.validator.register.CityRegisterChecker;
import edu.newjavaproject.studentorder.validator.register.FakeCityRegisterChecker;

import java.util.Iterator;
import java.util.List;

/***
 * Check if all people from student order (including children) have city register in SPb
 */
public class CityRegisterValidator {
    public static final String IN_CODE = "NO_GRN";          //internal code, т.е. мы не смогли добраться до ГРН

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

    /***
     * Fully check one person
     * @param person
     * @return AnswerCityRegisterItem
     */
    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;
        try {
            CityRegisterResponse tmp = personChecker.checkPerson(person);
            status = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex){
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
        } catch (TransportException ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }
        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, person, error);
        return ans;
    }

    }
