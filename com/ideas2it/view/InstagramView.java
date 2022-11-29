package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.InstagramController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.view.PostView;

public class InstagramView {
    private InstagramController instagramController;
    private Scanner scanner;
    private UserView userView;

    public InstagramView() {
        this.instagramController = new InstagramController();
        this.scanner = new Scanner(System.in);
        this.userView = new User View();
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
            System.out.println(userChoice);
            switch (scanner.nextInt()) {
            case Constant.LOGIN: 
                login();
                break;

            case Constant.CREATE: 
                create()
                break;

            case Constant.EXIT:
                choice = true;
                break;

            default :
                System.out.println("Invalid input");
                break;
            }
        } while(!choice);
    }

    /**
     * Login the user 
     */
    public void login() {
        String accountName;
 	String password;	    
        System.out.println("Enter account name");
        accountName = scanner.nextLine();
        System.out.println("Enter the password");
        password = scanner.nextLine();
        User user = instagramController.login(accountName, password);
        if (null != user) {
            userView.postMenu(user);
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
    private String getAccountName() {
        String accountName; 
        boolean isValid = false;
        
        do {
            System.out.println(Constant.ACCOUNTNAME_FORMATE);
            accountName = scanner.nextLine();
            isValid = instagramController.isValidAccountName(accountName);

            if (!isValid) {  
                CustomLogger.warn("Entered wrong format try again");
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
    private String getUserName() {
        String userName; 
        boolean isValid = false;
        do {
            System.out.println(Constant.NAME_FORMATE);
            userName = scanner.nextLine();
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
    private long getMobileNumber() {
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
}