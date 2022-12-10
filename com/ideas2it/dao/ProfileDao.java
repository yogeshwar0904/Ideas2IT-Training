package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;

/**
 *  To perform create, update, search and detele for the user
 *
 * @version    1.0 09 Dec 2022
 * @author     Yogeshwar
 */
public interface ProfileDao {     

    /**
     * Create account for user
     *
     * @param User user 
     *        details of the user
     * @return User user
     *        details of user if account created
     */
    public User create(User user);

    /**
     * To get account name of user
     *
     * @param accountName
     *        account name of user
     * @return User user
     *        details of user 
     */   
    public User getParticularAccountName(String accountName);

    /**
     * To get id of user
     *
     * @param accountName
     *        account name of user
     * @return String
     *         id of user 
     */ 
    public String getUserId(String accountName);

    /**
     * Deactivate the user account
     *
     * @param String accountName
     *        account name of user
     * @return boolean 
     *        true if account deactivated                    
     */ 
    public boolean updateAccountActiveStatus(String accountName);
           
    /**
     * update the profile of user
     *
     * @param String accountName
     *        account name of user
     * @param User user
     *         details of user
     * @param String userid
     *        id of user
     * @return User user
     *         details of user 
     */   
    public User update(String accountName, User user, String userId);

    /**
     * Login the user
     *
     * @param String accountName 
     *        account name  of the user
     * @param String password 
     *        password  of the user
     * @return User user
     *         details of user
     */
    public User login(String accountName, String password);

    /**
     * get the particular user profile details
     *
     * @param String accountName
     *        account name of user
     * @return List<User> 
     *        details of user account                   
     */ 
    public List<User> getUserProfileDetails(String accountName);
}