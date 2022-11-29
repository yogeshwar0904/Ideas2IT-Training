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
             postView.postMenu(user);
        } else {
            userInput();
            CustomLogger.info("No account exist");
        }
     }

    /**
     * creates the account for user.         
     */   
    private void add() {
        boolean isValid = false;
        User user = null;
        String  accountName = getAccountName(); 
        String  userName = getUserName();
        long mobileNumber = getMobileNumber();
        String  password = getPassword();     
        user = new User(accountName, userName,
                        mobileNumber, password);

        if (instagramController.add(user) != null) {
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
}