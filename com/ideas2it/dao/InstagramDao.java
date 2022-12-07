package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Post;
import com.ideas2it.model.User;

/**
 * To perform create,update,search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class InstagramDao {

    private Connection connection; 
    private PreparedStatement statement; 
    private String query;    

    /**
     * Create account for user
     *
     * @param user 
     *        details of the user
     * @return users
     *         details of user
     */
    public User create(User user) {
        StringBuilder query;
        query = new StringBuilder();
        query.append("INSERT INTO ")
             .append("user (user_id, account_name, user_name, mobile_number, password)")
             .append("VALUES(?,?,?,?,?)");
       
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getAccountName());
            statement.setString(3, user.getUserName());
            statement.setLong(4, user.getMobileNumber());
            statement.setString(5, user.getPassword());
            statement.execute(); 
            statement.close();
        } catch (SQLException exception) {
            CustomLogger.error(exception.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;
    }

    /**
     * To get account name of user
     *
     * @param accountName
     *        account name of user
     * @return users
     *         details of user 
     */   
    public User getAccountName(String accountName) {
        ResultSet resultSet;
        User user = null;
        query = "SELECT * From user WHERE account_name = ? and is_deactivated = 0";
        user = new User();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,accountName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getString("user_id"));
                user.setAccountName(resultSet.getString("account_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultSet.getString("mobile_number")));
                user.setPassword(resultSet.getString("password"));
                statement.close();
            }
         } catch(SQLException exception) {
             CustomLogger.error(exception.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
         return user;    
    }

    /**
     * To get id of user
     *
     * @param accountName
     *        account name of user
     * @return String
     *         id of user 
     */ 
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
             CustomLogger.error(exception.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
         return userId;    
    }

    /**
     * remove the user account
     *
     * @param accountName
     *        account name of user
     * @param password
     *        password of user
     * @return boolean 
     *         true if deleted message                  
     */ 
    public boolean deleteAccount(String accountName, String password) {
       boolean isFound;
       isFound = false;
 
       try {
           connection = DatabaseConnection.getConnection();
           query ="UPDATE user SET is_deactivated = 1 Where account_name = ? and password = ?";
           statement = connection.prepareStatement(query);
           statement.setString(1, accountName);
           statement.setString(2, password);
           isFound = statement.execute();
           statement.execute();
           statement.close();
       } catch (SQLException exception) {
           CustomLogger.error(exception.getMessage());
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
        StringBuilder query;
        query = new StringBuilder();
        query.append("UPDATE user SET user_name = ?,")
             .append("mobile_number = ?, password = ?")
             .append("WHERE user_id = ? and is_deactivated = 0");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getMobileNumber());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserId());
            statement.execute();
            statement.close();
        } catch(SQLException exception) {
            CustomLogger.error(exception.getMessage()); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;   
    }

    /**
     * Login the user
     *
     * @param String accountName 
     *        account name  of the user
     * @param String password 
     *        password  of the user
     * @return users
     *         details of user
     */
    public User login(String accountName, String password) {
        ResultSet resultSet;
        StringBuilder query;
        User user = null;
        query = new StringBuilder();
        query.append("SELECT * From user ")
             .append("WHERE account_name = ? and is_deactivated = 0 and password = ?");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,accountName);
	    statement.setString(2,password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setAccountName(resultSet.getString("account_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultSet.getString("mobile_number")));
                user.setPassword(resultSet.getString("password"));
                statement.close();
            }
         } catch(SQLException exception) {
             CustomLogger.error(exception.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
         return user;    
    }
}