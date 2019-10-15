package edu.javaproject.studentorder.validator;

import edu.javaproject.studentorder.domain.StudentOrder;
import edu.javaproject.studentorder.domain.wedding.AnswerWedding;

/***
 * Check if people from student order are married.
 */
public class WeddingValidator {
    public AnswerWedding checkWedding(StudentOrder so){
        System.out.println("Wedding is running");
        return new AnswerWedding();
    }
}
