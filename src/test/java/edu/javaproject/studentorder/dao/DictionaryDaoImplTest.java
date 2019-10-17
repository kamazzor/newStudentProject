package edu.javaproject.studentorder.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
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
    public void testExample1(){
        System.out.println("TEST 1");
    }

    @Test
//    @Ignore
    public void testExample2(){
        System.out.println("TEST 2");
    }

    @Test
    public void testExample3(){
        System.out.println("TEST 3");
//        throw new RuntimeException("BAAAAD RESULT");
    }

}