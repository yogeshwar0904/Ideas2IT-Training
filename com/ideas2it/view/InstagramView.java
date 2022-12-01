package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.InstagramController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.view.UserView;
import com.ideas2it.view.PostView;

public class InstagramView {
    private InstagramController instagramController;
    private Scanner scanner;

    public InstagramView() {
        this.instagramController = new InstagramController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * option for the user 
     */
    public void userInput() {
        boolean choice;
        StringBuilder userChoice = new StringBuilder();
        userChoice.append("Enter 1 for Login")
                  .append("\nEnter 2 for Create account")
                  .append("\nEnter 3 for Exit");
        choice = false;
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
                    System.out.println("Entered data not match");
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Entered data is invalid");
                scanner.next();
            }
        } while(!choice);
    }

    /**
     * Login the user 
     */
    public void login() {
        UserView userView;
        String accountName;
 	String password;
        userView = new UserView();	    
        System.out.println("Enter account name");
        accountName = scanner.next();
        System.out.println("Enter the password");
        password = scanner.next();
        User user = instagramController.login(accountName, password);

        if (null != user) {
            userView.homeMenu(user);
        } else {
            userInput();
            CustomLogger.info("No account exist");
        }
     }

    /**
     * creates the account for user.         
     */   
    private void create() {
        boolean isValid = false;
        User user = null;
        String  accountName = getAccountName(); 
        String  userName = getUserName();
        long mobileNumber = getMobileNumber();
        String  password = getPassword();     
        user = new User(accountName, userName,
                        mobileNumber, password);

        if (instagramController.create(user) != null) {
	    CustomLogger.info("Account created sucessfully"); 
            userInput();
        } else {
            CustomLogger.info("Account not created");
        }
    }

    /**
     * Create account name for user.
     *
     * @return String accountName
     *         accountName of the user
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
                if (user == null) {
                    return accountName;
                } else {
                    System.out.println("account name alredy exist");
                    isValid = false;
                }
            }  else {
                 CustomLogger.warn("Entered wrong format try again");
                 isValid = false;
            }
        } while (!isValid);
        return accountName;
    }

    /**
     * creates the first name for user.
     *
     * @return String userName
     *         name of the user
     */ 
    public String getUserName() {
        String userName; 
        boolean isValid = false;
        do {
            System.out.println(Constant.NAME_FORMATE);
            userName = scanner.next();
            isValid = instagramController.isValidName(userName);

            if (!isValid) {
                CustomLogger.warn("Entered wrong format try again");
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
        long mobileNumber; 
        boolean isValid = false;
        do {
            System.out.println(Constant.MOBILENUMBER_FORMATE);
            mobileNumber = scanner.nextLong();
            scanner.skip("\r\n");
            isValid = instagramController.isValidMobileNumber(mobileNumber);

            if (!isValid) {
                CustomLogger.warn("Entered wrong format try again");
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
        String password; 
        boolean isValid = false;
        
        do {
            System.out.println(Constant.PASSWORD_FORMATE);
            password = scanner.next();
            isValid = instagramController.isValidPassword(password);
            if (!isValid) {
                CustomLogger.warn("Entered wrong format try again");
            } 
        } while (!isValid);
        return password;
    } 
}