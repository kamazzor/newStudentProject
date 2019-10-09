package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.config.Config;
import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.StudentOrder;
import edu.newjavaproject.studentorder.domain.StudentOrderStatus;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

/***
 * That class implements methods and fields of {@link StudentOrderDao} interface.
 * Class help to save student order data from web-form to the database.
 */
public class StudentOrderDaoImpl implements StudentOrderDao{
    public static final String INSERT_ORDER =
            "INSERT INTO jc_student_order(" +
                    "student_order_status, student_order_date, h_sur_name, " +
                    "h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, " +
                    "h_passport_number, h_passport_date, h_passport_office_id, h_post_index, " +
                    "h_street_code, h_building, h_extension, h_apartment, " +
                    "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, " +
                    "w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, " +
                    "w_post_index, w_street_code, w_building, w_extension, " +
                    "w_apartment, certificate_id, register_office_id, marriage_date) " +
            "VALUES (?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?);";


    // TODO: 10/9/2019 refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    /***
     *  That method save student order data from web-form to the database.
     * @param so new student order
     * @return number of inserted recordings into jc_student_order table
     * @throws DaoException
     */
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1L;          //ID of last added student order in out database

        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"} )) {
            
            //Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            //Husband and wife
            setParamsForAdult(stmt, 3, so.getHusband());
            setParamsForAdult(stmt, 16, so.getWife());

            //Marriage
            stmt.setString(29, so.getMarriageCertificateId());
            stmt.setLong(30, so.getMarriageOffice().getOfficeId());
            stmt.setDate(31, Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet gkRs = stmt.getGeneratedKeys();
            if (gkRs.next()){
                result = gkRs.getLong(1);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /***
     * Insert parameters into SQL query to save student order into our database
     * @param stmt  SQL query template INSERT_ORDER
     * @param start start parameterIndex of stmt
     * @param adult Adult for whom stmt params are configured
     * @throws SQLException
     */
    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        stmt.setString(start, adult.getSurName());
        stmt.setString(start + 1, adult.getGivenName());
        stmt.setString(start + 2, adult.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        stmt.setString(start + 8, adult.getAddress().getPostcode());
        stmt.setLong(start + 9, adult.getAddress().getStreet().getStreetCode());
        stmt.setString(start + 10, adult.getAddress().getBuilding());
        stmt.setString(start + 11, adult.getAddress().getExtension());
        stmt.setString(start + 12, adult.getAddress().getApartment());
    }
}
