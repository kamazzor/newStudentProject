package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.AnswerStudent;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Check if people from student order are real students
 */
public class StudentValidator {
    public AnswerStudent checkStudent(StudentOrder so){
        System.out.println("IsStudent is running");
        return new AnswerStudent();
    }
}
