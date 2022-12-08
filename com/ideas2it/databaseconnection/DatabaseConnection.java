package com.ideas2it.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.constant.Constant;

public class DatabaseConnection {
    private static Connection connection = null;
    private static DatabaseConnection databaseConnection;  
 
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(Constant.URL, Constant.USER_NAME, Constant.PASSWORD);
        } catch (SQLException exception) {
             CustomLogger.fatal("Not connected to database");
        }
    }
    
    public static Connection getConnection() {
        try {
            if(null == connection || connection.isClosed()) {
                databaseConnection = new DatabaseConnection();
            } 
        } catch (SQLException exception) {
            CustomLogger.fatal("Not connected to database");
        }
        return connection;
    } 

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            CustomLogger.fatal("SQL Exception");         
        }
    }
}


            


