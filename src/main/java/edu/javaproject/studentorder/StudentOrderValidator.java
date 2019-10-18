package edu.javaproject.studentorder;

import edu.javaproject.studentorder.dao.StudentOrderDaoImpl;
import edu.javaproject.studentorder.domain.StudentOrder;
import edu.javaproject.studentorder.domain.children.AnswerChildren;
import edu.javaproject.studentorder.domain.register.AnswerCityRegister;
import edu.javaproject.studentorder.domain.student.AnswerStudent;
import edu.javaproject.studentorder.domain.wedding.AnswerWedding;
import edu.javaproject.studentorder.exception.CityRegisterException;
import edu.javaproject.studentorder.exception.DaoException;
import edu.javaproject.studentorder.mail.MailSender;
import edu.javaproject.studentorder.validator.ChildrenValidator;
import edu.javaproject.studentorder.validator.CityRegisterValidator;
import edu.javaproject.studentorder.validator.StudentValidator;
import edu.javaproject.studentorder.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;

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
    private MailSender mailSender;

    public StudentOrderValidator(){
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) throws CityRegisterException {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    /***
     * Check all student orders if they fit requirements to get student pay
     */
    public void checkAll() throws CityRegisterException {
        try {
            List<StudentOrder> soList = readStudentOrders();

            for(StudentOrder so : soList) {
                checkOneOrder(so);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public List<StudentOrder> readStudentOrders() throws DaoException {
        return new StudentOrderDaoImpl().getStudentOrders();
    }

    /**
     * Check one order if it fits requirements to get student pay
     * @param so
     */
    public void checkOneOrder(StudentOrder so) throws CityRegisterException {
        AnswerCityRegister cityAnswer = checkCityRegister(so);
//        AnswerWedding wedAnswer = checkWedding(so);
//        AnswerChildren childAnswer = checkChildren(so);
//        AnswerStudent studentAnswer = checkStudent(so);
//
//        sendMail(so);

    }

    /***
     * Check if Students from student order have city register in SPb
     */
    public AnswerCityRegister checkCityRegister(StudentOrder so) throws CityRegisterException {
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
