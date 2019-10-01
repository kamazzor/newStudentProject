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
    /***
     * Create default examples of important validators. They are needed cause
     * StudentOrderValidator meaningless without that validators.
     */
    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private  MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    /***
     * Check all student orders if they fit requirements to get student pay
     */
    public void checkAll(){
        StudentOrder[] soArray = readStudentOrders();

        for(StudentOrder so : soArray) {
            System.out.println();
            checkOneOrder(so);
        }
    }

    public StudentOrder[] readStudentOrders() {
        StudentOrder[] soArray = new StudentOrder[3];       //array of student orders
        for(int i = 0; i < soArray.length; i++) {
            soArray[i] = SaveStudentOrder.buildStudentOrder(i);
        }
        return soArray;
    }

    /**
     * Check one order if it fits requirements to get student pay
     * @param so
     */
    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);

        sendMail(so);

    }

    /***
     * Check if Students from student order have city register in SPb
     */
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterVal.checkCityRegister(so);
    }

    /***
     * Check if students from student order are married
     */
    public AnswerWedding checkWedding(StudentOrder so){
        return weddingVal.checkWedding(so);
    }

    /***
     * Check if student from student order have childrens
     */
    public AnswerChildren checkChildren(StudentOrder so){
        return childrenVal.checkChildren(so);
    }

    /***
     * Check if students from student order are real students. Method check it using
     * city student list on special website.
     */
    public AnswerStudent checkStudent(StudentOrder so){
        return studentVal.checkStudent(so);
    }

    /***
     *
     * @param so
     */
    public void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }


}
