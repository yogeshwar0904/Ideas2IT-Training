package com.ideas2it.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import com.ideas2it.model.User;
import com.ideas2it.service.ProfileService;
import com.ideas2it.service.serviceImpl.ProfileServiceImpl;
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
public class ProfileController extends HttpServlet {
    private ProfileService profileService;

    public ProfileController() {
        this.profileService = new ProfileServiceImpl(); 
    }

    protected  void doPost(HttpServletRequest request, 
                    HttpServletResponse response) throws 
                    ServletException, IOException {
        String path = request.getServletPath();
        String accountName;
        String userName;
        String mobileNumber;
        String password;
        String message;
        User user = null;

        switch (path) {
        case "/login":
            accountName = request.getParameter("accountName");
            password = request.getParameter("password");
            user = this.getUser(accountName, password);
          
            if (null != user) {
                HttpSession session = request.getSession();
                session.setAttribute("userAccount", getUser(accountName, password));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("homePage");
                requestDispatcher.forward(request, response);
            } else {
                message = "Sorry Email Id or Password is wrong";
                request.setAttribute("Message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
                requestDispatcher.forward(request, response);
            }
            break;
  
        case "/register":
            accountName = request.getParameter("accountname");
            userName = request.getParameter("username");
            mobileNumber = request.getParameter("mobilenumber");
            password = request.getParameter("password");
            user = new User();
            user.setAccountName(accountName);
            user.setUserName(userName);
            user.setMobileNumber(Long.parseLong(mobileNumber));
            user.setPassword(password);
            create(user);
            message = "Account Created SuccessFully";
            request.setAttribute("Message", message);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(request, response);
            break;
    
        case "/Exit":
            break;
        }
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
     public User getUser(String accountName, String password) {
         try {
             return profileService.getUser(accountName, password);
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
     * Deactivate user account
     *
     * @param String accountName 
     *        name of the user account
     * @return boolean
     *        true if sucessfully account deleted         
     */  
    public boolean deactivateAccount(String accountName) { 
        try {
            return profileService.updateAccountActiveStatus(accountName);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }

    /** 
     * search the particular user account
     *
     * @param String accountName 
     *        account name of user
     * @return User user
     *        account name of user 
     *        if account name exist   
     */   
    public User searchParticularAccountName(String accountName) { 
        try {
            return profileService.searchParticularAccountName(accountName);
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * get profile details of the user
     *
     * @return List<User> 
     *         profile details of user         
     */   
    public List<User> getUserProfileDetails(String accountName) { 
        try {
            return profileService.getUserProfileDetails(accountName);
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
}