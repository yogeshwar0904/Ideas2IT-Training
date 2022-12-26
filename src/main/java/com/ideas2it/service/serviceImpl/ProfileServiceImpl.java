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
        return profileDao.getUser(accountName,password); 
    } 
 
    /**
     * {@inheritDoc}
     */
    @Override
    public String add(User user) throws InstagramManagementException {
        String id = UUID.randomUUID().toString(); 
        user.setUserId(id);
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
        return profileDao.getParticularAccountName(accountName);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserProfileDetails(String accountName) 
                            throws InstagramManagementException {
        return profileDao.getUserProfileDetails(accountName);    
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public User update(User user) throws InstagramManagementException {
        return profileDao.update(user);     
    }
}