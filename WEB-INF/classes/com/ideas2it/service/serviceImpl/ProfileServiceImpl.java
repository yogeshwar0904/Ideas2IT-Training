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
         return user;
    } 
 
    /**
     * {@inheritDoc}
     */
    @Override
    public User add(User user) throws InstagramManagementException {
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
        return profileDao.updateAccountActiveStatus(accountName);
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public User searchParticularAccountName(String accountName) throws 
                                            InstagramManagementException { 
        User user = profileDao.getParticularAccountName(accountName);
        return user;    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUserProfileDetails(String accountName) 
                            throws InstagramManagementException {
        List<User> userProfileDetails = profileDao
                                        .getUserProfileDetails(accountName);   
        return userProfileDetails;  
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public User update(User user) throws InstagramManagementException {
        return profileDao.update(user);     
    }
}