package com.ideas2it.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    protected  void doPost(HttpServletRequest request,                            
                           HttpServletResponse response) throws 
                           ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
        case "/login":
            login(request, response);
            break;
  
        case "/register":
            register(request, response);
            break;
    
        case "/update":
            update(request, response);
            break;

        case "/delete":
            delete(request, response);
            break;
        }
    }

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    protected  void doGet(HttpServletRequest request,                            
                           HttpServletResponse response) throws 
                           ServletException, IOException {
        String path = request.getServletPath();

        switch (path) { 
        case "/showUserDetails":
            //getUserProfileDetails(request, response);
            break;

        case "/postMenu":
            break;
        }
    }

    /**
     * Allows the user to login when the email and password is Valid
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void login(HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
                                                     ServletException {
         User user = this.getUser(request.getParameter("accountName"),
                                  request.getParameter("password"));
          
         if (null != user) {
             HttpSession session = request.getSession();
             session.setAttribute("accountName", request.getParameter("accountName"));
             response.sendRedirect("homePage.jsp");
         } else {
             String message = "Sorry Email Id or Password is wrong";
             request.setAttribute("Message", message);
             RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
             requestDispatcher.forward(request, response);
         }
    }

    /**
     * Register new account for the user.
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void register(HttpServletRequest request,  
                          HttpServletResponse response) throws IOException,
                                                       ServletException {
        User user = new User();
        user.setAccountName(request.getParameter("accountName"));
        user.setUserName(request.getParameter("userName"));
        user.setMobileNumber(request.getParameter("mobileNumber"));
        user.setPassword(request.getParameter("password"));
        String message = "Account Created SuccessFully";
        request.setAttribute("Message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
        profileService.add(user);    
    }

    /**
     * Updates the details of the user.
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            String accountName = (String)session.getAttribute("accountName");
            String userName = (String)session.getAttribute("userName");
            User user = this.searchParticularAccountName(accountName);
            user.setAccountName(accountName);
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setMobileNumber(request.getParameter("mobileNumber"));
            profileService.update(user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("homePage.jsp");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
        }
    }

    /**
     * delete the account of the user.
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void delete(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            String accountName = (String)session.getAttribute("accountName");
            String mobileNumber = (String)session.getAttribute("mobileNumber");
            profileService.updateAccountActiveStatus(accountName);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher
                                                          ("login.jsp");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
        }
    }

    /** 
     * gets the user account name and password.  
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
}