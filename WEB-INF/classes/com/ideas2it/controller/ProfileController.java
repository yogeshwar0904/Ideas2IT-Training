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
     * @param request  - The request object is used to
     *                   get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */
    private void login(HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
                                                     ServletException {
         User user = this.getUser(request.getParameter("accountName"),
                                  request.getParameter("password"));
          
         if (null != user) {
             HttpSession session = request.getSession();
             session.setAttribute("accountName", request
                                  .getParameter("accountName"));
             response.sendRedirect("homePage.jsp");
         } else {
             request.setAttribute("Message", Constant.LOGIN_ERROR);
             RequestDispatcher requestDispatcher = request
                               .getRequestDispatcher("login.jsp");
             requestDispatcher.forward(request, response);
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
        user.setAccountName(request.getParameter("accountName"));
        user.setUserName(getAccountName(request,response));
        user.setMobileNumber(request.getParameter("mobileNumber"));
        user.setPassword(request.getParameter("password"));
        request.setAttribute("Message", Constant.ACCOUNT_CREATED);
        RequestDispatcher requestDispatcher = request
                          .getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
        profileService.add(user);    
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
            String accountName = (String)session.getAttribute("accountName");
            String userName = (String)session.getAttribute("userName");
            User user = this.searchParticularAccountName(accountName);
            user.setAccountName(accountName);
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setMobileNumber(request.getParameter("mobileNumber"));
            profileService.update(user);
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("homePage.jsp");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            request.forward(requset, response);
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
            String accountName = (String)session.getAttribute("accountName");
            String mobileNumber = (String)session.getAttribute("mobileNumber");
            profileService.updateAccountActiveStatus(accountName);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher
                                                          ("login.jsp");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", customException.getMessage());
            request.forward(requset, response);
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
    public List<User> getUserProfileDetails(HttpServletRequest request, 
                      HttpServletResponse response, String accountName) { 
        try {
            return profileService.getUserProfileDetails(accountName);
        } catch(InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * Create account name for user.
     *
     * @return accountName
     *         accountName of the user.
     */ 
    public String getAccountName(HttpServletRequest request, 
                                 HttpServletResponse response ) {
        String accountName = request.getParameter("accountName"); 
        boolean isValid = false;
        User user = null;

        do {
            System.out.println(Constant.ACCOUNTNAME_FORMATE);
            accountName = scanner.next();
            isValid = profileController.isValidAccountName(accountName);
            user = profileController.searchParticularAccountName(accountName);

            if (isValid) { 
                if (user.getAccountName() == null) {
                    return accountName;
                } else {
                    System.out.println(Constant.ACCOUNT_NAME_ALREDY_EXIST);
                    isValid = false;
                }
            } else {
                isValid = false;
                CustomLogger.warn(Constant.WRONG_ACCOUNTNAME_FORMATE);   
            }
        } while (!isValid);
        return accountName;
    }

    /**
     * creates the name for user.
     *
     * @return userName
     *         name of the user
     */ 
    public String getUserName() {
        boolean isValid = false;
        String userName; 

        do {
            System.out.println(Constant.NAME_FORMATE);
            userName = scanner.next();
            isValid = profileController.isValidName(userName);

            if (!isValid) {
                CustomLogger.warn(Constant.WRONG_USERNAME_FORMATE);
            } 
        } while (!isValid);
        return userName;
    }

    /**
     * creates mobile number for user.
     *      
     * @return mobileNumber
     *         mobileNumber of the user
     */     
    public long getMobileNumber() {
        boolean isValid = false;
        long mobileNumber; 

        do {
            System.out.println(Constant.MOBILENUMBER_FORMATE);
            mobileNumber = scanner.nextLong();
            scanner.skip("\r\n");
            isValid = profileController.isValidMobileNumber(mobileNumber);

            if (!isValid) {
                CustomLogger.warn(Constant.WRONG_MOBILENUMBER_FORMATE);
            } 
        } while (!isValid); 
        return mobileNumber;
    }

    /**
     * creates password for user.
     *
     * @return password
     *         password of the user.
     */   
    public String getPassword() {
        boolean isValid = false;
        String password;   

        do {
            System.out.println(Constant.PASSWORD_FORMATE);
            password = scanner.next();
            isValid = profileController.isValidPassword(password);

            if (!isValid) {
                CustomLogger.warn(Constant.WRONG_PASSWORD_FORMATE);
            } 
        } while (!isValid);
        return password;
    } 

    /**
     * Redirect to the register page with the warning message
     * when user register their details wrongly.
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void redirectToRegisterPage(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String message) throws IOException,
                                                       ServletException {
        request.setAttribute("Message", message);
        RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
}