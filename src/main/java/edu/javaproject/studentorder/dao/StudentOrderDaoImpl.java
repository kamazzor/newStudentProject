package edu.javaproject.studentorder.dao;

import edu.javaproject.studentorder.config.Config;
import edu.javaproject.studentorder.domain.*;
import edu.javaproject.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                    "h_university_id, h_student_number, " +
                    "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, " +
                    "w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, " +
                    "w_post_index, w_street_code, w_building, w_extension, " +
                    "w_apartment, w_university_id, w_student_number, certificate_id, " +
                    "register_office_id, marriage_date) " +
            "VALUES (?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?);";

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

    public static final String SELECT_ORDERS =
            "select so.*, ro.r_office_area_id, ro.r_office_name, " +
                    "po_h.p_office_area_id h_p_office_area_id, po_h.p_office_name h_p_office_name, " +
                    "po_w.p_office_area_id w_p_office_area_id, po_w.p_office_name w_p_office_name " +
            "from jc_student_order so " +
            "inner join jc_register_office ro on ro.r_office_id = so.register_office_id " +
            "inner join jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "inner join jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "where student_order_status = ? order by student_order_date";

    // TODO: 10/15/2019 change that query
    public static final String SELECT_CHILD =
            "SELECT soc.*, ro.r_office_area_id, ro.r_office_name " +
            "FROM jc_student_child soc " +
            "INNER JOIN jc_register_office ro " +
            "ON ro.r_office_id = soc.c_register_office_id " +
            "WHERE student_order_id IN ";

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
        //Вторым параметром в Connection передан массив имен столбцов,
        //данные из которых нам нужны сразу для продолжения работы программы.
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
                setParamsForAdult(stmt, 18, so.getWife());

                //Marriage
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, Date.valueOf(so.getMarriageDate()));

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

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();
        //Устанавливаем соединение с БД и делаем запрос на получение студенческих заявок из БД.
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)) {

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            List<Long> ids = new LinkedList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                StudentOrder so = new StudentOrder();
                fillStudentOrder (rs, so);
                fillMarriage(rs, so);
                Adult husband = fillAdult(rs, "h_");
                Adult wife = fillAdult(rs, "w_");
                so.setHusband(husband);
                so.setWife(wife);

                result.add(so);
                ids.add(so.getStudentOrderId());
            }

            findChildren(con, result);

            rs.close();

        } catch (SQLException e){
                 throw new DaoException(e);
        }
        return result;
    }

    /**
     * Method find childrens for each processed student order
     * @param con Connection with out database
     * @param result List of Student orders we process
     * @throws SQLException
     */
    private void findChildren(Connection con, List<StudentOrder> result) throws SQLException{
        //Создали добавку в SQL-запрос SELECT_CHILD с id обрабатываемых студенческих заявок
        String cl = "(" + result.stream().map(so -> String.valueOf(so.getStudentOrderId()))
                .collect(Collectors.joining(",")) + ")";

        //Создаём Map: StudentOrderId -> student order object with that student order id
        Map<Long, StudentOrder> studentOrdersMap = result.stream().collect(Collectors
                .toMap(so -> so.getStudentOrderId(), so -> so));

        try (PreparedStatement stmt = con.prepareStatement(SELECT_CHILD + cl)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Child ch = fillChild(rs);
                StudentOrder so = studentOrdersMap.get(rs.getLong("student_order_id"));
                so.addChild(ch);
            }
        }
    }

    //---------------------------------------------------------------------
    /**
     *  Fill fields of 1 Child example with data from our database.
     * @param rs ResultSet after executing SELECT_CHILD query
     * @return Return Child object
     * @throws SQLException
     */
    private Child fillChild(ResultSet rs) throws SQLException {
        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        Child child = new Child(surName, givenName, patronymic, dateOfBirth);

        child.setCertificateNumber(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());

        //Данные Офиса ЗАГСа, где родился ребенок
        Long roId = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, roArea, roName);
        child.setIssueDepartment(ro);

        Address address = new Address();
        Street street = new Street(rs.getLong("c_street_code"), "");
        address.setPostcode(rs.getString("c_post_index"));
        address.setBuilding(rs.getString("c_building"));
        address.setExtension(rs.getString("c_extension"));
        address.setApartment(rs.getString("c_apartment"));
        address.setStreet(street);
        child.setAddress(address);

        return child;
    }
    /**
     *  Fill husband and wife fields of StudentOrder example with data from our database.
     * @param rs ResultSet after executing SELECT_ORDERS query
     * @param prefix Prefix h_ or w_ of columnlabel of ResultSet obtained after SELECT_ORDERS query
     * @return Return Adult object
     * @throws SQLException
     */
    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(rs.getString(prefix + "sur_name"));
        adult.setPatronymic(rs.getString(prefix + "given_name"));
        adult.setDateOfBirth(rs.getDate(prefix + "date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(prefix + "passport_seria"));
        adult.setPassportNumber(rs.getString(prefix + "passport_number"));
        adult.setIssueDate(rs.getDate(prefix + "passport_date").toLocalDate());

        //Данные паспортного стола из БД
        Long poId = rs.getLong(prefix + "passport_office_id");
        String poArea = rs.getString(prefix + "p_office_area_id");
        String poName = rs.getString(prefix + "p_office_name");
        PassportOffice po = new PassportOffice(poId, poArea, poName);
        adult.setIssueDepartment(po);

        //Данные адреса и улицы из БД
        Address address = new Address();
        Street street = new Street(rs.getLong(prefix + "street_code"), "");
        address.setPostcode(rs.getString(prefix + "post_index"));
        address.setBuilding(rs.getString(prefix + "building"));
        address.setExtension(rs.getString(prefix + "extension"));
        address.setApartment(rs.getString(prefix + "apartment"));
        address.setStreet(street);
        adult.setAddress(address);

        //Заглушка университета
        University univer = new University(rs.getLong(prefix + "university_id"), "");
        adult.setUniversity(univer);
        adult.setStudentId(rs.getString(prefix + "student_number"));
        return adult;
    }

    /**
     * Fill header fields of StudentOrder example with data from our database.
     * @param rs ResultSet after executing SELECT_ORDERS query
     * @param so Example of Student order where we save student order data from our database
     * @throws SQLException
     */
    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }

    /**
     * Fill marriage fields of StudentOrder example with data from our database.
     * @param rs ResultSet after executing SELECT_ORDERS query
     * @param so Example of Student order where we save student order data from our database
     * @throws SQLException
     */
    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        //Данные офиса бракосочетания из БД
        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String areaName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, areaId, areaName);
        so.setMarriageOffice(ro);
    }
    //----------------------------------------------------------------------------------
    /***
     * That method save children data from student order application web form to our database
     * @param con Example of connection with our database
     * @param so Student order where the added child from
     * @param soId ID of student order where the added child from
     */
    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            for (Child child : so.getChildren()) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.executeUpdate();
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
        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
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
