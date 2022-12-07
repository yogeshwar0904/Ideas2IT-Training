package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.InstagramController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.view.PostView;
import com.ideas2it.view.UserView;

/**
 * Shows the login option, create option to the user based on 
 * the user option it takes to the further pages.
 *
 * @version 1.0 22-Nov-2022
 * @author  Yogeshwar S
 */
public class InstagramView {
    private InstagramController instagramController;
    private Scanner scanner;

    public InstagramView() {
        this.instagramController = new InstagramController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Allow the user to login to their account or 
     * create new account for user. 
     */
    public void userInput() {
        boolean choice = false;
        StringBuilder userChoice = new StringBuilder();;
        userChoice.append("Enter 1 for Login")
                  .append("\nEnter 2 for Create account")
                  .append("\nEnter 3 for Exit");

        do {
            try {
                System.out.println(userChoice);
  
                switch (scanner.nextInt()) {
                case Constant.LOGIN: 
                    login();
                    choice = true;
                    break;

                case Constant.CREATE: 
                    create();
                    choice = true;
                    break;

                case Constant.EXIT:
                    choice = true;
                    break;

                default :
                    CustomLogger.warn(Constant.USER_INPUT_NOT_EXIST);
                    break;
                }
            } catch (InputMismatchException exception) {
                CustomLogger.error(Constant.USER_INPUT_MISMATCH);
                scanner.next();
            }
        } while(!choice);
    }

    /**
     * Allow the user to Login if their account
     * alredy exist.
     */
    private void login() {
        UserView userView = new UserView();
        System.out.println("Enter account name");
        String accountName = scanner.next();
        System.out.println("Enter the password");
        String password = scanner.next();
        User user = instagramController.login(accountName, password);

        if (null != user) {
            userView.homeMenu(user);
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

        if (instagramController.create(user) != null) {
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
            isValid = instagramController.isValidAccountName(accountName);
            user = instagramController.search(accountName);

            if (isValid) { 
                if (user.getAccountName() == null) {
                    return accountName;
                } else {
                    System.out.println(Constant.ACCOUNT_NAME_ALREDY_EXIST);
                    isValid = false;
                }
            }  else {
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
        boolean isValid;
        isValid = false;
        String userName; 

        do {
            System.out.println(Constant.NAME_FORMATE);
            userName = scanner.next();
            isValid = instagramController.isValidName(userName);

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
            isValid = instagramController.isValidMobileNumber(mobileNumber);

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
            isValid = instagramController.isValidPassword(password);

            if (!isValid) {
                CustomLogger.warn(Constant.WRONG_PASSWORD_FORMATE);
            } 
        } while (!isValid);
        return password;
    } 
}