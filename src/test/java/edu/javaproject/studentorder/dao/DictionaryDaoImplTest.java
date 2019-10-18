package edu.javaproject.studentorder.dao;

import edu.javaproject.studentorder.domain.CountryArea;
import edu.javaproject.studentorder.domain.PassportOffice;
import edu.javaproject.studentorder.domain.RegisterOffice;
import edu.javaproject.studentorder.domain.Street;
import edu.javaproject.studentorder.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryDaoImplTest {

    @BeforeClass
    public static void startUp() throws Exception {
        //Get resources files that are attached to our project (root is in the target->classes folder)
        URL url1 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student_project.sql");
        URL url2 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student_data.sql");
        //Get list of all strings from resource files we got using url
        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        //Join each list of strings above in one string to make sql queries.
        String sql1 = str1.stream().collect(Collectors.joining());
        String sql2 = str2.stream().collect(Collectors.joining());
        try (Connection con = ConnectionBuilder.getConnection();
             Statement stmt = con.createStatement()){

            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        }
    }

//    @Before
//    public void startTest(){
//        System.out.println("START TEST");
//    }

    @Test
    public void testStreet() throws DaoException {
        List<Street> d = new DictionaryDaoImpl().findStreets("про");
        Assert.assertTrue(d.size() == 2);
    }

    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
        Assert.assertTrue(po.size() == 2);
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        Assert.assertTrue(ro.size() == 2);
    }

    @Test
    public void testArea() throws DaoException {
        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
        Assert.assertTrue(ca1.size() == 2);
        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
        Assert.assertTrue(ca2.size() == 2);
        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
        Assert.assertTrue(ca3.size() == 2);
        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
        Assert.assertTrue(ca4.size() == 2);
    }
}