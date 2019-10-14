package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.domain.StudentOrder;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.util.List;

/***
 * That interface describe methods and fields which help to save
 * student order data from web-form to the database.
 */
public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder so) throws DaoException;

    List<StudentOrder> getStudentOrders() throws DaoException;
}
