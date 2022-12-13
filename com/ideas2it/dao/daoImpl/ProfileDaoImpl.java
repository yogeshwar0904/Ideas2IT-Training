package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.dao.ProfileDao;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;

/**
 * To perform create, update, search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class ProfileDaoImpl implements ProfileDao {     

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User user) {
        User userAccount = null;
        int numberOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO")
             .append(" user (user_id, account_name, user_name,")
             .append(" mobile_number, password)")
             .append(" VALUES(?, ?, ?, ?, ?);");
       
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getAccountName());
            statement.setString(3, user.getUserName());
            statement.setLong(4, user.getMobileNumber());
            statement.setString(5, user.getPassword());
            numberOfRowsAffected = statement.executeUpdate(); 

            if(numberOfRowsAffected > 0) {
                userAccount  = user;
            }
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return userAccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public User getParticularAccountName(String accountName) {
        StringBuilder query = new StringBuilder();
        User user =  new User();
        query.append("SELECT * From user WHERE account_name = ?")
             .append(" AND is_deactivated = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, accountName);
            ResultSet resultSet = statement. executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getString("user_id"));
                user.setAccountName(resultSet.getString("account_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultSet
                                     .getString("mobile_number")));
                user.setPassword(resultSet.getString("password"));
            }
            statement.close();
         } catch(SQLException sqlException) {
             CustomLogger.error(sqlException.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
         return user;    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserId(String accountName) {
        String userId = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT user_id From user WHERE account_name = ?")
             .append(" AND is_deactivated = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, accountName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getString("user_id");
            }
            statement.close();
         } catch(SQLException sqlException) {
             CustomLogger.error(sqlException.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
         return userId;    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateAccountActiveStatus(String accountName) {
       boolean isDeactivated = false;
       StringBuilder query = new StringBuilder();
       query.append("UPDATE user SET is_deactivated = 1")
            .append(" WHERE account_name = ?;");

       try {
           Connection connection = DatabaseConnection.getConnection();
           PreparedStatement statement = connection
                                         .prepareStatement(query.toString());
           statement.setString(1, accountName);
           isDeactivated = statement.execute();
           statement.execute();
           statement.close();
       } catch (SQLException sqlException) {
           CustomLogger.error(sqlException.getMessage());
       } finally {
           DatabaseConnection.closeConnection();           
       }
       return !isDeactivated;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override  
    public User update(String accountName, User user, String userId) {
        boolean isUpdated = false;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE user SET user_name = ?,")
             .append(" mobile_number = ?, password = ?")
             .append(" WHERE user_id = ? AND is_deactivated = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getMobileNumber());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserId());
            isUpdated = statement.execute();
 
            if (!isUpdated) {
                return user;
            }
            statement.close();
        } catch(SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage()); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String accountName, String password) {
        User user = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * From user")
             .append(" WHERE account_name = ? AND is_deactivated = 0")
             .append(" And password = ?;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, accountName);
	    statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setAccountName(resultSet.getString("account_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultSet
                                         .getString("mobile_number")));
                user.setPassword(resultSet.getString("password"));
            }
            statement.close();
         } catch(SQLException sqlException) {
             CustomLogger.error(sqlException.getMessage());
         } finally {
             DatabaseConnection.closeConnection(); 
         }
         return user;    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUserProfileDetails(String accountName) {
        List<User> userProfileDetails = new ArrayList();
        StringBuilder query = new StringBuilder();
        User user = null;
        query.append("SELECT * FROM user")
             .append(" WHERE account_name = ? AND is_deactivated = 0;");
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, accountName);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setAccountName(resultSet.getString("account_name"));
                user.setUserName(resultSet.getString("user_name"));
                user.setMobileNumber(Long.parseLong(resultSet
                                         .getString("mobile_number")));
                userProfileDetails.add(user);               
            } 
            statement.close(); 
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return userProfileDetails;       
    }
}