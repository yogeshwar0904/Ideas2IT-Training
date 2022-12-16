package com.ideas2it.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.User;
import com.ideas2it.service.ProfileService;
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
public class ProfileServiceImpl implements ProfileService {
    private ProfileDao profileDao;

    public ProfileServiceImpl() {
        this.profileDao = new ProfileDaoImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
     public User getUser(String accountName, String password) 
                          throws InstagramManagementException { 
         User user = profileDao.getUser(accountName,password);

         if(null != user) { 
             return user;
         } else {
             throw new InstagramManagementException(Constant
                                          .NO_ACCOUNT_EXIST_TO_LOGIN);
         } 
    } 
 
    /**
     * {@inheritDoc}
     */
    @Override
    public User add(User user) {
        String userId = UUID.randomUUID().toString(); 
        user.setUserId(userId);
        return profileDao.create(user);
    } 
  
    /**
     * {@inheritDoc}
     */
    @Override 
    public boolean updateAccountActiveStatus(String accountName) throws
                                             InstagramManagementException { 
        User user = profileDao.getParticularAccountName(accountName);

        if (null != user) {
            return profileDao.updateAccountActiveStatus(accountName);
        } else {
           throw new InstagramManagementException(Constant
                                        .NO_ACCOUNT_EXIST_TO_DELETE);
        }
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public User searchParticularAccountName(String accountName) throws 
                                            InstagramManagementException {
        User user = profileDao.getParticularAccountName(accountName);

        if( null == user) {
            throw new InstagramManagementException(Constant
                                         .NO_ACCOUNT_EXIST_TO_SEARCH);
        } else {
            return user;
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUserProfileDetails(String accountName) 
                            throws InstagramManagementException {
        List<User> userProfileDetails = profileDao
                                        .getUserProfileDetails(accountName); 

        if (userProfileDetails.isEmpty()) {
            throw new InstagramManagementException(Constant
                                         .UNABLE_TO_SHOW_PROFILE);
        }  
        return userProfileDetails;  
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public User update(String accountName, String updateValue,
                           int choice) throws InstagramManagementException {
        User user = profileDao.getParticularAccountName(accountName);
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