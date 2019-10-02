package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.Child;
import edu.newjavaproject.studentorder.domain.register.AnswerCityRegister;
import edu.newjavaproject.studentorder.domain.register.CityRegisterCheckerResponse;
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

    public AnswerCityRegister checkCityRegister(StudentOrder so) throws CityRegisterException {
        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband());
            CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife());

            List<Child> children = so.getChildren();

            //1st way to run through all List using standart for
            for(int i = 0; i < so.getChildren().size(); i++) {
                CityRegisterCheckerResponse cans = personChecker.checkPerson(children.get(i));
            }

            //2nd way to run through all List using Iterator
            for (Iterator<Child> it = children.iterator(); it.hasNext(); ){
                Child child = it.next();
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
            }

            //3rd way to run through all List using foreach
            for (Child child : children) {
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
            }

        } catch (CityRegisterException ex){
            ex.printStackTrace();
        }

        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;
        return ans;
    }
}
