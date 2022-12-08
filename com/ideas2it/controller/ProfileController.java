package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.User;
import com.ideas2it.service.ProfileService;
import com.ideas2it.constant.Constant;
import com.ideas2it.util.InstagramUtil;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;

/**
 * Based on user request it perform login, create  
 * validate the user account.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class ProfileController {
    private ProfileService profileService;
    private InstagramUtil instagramUtil;

    public ProfileController() {
        this.profileService = new ProfileService(); 
        this.instagramUtil = new InstagramUtil();
    }

    /**
     * Allow the user to login.
     *
     * @param accountName
     *        account name of user
     * @param password
     *        password of user
     * @return User user
     *        details of the user.
     */
     public User login(String accountName, String password) {
         try {
             return profileService.login(accountName, password);
         } catch (InstagramManagementException exception) {
             CustomLogger.error(exception.getMessage());
         }
         return null; 
     }

    /**
     * Create account for the user
     *
     * @param user
     *        details of the user
     * @return users
     *        details of the user           
     */ 
    public User create(User user) {
        return profileService.add(user);    
    }

    /**
     * remove the user
     *
     * @param accountName 
     *        name of the account
     * @param password
     *        password of the account
     * @return null if sucessfully deleted         
     */  
    public boolean deleteAccount(String accountName, String password) { 
        try {
            return profileService.deleteAccount(accountName, password);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }

    /** 
     * search the user
     *
     * @param String accountName 
     *        account name of user
     * @return user
     *         account name of user 
     *         if account name exist   
     */   
    public User search(String accountName) { 
        try {
            return profileService.search(accountName);
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * display the user
     *
     * @return Map<String, User> 
     *         accounts of user         
     */   
    public List<String> getAllUserAccount() { 
        try {
            return profileService.getAllUserAccount();
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * update the user
     *
     * @param string accountName
     *        account name of user
     * @param string updateValue
     *        update detail  of user
     * @param int choice
     *        choice of user
     * @return user
     *         details of user if account
     *         updated succesfully.
     */   
    public User update(String accountName, String updateValue, int choice) { 
        try {
            return profileService.update(accountName, updateValue, choice); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return null; 
    } 

    /**
     * validate the account name
     *
     * @param string accountName
     *        account name of user
     * @return if account name valid return true
     *         else return false         
     */  
    public boolean isValidAccountName(String accountName) {
        return instagramUtil.isValidAccountName(accountName);
    }
 
    /**
     * validate the name of user
     *
     * @param name
     *        name of user  
     * @return if name valid return true
     *         else return false       
     */
    public boolean isValidName(String name) {
        return instagramUtil.isValidName(name);
    }

    /**
     * validate the mobile number
     *
     * @param mobileNumber
     *        mobile number of user
     * @return if mobile number valid return true
     *         else return false               
     */
    public boolean isValidMobileNumber(long mobileNumber) {
        return instagramUtil.isValidMobileNumber(mobileNumber);
    }

    /**
     * validate the password
     *
     * @param password
     *        password of user
     * @return if password valid return true
     *         else return false              
     */ 
    public boolean isValidPassword(String password) {
        return instagramUtil.isValidPassword(password);
    }
}