package edu.javaproject.studentorder.validator;

import edu.javaproject.studentorder.domain.StudentOrder;
import edu.javaproject.studentorder.domain.student.AnswerStudent;

/***
 * Check if people from student order are real students
 */
public class StudentValidator {
    public AnswerStudent checkStudent(StudentOrder so){
        System.out.println("IsStudent is running");
        return new AnswerStudent();
    }
}
