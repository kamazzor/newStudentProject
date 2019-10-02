package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.AnswerCityRegister;
import edu.newjavaproject.studentorder.domain.StudentOrder;

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

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        personChecker.checkPerson(so.getHusband());
        personChecker.checkPerson(so.getWife());
        personChecker.checkPerson(so.getChild());

        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;
        return ans;
    }
}
