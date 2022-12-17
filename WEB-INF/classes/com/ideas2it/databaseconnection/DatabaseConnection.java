package com.ideas2it.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.constant.Constant;

/**
 * Implements connection to database server
 *
 * @version 1.0 01-Nov-2022
 * @author Yogeshwar
 */
public class DatabaseConnection {
    private static Connection connection = null; 
    
    private DatabaseConnection() {}
    
    /**
     * get the connection for the for mysql database
     */
    public static Connection getConnection() {

        try {
            if (null == connection || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(Constant.URL, 
                                   Constant.USER_NAME, Constant.PASSWORD);
            } 
        } catch (ClassNotFoundException | SQLException sqlException) {
            CustomLogger.fatal(sqlException.getMessage());
        }
        return connection;
    } 

    /**
     * close the connection for the for mysql database
     */
    public static void closeConnection() {

        try {
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            CustomLogger.fatal(sqlException.getMessage());         
        }
    }
}