package edu.newjavaproject.studentorder;

import edu.newjavaproject.studentorder.dao.DictionaryDaoImpl;
import edu.newjavaproject.studentorder.domain.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/***
 * Main class where project start
 */
public class SaveStudentOrder {
    public static void main(String[] args) throws Exception{
        List<Street> d = new DictionaryDaoImpl().findStreets("ec");
        for (Street s : d){
            System.out.println(s.getStreetName());
        }
        //Unnecessary
//        Get connection with jc_student database
//        Class.forName("org.postgresql.Driver");
//      buildStudentOrder(10);
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
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate((LocalDate.of(2016, 7,14)));
        so.setMarriageOffice("Отдел ЗАГС");

        Street street = new Street(1, "First street");

        Address address = new Address("195000", street, "12", "", "142");

        //Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment("Отдел полиции №" + id);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        //Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment("Отдел полиции №" + id);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);

        //Ребенок 1
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 4, 5));
        child1.setIssueDepartment("Отдел ЗАГС №" + id);
        child1.setAddress(address);

        //Ребенок 2
        Child child2 = new Child("Петрова", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 4, 5));
        child2.setIssueDepartment("Отдел ЗАГС №" + id);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }

    private static void printStudentOrder(StudentOrder stOr) {
        System.out.println(stOr.getStudentOrderId());
    }
}
