package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.config.Config;
import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.StudentOrder;
import edu.newjavaproject.studentorder.domain.StudentOrderStatus;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

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
    
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        Long i;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER)) {
            
            //Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            
            //Husband
            Adult husband = so.getHusband();
            stmt.setString(3, husband.getSurName());
            stmt.setString(4, husband.getGivenName());
            stmt.setString(5, husband.getPatronymic());
            stmt.setDate(6, Date.valueOf(husband.getDateOfBirth()));
            stmt.setString(7, husband.getPassportSeria());
            stmt.setString(8, husband.getPassportNumber());
            stmt.setDate(9, Date.valueOf(husband.getIssueDate()));
            stmt.setLong(10, husband.getIssueDepartment().getOfficeId());
            stmt.setString(11, husband.getAddress().getPostcode());
            stmt.setLong(12, husband.getAddress().getStreet().getStreetCode());
            stmt.setString(13, husband.getAddress().getBuilding());
            stmt.setString(14, husband.getAddress().getExtension());
            stmt.setString(15, husband.getAddress().getApartment());

            //Wife
            Adult wife = so.getWife();
            stmt.setString(16, wife.getSurName());
            stmt.setString(17, wife.getGivenName());
            stmt.setString(18, wife.getPatronymic());
            stmt.setDate(19, Date.valueOf(wife.getDateOfBirth()));
            stmt.setString(20, wife.getPassportSeria());
            stmt.setString(21, wife.getPassportNumber());
            stmt.setDate(22, Date.valueOf(wife.getIssueDate()));
            stmt.setLong(23, wife.getIssueDepartment().getOfficeId());
            stmt.setString(24, wife.getAddress().getPostcode());
            stmt.setLong(25, wife.getAddress().getStreet().getStreetCode());
            stmt.setString(26, wife.getAddress().getBuilding());
            stmt.setString(27, wife.getAddress().getExtension());
            stmt.setString(28, wife.getAddress().getApartment());

            //Marriage
            stmt.setString(29, so.getMarriageCertificateId());
            stmt.setLong(30, so.getMarriageOffice().getOfficeId());
            stmt.setDate(31, Date.valueOf(so.getMarriageDate()));

            i = (long) stmt.executeUpdate();
            System.out.println(i);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        // TODO: 10/9/2019 return Long
        return i;
    }
}
