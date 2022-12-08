package com.ideas2it.service;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.User;
import com.ideas2it.dao.ProfileDao;
import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;

/**
 * perform the create, update, delete,
 * search and display operation of  
 * the user account.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class ProfileService {
    private ProfileDao profileDao;

    public ProfileService() {
        this.profileDao = new ProfileDao();
    }

    /**
     * Login the user.
     *
     * @param accountName
     *        account name of user
     * @param password
     *        password of user
     */
     public User login(String accountName, String password) throws InstagramManagementException { 
         User user;
         user = profileDao.login(accountName,password);

         if(null != user&& user.getPassword().equals(password)) { 
             return user;
         } else {
             throw new InstagramManagementException(Constant.ERROR_05);
         } 
    } 
 
    /**
     * Add the user
     *
     * @param accountName 
     *        accountName of the user
     * @param user 
     *        details of the user
     * @return users
     *         details of user        
     */
    public User add(User user) {
        String userId;
        userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return profileDao.create(user);
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
    public boolean deleteAccount(String accountName, 
                        String password) throws InstagramManagementException { 
        User user = profileDao.getAccountName(accountName);

        if (null != user && user.getPassword().equals(password)) {
            return profileDao.deleteAccount(accountName, password);
        } else {
           throw new InstagramManagementException(Constant.ERROR_03);
        }
    }
   
    /* search the user
     *
     * @param String accountName 
     *        account name of user
     * @return user
     *         account name of user   
     */
    public User search(String accountName) throws 
                    InstagramManagementException  { 
        User user = profileDao.getAccountName(accountName);

        if( null == user) {
            throw new InstagramManagementException(Constant.ERROR_01);
        } else {
            return user;
        } 
    }

    /**
     * display the user
     *
     * @param 
     * @return List<String>
     *         accountNames of user
     */
    public List<String> getAllUserAccount() throws InstagramManagementException {
        List<String> accountNames = profileDao.getAllAccountName(); 

        if (accountNames.isEmpty()) {
            throw new InstagramManagementException(Constant.ERROR_04);
        }  
        return accountNames;  
    }

    /**
     * update the user
     *
     * @param String accountName 
     *        accountName of the user
     * @param int choice
     *        choice of the user
     * @return User
     *         update the users account        
     */   
    public User update(String accountName, String updateValue,
                           int choice) throws InstagramManagementException {
        User user = profileDao.getAccountName(accountName);
        String userId = profileDao.getUserId(accountName);

        if (null != user) {
            switch (choice) {
            case Constant.UPDATE_USER_NAME:
                user.setUserName(updateValue);
                break;

            case Constant.UPDATE_MOBILE_NUMBER:
                user.setMobileNumber(Long.parseLong(updateValue));
                break;

            case Constant.UPDATE_PASSWORD:	
                user.setPassword(updateValue);
                break;

            default:
                CustomLogger.info("invalid!");
                break;         
            }
            return profileDao.update(accountName, user,userId);
        }
        throw new InstagramManagementException(Constant.ERROR_02);
    }
}