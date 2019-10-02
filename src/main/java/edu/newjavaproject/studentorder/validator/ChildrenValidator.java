package edu.newjavaproject.studentorder.validator;

import edu.newjavaproject.studentorder.domain.children.AnswerChildren;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Check if people from student order have enough childrens
 */
public class ChildrenValidator {
    public AnswerChildren checkChildren(StudentOrder so){
        System.out.println("Children is running");
        return new AnswerChildren();
    }
}
