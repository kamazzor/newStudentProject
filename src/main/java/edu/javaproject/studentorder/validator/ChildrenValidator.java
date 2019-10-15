package edu.javaproject.studentorder.validator;

import edu.javaproject.studentorder.domain.StudentOrder;
import edu.javaproject.studentorder.domain.children.AnswerChildren;

/***
 * Check if people from student order have enough childrens
 */
public class ChildrenValidator {
    public AnswerChildren checkChildren(StudentOrder so){
        System.out.println("Children is running");
        return new AnswerChildren();
    }
}
