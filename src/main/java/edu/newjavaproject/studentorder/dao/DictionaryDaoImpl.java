package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.config.Config;
import edu.newjavaproject.studentorder.domain.Street;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/***
 * That class is data access object (DAO) and implementation of
 * {@link DictionaryDao} interface.
 * That class get access to data in our dictionaries
 * (tables: jc_student_order, jc_street, jc_register_office,
 * jc_passport_office, jc_country_struct, jc_student_child)
 */
public class DictionaryDaoImpl implements DictionaryDao {
    //Параметризованный SQL-запрос на поиск улиц в таблице jc_street
    private static final String GET_STREET = "select street_code, street_name from jc_street\n" +
            "where UPPER(street_name) like UPPER(?)";


    //Соединяемся с таблицей jc_student
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    //Ищем в таблице jc_street улицы по введенному в заявочную веб-форму образцу
    public List<Street> findStreets(String pattern) throws DaoException {
        //Найденные после запроса с паттерном улицы.
        List<Street> result = new LinkedList<>();

        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
