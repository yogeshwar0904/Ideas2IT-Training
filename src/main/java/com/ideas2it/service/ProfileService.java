package com.ideas2it.service;

import java.util.List;

import com.ideas2it.model.User;
import com.ideas2it.exception.InstagramManagementException;

/**
 * perform the create, update, delete,
 * search and display operation of  
 * the user account.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public interface ProfileService {

    /**
     * Login the user if already user account exist.
     *
     * @param accountName
     *        account name of user
     * @param password
     *        password of user
     * @return user
     *        details of user if login succesfully
     * @throws InstagramManagementException
     *        account does not exist  
     */
    public User getUser(String accountName, String password) 
                         throws InstagramManagementException;

    /**
     * create the account for user.
     *
     * @param user 
     *        details of the user
     * @return user
     *        details of user if account created succesfully       
     */
    public User add(User user) throws InstagramManagementException; 
  
    /**
     * remove the user
     *
     * @param accountName 
     *        name of the account
     * @return true
     *        if sucessfully deleted  
     * @throws InstagramManagementException
     *        no account exist to delete       
     */ 
    public boolean updateAccountActiveStatus(String accountName) throws
                                             InstagramManagementException;

    /** 
     * search the user
     *
     * @param accountName 
     *        account name of user
     * @return user
     *         account name of user 
     * @throws InstagramManagementException
     *        no account exist to search  
     */
    public User searchParticularAccountName(String accountName)
                throws InstagramManagementException;

    /**
     * display the profile of the user
     *
     * @return user
     *         detils of user profile
     * @throws InstagramManagementException
     *         no account exist to display 
     */
    public User getUserProfileDetails(String accountName) 
                            throws InstagramManagementException;

    /**
     * update the details of user
     *
     * @param accountName 
     *        accountName of the user
     * @param choice
     *        choice of the user
     * @return user
     *        update the users account  
     * @throws InstagramManagementException
     *        no account exist to update       
     */   
    public User update(User user) throws InstagramManagementException;
}