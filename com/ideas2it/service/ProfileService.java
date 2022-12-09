package com.ideas2it.service;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.User;
import com.ideas2it.dao.daoImpl.ProfileDaoImpl;
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
        this.profileDao = new ProfileDaoImpl();
    }

    /**
     * Login the user if already user account exist.
     *
     * @param accountName
     *        account name of user
     * @param password
     *        password of user
     * @return User user
     *        details of user if login succesfully
     * @throws InstagramManagementException
     *        account does not exist  
     */
     public User login(String accountName, String password) 
                          throws InstagramManagementException { 
         User user = profileDao.login(accountName,password);

         if(null != user) { 
             return user;
         } else {
             throw new InstagramManagementException(Constant
                                          .NO_ACCOUNT_EXIST_TO_LOGIN);
         } 
    } 
 
    /**
     * create the account for user.
     *
     * @param User user 
     *        details of the user
     * @return User user
     *        details of user if account created succesfully       
     */
    public User add(User user) {
        String userId = UUID.randomUUID().toString();
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
     * @return boolean true
     *        if sucessfully deleted  
     * @throws InstagramManagementException
     *        no account exist to delete       
     */ 
    public boolean deleteAccount(String accountName, String password) throws
                                    InstagramManagementException { 
        User user = profileDao.getAccountName(accountName);

        if ((null != user) && (user.getPassword().equals(password))) {
            return profileDao.deactivateAccount(accountName, password);
        } else {
           throw new InstagramManagementException(Constant
                                        .NO_ACCOUNT_EXIST_TO_DELETE);
        }
    }
   
    /** 
     * search the user
     *
     * @param String accountName 
     *        account name of user
     * @return User user
     *         account name of user 
     * @throws InstagramManagementException
     *        no account exist to search  
     */
    public User search(String accountName) throws 
                         InstagramManagementException {
        User user = profileDao.getAccountName(accountName);

        if( null == user) {
            throw new InstagramManagementException(Constant
                                         .NO_ACCOUNT_EXIST_TO_SEARCH);
        } else {
            return user;
        } 
    }

    /**
     * display the user
     *
     * @return List<String>
     *         accountNames of user
     * @throws InstagramManagementException
     *         no account exist to display 
     */
    public List<String> getAllUserAccount() 
                            throws InstagramManagementException {
        List<String> accountNames = profileDao.getAllAccountName(); 

        if (accountNames.isEmpty()) {
            throw new InstagramManagementException(Constant
                                         .NO_ACCOUNT_EXIST_TO_SHOW);
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
     * @return User user
     *         update the users account  
     * @throws InstagramManagementException
     *         no account exist to update       
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
            return profileDao.update(accountName, user, userId);
        }
        throw new InstagramManagementException(Constant.UNABLE_TO_UPDATE);
    }
}