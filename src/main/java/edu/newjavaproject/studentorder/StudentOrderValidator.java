package edu.newjavaproject.studentorder;
/**
 * @author Kozlov Mikhail
 */

import edu.newjavaproject.studentorder.domain.*;
import edu.newjavaproject.studentorder.mail.MailSender;
import edu.newjavaproject.studentorder.validator.ChildrenValidator;
import edu.newjavaproject.studentorder.validator.CityRegisterValidator;
import edu.newjavaproject.studentorder.validator.StudentValidator;
import edu.newjavaproject.studentorder.validator.WeddingValidator;

/**
 * Class where check if students from student order are valid to get student pay
 */
public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    /***
     * Check all requirements to get student pay
     */
    static void checkAll(){
        while (true) {
            StudentOrder so = readStudentOrder();
            if (so == null) {
                break;
            }
            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.success){
                //continue;
                break;
            }
            AnswerWedding wedAnswer = checkWedding(so);
            AnswerChildren childAnswer = checkChildren(so);
            AnswerStudent studentAnswer = checkStudent(so);

            sendMail(so);
        }
    }

    static StudentOrder readStudentOrder() {
        StudentOrder so = new StudentOrder();
        return so;
    }

    /***
     * Check if Students from student order have city register in SPb
     */
    static AnswerCityRegister checkCityRegister(StudentOrder so){
        CityRegisterValidator crv = new CityRegisterValidator();
        crv.hostName = "Host";
        crv.password = "Password";
        AnswerCityRegister ans = crv.checkCityRegister(so);
        return ans;
    }

    /***
     * Check if students from student order are married
     */
    static AnswerWedding checkWedding(StudentOrder so){
        WeddingValidator wd = new WeddingValidator();
        AnswerWedding answerWedding = wd.checkWedding(so);
        return answerWedding;
    }

    /***
     * Check if student from student order have childrens
     */
    static AnswerChildren checkChildren(StudentOrder so){
        ChildrenValidator cv = new ChildrenValidator();
        AnswerChildren answerChildren = cv.checkChildren(so);
        return answerChildren;
    }

    /***
     * Check if students from student order are real students. Method check it using
     * city student list on special website.
     */
    static AnswerStudent checkStudent(StudentOrder so){
//        edu.newjavaproject.studentorder.validator.StudentValidator studentValidator = new edu.newjavaproject.studentorder.validator.StudentValidator();
//        edu.newjavaproject.studentorder.domain.AnswerStudent answerStudent = studentValidator.checkStudent(so);
        return new StudentValidator().checkStudent(so);
    }

    /***
     *
     * @param so
     */
    static void sendMail(StudentOrder so) {
        new MailSender().sendMail(so);
    }


}
