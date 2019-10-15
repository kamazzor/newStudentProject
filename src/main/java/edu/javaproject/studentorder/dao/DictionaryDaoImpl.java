package edu.javaproject.studentorder.dao;

import edu.javaproject.studentorder.config.Config;
import edu.javaproject.studentorder.domain.CountryArea;
import edu.javaproject.studentorder.domain.PassportOffice;
import edu.javaproject.studentorder.domain.RegisterOffice;
import edu.javaproject.studentorder.domain.Street;
import edu.javaproject.studentorder.exception.DaoException;

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
    private static final String GET_STREET = "select street_code, street_name from jc_street " +
            "where UPPER(street_name) like UPPER(?)";
    //Параметризованный SQL-запрос на поиск паспортных столов в таблице jc_passport_office
    private static final String GET_PASSPORT = "select * from jc_passport_office " +
            "where p_office_area_id = ?";
    //Параметризованный SQL-запрос на поиск ЗАГСов в таблице jc_register_office
    private static final String GET_REGISTER = "select * from jc_register_office " +
            "where r_office_area_id = ?";
    //Параметризованный SQL-запрос на поиск нужных подуровней
    // (край, область, район, поселение и т.п.) в таблице jc_country_struct
    private static final String GET_AREA = "select * from jc_country_struct " +
            "where area_id like ? and area_id <> ?";


    // TODO: 10/9/2019 refactoring - make one method
    //Соединяемся с таблицей jc_student
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    //Search for street in jc_street table by pattern typed in application web-form
    public List<Street> findStreets(String pattern) throws DaoException {
        //Найденные после запроса с паттерном улицы.
        List<Street> result = new LinkedList<>();

        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Street str = new Street(
                        rs.getLong("street_code"),
                        rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    /***
     * @param areaId OKATO code of area where necessary ZAGS is
     * @return
     * @throws DaoException
     */
    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        //Найденные после запроса с паттерном паспортные столы.
        List<PassportOffice> result = new LinkedList<>();

        //Устанавливаем соединение с БД и делаем запрос на паспортные столы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PassportOffice po = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name"));
                result.add(po);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;

    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        //Найденные после запроса с паттерном ЗАГСы.
        List<RegisterOffice> result = new LinkedList<>();

        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RegisterOffice ro = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                result.add(ro);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        //Найденные после запроса с паттерном ЗАГСы.
        List<CountryArea> result = new LinkedList<>();

        //Устанавливаем соединение с БД и делаем запрос на ЗАГСы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AREA)) {

            String param1 = buildParam(areaId);
            String param2 = areaId;

            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CountryArea ca = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name"));
                result.add(ca);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private String buildParam(String areaId) throws SQLException{
        if (areaId == null || areaId.trim().isEmpty()){
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")){
            return areaId.substring(0, 2) + "___0000000";
        } else if (areaId.endsWith("0000000")){
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")){
            return areaId.substring(0, 8) + "____";
        }
        throw new SQLException("Invalid parameter 'areaID': " + areaId);
    }
}
