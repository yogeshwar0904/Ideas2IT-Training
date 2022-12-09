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
    public User getAccountName(String accountName);

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
     * @param String password
     *        password of user
     * @return boolean 
     *        true if account deactivated                    
     */ 
    public boolean deactivateAccount(String accountName, String password);

    /**
     * Display all account name of user
     *
     * @return List<String> accountNames 
     *         all user account name.                  
     */ 
    public List<String> getAllAccountName();
           
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
}