package edu.newjavaproject.studentorder;

import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Main class where project start
 */
public class SaveStudentOrder {
    public static void main(String[] args) {
//        StudentOrder so = new StudentOrder();
//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);

        Adult husband = new Adult("Васильев", "Андрей", "Петрович", null);

        return so;
    }
}
