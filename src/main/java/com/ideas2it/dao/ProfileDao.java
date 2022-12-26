package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.exception.InstagramManagementException;

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
     * @param user 
     *        details of the user
     * @return userId
     *        id of user if account created
     */
    public String create(User user) throws InstagramManagementException;

    /**
     * To get account name of user
     *
     * @param accountName
     *        account name of user
     * @return user
     *        details of user 
     */   
    public User getParticularAccountName(String accountName) 
                throws InstagramManagementException;

    /**
     * To get id of user
     *
     * @param accountName
     *        account name of user
     * @return userId
     *        id of user 
     */ 
    public String getUserId(String accountName);

    /**
     * Deactivate the user account
     *
     * @param accountName
     *        account name of user
     * @return true 
     *        if account deactivated                    
     */ 
    public boolean updateAccountActiveStatus(String accountName) 
                   throws InstagramManagementException;
           
    /**
     * update the profile of user
     *
     * @param accountName
     *        account name of user
     * @param user
     *        details of user
     * @param userid
     *        id of user
     * @return user
     *        details of user 
     */   
    public User update(User user) throws InstagramManagementException;

    /**
     * Login the user
     *
     * @param accountName 
     *        account name  of the user
     * @param password 
     *        password  of the user
     * @return user
     *        details of user
     */
    public User getUser(String accountName, String password) 
                throws InstagramManagementException;

    /**
     * get the particular user profile details
     *
     * @param String accountName
     *        account name of user
     * @return user 
     *        details of user account                   
     */ 
    public User getUserProfileDetails(String accountName) 
                      throws InstagramManagementException;
}