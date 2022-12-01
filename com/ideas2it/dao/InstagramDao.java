package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.model.User;

/**
 * To perform create,update,search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class InstagramDao {

    Connection connection;  
    String query;    
    PreparedStatement statement;

    /**
     * Add the user
     *
     * @param user 
     *        details of the user
     * @return users
     *         details of user
     */
    public User create(User user) {

        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO user(user_id, account_name, user_name, mobile_number, password) VALUES(?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getUserId());
            statement.setString(2, user.getAccountName());
            statement.setString(3, user.getUserName());
            statement.setLong(4, user.getMobileNumber());
            statement.setString(5, user.getPassword());
            statement.execute(); 
            statement.close();
        } catch (SQLException exception) {
           exception.printStackTrace();
            CustomLogger.error("SQL exception occure");
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;
    }

    /**
     * search the user
     *
     * @param accountName
     *        account name of user
     * @return users
     *         details of user 
     */   
    public User getAccountName(String accountName) {
        ResultSet resultset;
        User user = null;

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * From user WHERE account_name = ? and is_deactivated = 0";
            statement = connection.prepareStatement(query);
            statement.setString(1,accountName);
            resultset = statement.executeQuery();

            if (resultset.next()) {
                user = new User();
                System.out.println(resultset.getString("user_name"));
                user.setUserId(resultset.getString("user_id"));
                user.setAccountName(resultset.getString("account_name"));
                user.setUserName(resultset.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultset.getString("mobile_number")));
                user.setPassword(resultset.getString("password"));
                statement.close();
            }
         } catch(SQLException exception) {
             CustomLogger.error("SQL exception occure");
         } finally {
             DatabaseConnection.closeConnection();
         }
         return user;    
    }

    public String getUserId(String accountName) {
        ResultSet resultset;
        String userId = null;
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT user_id From user WHERE account_name = ? and is_deactivated = 0";
            statement = connection.prepareStatement(query);
            statement.setString(1,accountName);
            resultset = statement.executeQuery();

            if (resultset.next()) {
                userId = resultset.getString("user_id");
            }
            statement.close();
         } catch(SQLException exception) {
             CustomLogger.error("SQL exception occure");
         } finally {
             DatabaseConnection.closeConnection();
         }
         return userId;    
    }

    /**
     * remove the user
     *
     * @param accountName
     *        account name of user
     * @return deleted message                  
     */ 
    public boolean deleteAccount(String accountName, String password) {
       boolean isFound = false;
 
       try {
           connection = DatabaseConnection.getConnection();
           query ="UPDATE user SET is_deactivated = 1 Where account_name = ? and password = ?";
           statement = connection.prepareStatement(query);
           statement.setString(1, accountName);
           statement.setString(2, password);
           isFound = statement.execute();
           statement.close();
       } catch (SQLException exception) {
           CustomLogger.error("SQL exception occure");
           exception.printStackTrace();
       } finally {
           DatabaseConnection.closeConnection();           
       }
       return !isFound;
    }

    public List<String> display() {
        List<String> accountNames = new ArrayList();
        ResultSet resultSet;
        String query;
        query = "SELECT account_name FROM user WHERE is_deactivated = 0";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                accountNames.add(resultSet.getString("account_name"));
            } 
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        }  finally {
            DatabaseConnection.closeConnection();
        }
        return accountNames;         
    }    

    /**
     * update the user
     *
     * @param accountName
     *        account name of user
     * @param updatevalue
     *        update detail of user
     * @param choice
     *        choice of user
     * @return Map<String, User>
     *         account of user 
     */   
    public User update(String accountName, User user, String userId) {
        try {
            connection = DatabaseConnection.getConnection();
            StringBuilder query = new StringBuilder();
            query.append("UPDATE user SET user_name = ?,")
                 .append("mobile_number = ?, password = ?")
                 .append("WHERE user_id = ? and is_deactivated = 0");
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getMobileNumber());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserId());
            statement.execute();
            statement.close();
        } catch(SQLException exception) {
            CustomLogger.error("SQL exception occure");
            exception.printStackTrace(); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;   
    }

    public User login(String accountName, String password) {
        ResultSet resultset;
        User user = null;
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * From user WHERE account_name = ? and is_deactivated = 0 and password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1,accountName);
	    statement.setString(2,password);
            resultset = statement.executeQuery();

            if (resultset.next()) {
                user = new User();
                System.out.println(resultset.getString("user_name"));
                user.setUserId(resultset.getString("user_id"));
                user.setAccountName(resultset.getString("account_name"));
                user.setUserName(resultset.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultset.getString("mobile_number")));
                user.setPassword(resultset.getString("password"));
                statement.close();
            }
         } catch(SQLException exception) {
             CustomLogger.error("SQL exception occure");
         } finally {
             DatabaseConnection.closeConnection();
         }
         return user;    
    }
}