package edu.javaproject.studentorder;

import edu.javaproject.studentorder.dao.StudentOrderDao;
import edu.javaproject.studentorder.dao.StudentOrderDaoImpl;
import edu.javaproject.studentorder.domain.*;

import java.time.LocalDate;
import java.util.List;

/***
 * Main class where project start
 */
public class SaveStudentOrder {

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:");
        return answer;
    }

    private static void printStudentOrder(StudentOrder stOr) {
        System.out.println(stOr.getStudentOrderId());
    }
}
