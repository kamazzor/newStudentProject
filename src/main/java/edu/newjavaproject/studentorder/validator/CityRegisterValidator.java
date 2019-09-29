package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.AnswerCityRegister;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Check if Students from student order have city register in SPb
 */
public class CityRegisterValidator {

    public String hostName;
    protected int port;
    private String login;
    public String password;

    public AnswerCityRegister checkCityRegister(StudentOrder so){
        System.out.println("CityRegister is running: " + hostName);
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.success = false;
        return ans;
    }
}
