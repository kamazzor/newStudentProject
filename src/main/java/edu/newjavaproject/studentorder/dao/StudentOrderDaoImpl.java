package edu.newjavaproject.studentorder.dao;

import edu.newjavaproject.studentorder.config.Config;
import edu.newjavaproject.studentorder.domain.*;
import edu.newjavaproject.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

/***
 * That class implements methods and fields of {@link StudentOrderDao} interface.
 * Class help to save student order data from web-form to the database.
 */
public class StudentOrderDaoImpl implements StudentOrderDao{
    private static final String INSERT_ORDER =
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

    public static final String INSERT_CHILD =
            "INSERT INTO jc_student_child(" +
                    "student_order_id, c_sur_name, c_given_name, " +
                    "c_patronymic, c_date_of_birth, c_certificate_number, c_certificate_date, " +
                    "c_register_office_id, c_post_index, c_street_code, c_building, " +
                    "c_extension, c_apartment)" +
            "VALUES (?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?);";


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
     * @throws DaoException Data access object Exception
     */
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1L;          //ID of last added student order in out database

        //Устанавливаем соединение с БД и делаем запрос на улицы по паттерну.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"} )) {

            //Use transaction
            con.setAutoCommit(false);
            try {
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
                if (gkRs.next()) {
                    result = gkRs.getLong(1);
                }

                //Childrens
                saveChildren(con, so, result);
                // Commit transaction
                con.commit();
            } catch (SQLException e){
                //Rollback all commits from transaction
                con.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /***
     * That method save children data from student order application web form to our database
     * @param con Example of connection with our database
     * @param so Student order where the added child from
     * @param soId ID of student order where the added child from
     */
    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            int counter = 0;
            for (Child child : so.getChildren()) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch();
                counter++;
                if (counter > 10000) {
                    stmt.executeBatch();
                    counter = 0;
                }
            }
            if (counter > 0) {
                stmt.executeBatch();
            }
        }
    }
    /***
     * Insert parameters into SQL query to save student order into our database
     * @param stmt  SQL query template INSERT_ORDER
     * @param child Child for whom stmt params are configured
     * @throws SQLException
     */
    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException{
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child);
    }

    /***
     * Insert parameters into SQL query to save student order into our database
     * @param stmt  SQL query template INSERT_ORDER
     * @param start start parameterIndex of stmt
     * @param adult Adult for whom stmt params are configured
     * @throws SQLException
     */
    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, start + 8, adult);
    }

    /***
     * Insert parameters into SQL query for Person's common fields
     * @param stmt
     * @param start
     * @param person
     * @throws SQLException
     */
    private void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start + 1, person.getGivenName());
        stmt.setString(start + 2, person.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(person.getDateOfBirth()));
    }

    /***
     * Set params into SQL query for Address fields
     * @param stmt
     * @param start
     * @param person
     * @throws SQLException
     */
    private void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address address = person.getAddress();
        stmt.setString(start, address.getPostcode());
        stmt.setLong(start + 1, address.getStreet().getStreetCode());
        stmt.setString(start + 2, address.getBuilding());
        stmt.setString(start + 3, address.getExtension());
        stmt.setString(start + 4, address.getApartment());
    }
}
