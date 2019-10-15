package edu.javaproject.studentorder.validator;

import edu.javaproject.studentorder.domain.Child;
import edu.javaproject.studentorder.domain.Person;
import edu.javaproject.studentorder.domain.StudentOrder;
import edu.javaproject.studentorder.validator.register.CityRegisterChecker;
import edu.javaproject.studentorder.validator.register.FakeCityRegisterChecker;
import edu.javaproject.studentorder.domain.register.AnswerCityRegister;
import edu.javaproject.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javaproject.studentorder.domain.register.CityRegisterResponse;
import edu.javaproject.studentorder.exception.CityRegisterException;
import edu.javaproject.studentorder.exception.TransportException;

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
        } catch (Exception ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }
        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, person, error);
        return ans;
    }

    }
