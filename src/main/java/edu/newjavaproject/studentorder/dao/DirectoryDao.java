package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.domain.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/***
 * Class (data access object - DAO) that get access to data in our directories
 * (tables: jc_student_order, jc_street, jc_register_office,
 * jc_passport_office, jc_country_struct, jc_student_child
 */
public class DirectoryDao {
    //Соединяемся с таблицей jc_student
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jc_student",
                "postgres", "postgres");
        return con;
    }

    //Ищем в таблице jc_street улицы по введенному в заявочную веб-форму образцу
    public List<Street> findStreets(String pattern) throws Exception{
        //Найденные после запроса с паттерном улицы.
        List<Street> result = new LinkedList<>();

        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String sql = "select street_code, street_name from jc_street\n" +
                "where UPPER(street_name) like UPPER('%" + pattern + "%')";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
            result.add(str);
        }
        // TODO: 10/7/2019 return list of streets
    }
}
