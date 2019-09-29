package edu.newjavaproject.studentorder;

import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.StudentOrder;

/***
 * Main class where project start
 */
public class SaveStudentOrder {
    public static void main(String[] args) {
        buildStudentOrder();
//        StudentOrder so = new StudentOrder();
//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    static StudentOrder buildStudentOrder(){
        StudentOrder so = new StudentOrder();
        Adult husband = new Adult();
        husband.setGivenName("Андрей");
        husband.setSurName("Петров");
        so.setHusband(husband);

        String ans = husband.getPersonString();
        System.out.println(ans);

        return so;
    }
}
