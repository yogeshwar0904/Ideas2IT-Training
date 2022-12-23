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
    private CustomLogger logger;

    public ProfileController() {
        this.profileService = new ProfileServiceImpl(); 
        this.logger = new CustomLogger(ProfileController.class);
    }

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to 
     *                   get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */
    protected  void doPost(HttpServletRequest request,                            
                           HttpServletResponse response) throws 
                           IOException, ServletException {
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

        case "/getUserProfileDetails":
            getUserProfileDetails(request, response);
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
     * @param request  - The request object is used to 
     *                   get the request parameters.
     * @param response - This is the response object that 
     *                   is used to send data back to the client.
     */
    protected  void doGet(HttpServletRequest request,                            
                           HttpServletResponse response) throws 
                           IOException, ServletException {
        String path = request.getServletPath();

        switch (path) { 
        case "/getUserProfileDetails":
            getUserProfileDetails(request, response);
            break;

        case "/postMenu":
            break;
        }
    }

    /**
     * Allows the user to login when the email and password is Valid
     *
     * @param request  - The request object is used to
     *                   get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */
    private void login(HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
                                                     ServletException {
        User user = null;

        try {
            user = profileService.getUser(request.getParameter("accountName"), 
                                  request.getParameter("password"));
            logger.info("Inside the login method");
          
            if (null != user) {
                HttpSession session = request.getSession();
                session.setAttribute("accountName", user.getAccountName());
                session.setAttribute("user", user);
                response.sendRedirect("homePage.jsp");
            } else {
                request.setAttribute("Message", Constant.LOGIN_ERROR);
                RequestDispatcher requestDispatcher = request
                                  .getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }        
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Register new account for the user.
     *
     * @param request  - The request object is used to 
     *                   get the request parameters.
     * @param response - This is the response object that 
     *                   is used to send data back to the client.
     */
    private void register(HttpServletRequest request,  
                          HttpServletResponse response) throws IOException,
                                                       ServletException {
        User user = new User();
        String accountName = getAccountName(request, response);

        try {
            if (accountName == null) {
                request.setAttribute("message", Constant.ACCOUNT_NAME_ALREDY_EXIST);
                RequestDispatcher requestDispatcher = request
                                            .getRequestDispatcher("register.jsp");
                requestDispatcher.forward(request, response);
            } else {
                user.setAccountName(accountName);
                user.setUserName(request.getParameter("userName"));
                user.setMobileNumber(request.getParameter("mobileNumber"));
                user.setPassword(request.getParameter("password"));
                profileService.add(user);
                request.setAttribute("Message", Constant.ACCOUNT_CREATED);
                RequestDispatcher requestDispatcher = request
                                            .getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }    
    }

    /**
     * Updates the details of the user.
     *
     * @param request  - The request object is used 
     *                   to get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setMobileNumber(request.getParameter("mobileNumber"));
            profileService.update(user);

            if (null != profileService.update(user)) {
		
                request.setAttribute("message", "updated success");
            } else  {
                request.setAttribute("message", "not updated");
            }
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("getUserProfileDetails");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * delete the account of the user.
     *
     * @param request  - The request object is used 
     *                   to get the request parameters.
     * @param response - This is the response object that 
     *                   is used to send data back to the client.
     */
    private void delete(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            profileService.updateAccountActiveStatus(session
                                           .getAttribute("accountName").toString());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher
                                                          ("login.jsp");
            request.setAttribute("Message", Constant.ACCOUNT_DELETED);
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * get profile details of the user
     *
     * @param request  - The request object is used 
     *                   to get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */ 
    public void getUserProfileDetails(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            String accountName = session.getAttribute("accountName").toString();
            request.setAttribute("user", profileService.getUserProfileDetails(accountName));
        } catch(InstagramManagementException customException) {
            logger.error(customException.getMessage());
            response.sendRedirect("errorPage.jsp");
        }
        RequestDispatcher requestDispatcher = request
                          .getRequestDispatcher("userProfile.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Create account name for user.
     *
     * @param accountName  
     *        account name of the user.          
     * @return account name - if account name is valid
     */
    public String getAccountName(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            String accountName = request.getParameter("accountName");
            User user = profileService.searchParticularAccountName(accountName);

            if (user.getAccountName() == null) {
                return accountName;
            }
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
        return null;
    }
}