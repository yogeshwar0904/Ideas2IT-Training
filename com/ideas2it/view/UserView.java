package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.model.User;
import com.ideas2it.view.PostView;
import com.ideas2it.view.ProfileView;
import com.ideas2it.controller.ProfileController;
import com.ideas2it.constant.Constant;
import com.ideas2it.logger.CustomLogger;

/**
 * Shows the login option, create option to the user based on 
 * the user option it takes to the further pages.
 *
 * @version 1.0 22-Nov-2022
 * @author  Yogeshwar S
 */
public class UserView {
    private ProfileController profileController;
    private Scanner scanner;

    public UserView() {
        this.profileController = new ProfileController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Allow the user to login to their account or 
     * create new account for user. 
     */
    public void userInput() {
        boolean isRunning = false;
        StringBuilder userChoice = new StringBuilder();
        userChoice.append("Enter 1 for Login")
                  .append("\nEnter 2 for Create account")
                  .append("\nEnter 3 for Exit");

        do {
            try {
                System.out.println(userChoice);
  
                switch (scanner.nextInt()) {
                case Constant.LOGIN: 
                    login();
                    isRunning = true;
                    break;

                case Constant.CREATE: 
                    create();
                    isRunning = true;
                    break;

                case Constant.EXIT:
                    isRunning = true;
                    break;

                default :
                    CustomLogger.warn(Constant.USER_INPUT_NOT_EXIST);
                    break;
                }
            } catch (InputMismatchException exception) {
                CustomLogger.error(Constant.USER_INPUT_MISMATCH);
                scanner.next();
            }
        } while(!isRunning);
    }

    /**
     * Allow the user to Login if their account
     * alredy exist.
     */
    private void login() {
        ProfileView ProfileView = new ProfileView();
        System.out.println("Enter account name");
        String accountName = scanner.next();
        System.out.println("Enter the password");
        String password = scanner.next();
        User user = profileController.login(accountName, password);

        if (null != user) {
            ProfileView.showHomeMenu(user);
        } else {
            userInput();
            CustomLogger.info(Constant.NO_ACCOUNT_EXIST_TO_LOGIN);
        }
    }

    /**
     * Allow the user to create
     * new account.         
     */   
    private void create() {
        boolean isValid = false;     
        User user = new User(getAccountName(), getUserName(),
                             getMobileNumber(), getPassword());

        if (profileController.create(user) != null) {
	    CustomLogger.info(Constant.ACCOUNT_CREATED); 
            userInput();
        } else {
            CustomLogger.info(Constant.ACCOUNT_NOT_CREATED);
        }
    }

    /**
     * Create account name for user.
     *
     * @return String accountName
     *         accountName of the user.
     */ 
    public String getAccountName() {
        String accountName; 
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
     * @return String userName
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
     * @return long mobileNumber
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
     * @return String password
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
}