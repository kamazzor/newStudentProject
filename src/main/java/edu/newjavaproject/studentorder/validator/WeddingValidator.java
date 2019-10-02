package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.wedding.AnswerWedding;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Check if people from student order are married.
 */
public class WeddingValidator {
    public AnswerWedding checkWedding(StudentOrder so){
        System.out.println("Wedding is running");
        return new AnswerWedding();
    }
}
