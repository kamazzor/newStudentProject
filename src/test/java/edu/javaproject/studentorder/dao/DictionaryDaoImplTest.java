package edu.javaproject.studentorder.dao;

import org.junit.Ignore;
import org.junit.Test;

public class DictionaryDaoImplTest {

    @Test
    public void testExample1(){
        System.out.println("TEST 1");
    }

    @Test
    @Ignore
    public void testExample2(){
        System.out.println("TEST 2");
    }

    @Test
    public void testExample3(){
        System.out.println("TEST 3");
        throw new RuntimeException("BAAAAD RESULT");
    }

}