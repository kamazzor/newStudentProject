package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.domain.Street;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.util.List;

/***
 * That interface describe methods and fields which help to get access
 * to data in our dictionaries (tables: jc_student_order, jc_street, jc_register_office,
 * jc_passport_office, jc_country_struct, jc_student_child)
 */
public interface DictionaryDao {
    //Ищем в таблице jc_street улицы по введенному в заявочную веб-форму образцу
    public List<Street> findStreets(String pattern) throws DaoException;
}
