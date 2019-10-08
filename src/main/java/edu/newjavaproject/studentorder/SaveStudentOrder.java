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
        List<Street> d = new DictionaryDaoImpl().findStreets("про");
        for (Street s : d){
            System.out.println(s.getStreetName());
        }
        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
        for (PassportOffice p : po) {
            System.out.println(p.getOfficeName());
        }

        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        for (RegisterOffice r : ro) {
            System.out.println(r.getOfficeName());
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

        RegisterOffice ro = new RegisterOffice(1, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(1, "First street");

        Address address = new Address("195000", street, "12", "", "142");

        //Husband
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        PassportOffice po1 = new PassportOffice(1, "", "");
        husband.setIssueDepartment(po1);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);

        //Wife
        Adult wife = new Adult("Петрова", "Вероника", "Алексеевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        PassportOffice po2 = new PassportOffice(1, "", "");
        wife.setIssueDepartment(po2);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);

        //Child 1
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 4, 5));
        RegisterOffice ro2 = new RegisterOffice(2, "", "");
        child1.setIssueDepartment(ro2);
        child1.setAddress(address);

        //Child 2
        Child child2 = new Child("Петрова", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 4, 5));
        RegisterOffice ro3 = new RegisterOffice(3, "", "");
        child2.setIssueDepartment(ro3);
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
