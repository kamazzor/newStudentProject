package edu.javaproject.studentorder.dao;

import edu.javaproject.studentorder.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * Database connection builder. Database is specified in dao.properties
 */
public class ConnectionBuilder {
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;

    }
}
