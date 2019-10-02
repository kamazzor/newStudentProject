package edu.newjavaproject.studentorder;

import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Main class where project start
 */
public class SaveStudentOrder {
    public static void main(String[] args) {
        buildStudentOrder(10);
//      StudentOrder so = new StudentOrder();
//      long ans = saveStudentOrder(so);
//      System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        StudentOrder so1 = so;
        printStudentOrder(so);

        return so;
    }

    private static void printStudentOrder(StudentOrder stOr) {
        System.out.println(stOr.getStudentOrderId());
    }
}
