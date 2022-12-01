package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.InstagramController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.view.PostView;
import com.ideas2it.view.InstagramView;

public class UserView {
    private InstagramController instagramController;
    private Scanner scanner;
    private PostView postView;
    private InstagramView instagramView;

    public UserView() {
        this.instagramController = new InstagramController();
        this.scanner = new Scanner(System.in);
        this.postView = new PostView();
        this.instagramView = new InstagramView();
    }

    public void homeMenu(User user) {
        //dispalyPost(user);
        boolean choice;
        StringBuilder userChoice = new StringBuilder();
        userChoice.append("Enter 1 for profile menu")
                  .append("\nEnter 2 for Exit");
        choice = false;
        do {
            try {
                System.out.println(userChoice);
                switch (scanner.nextInt()) {
                case Constant.PROFILE_MENU: 
                    profileMenu(user);
                    choice = true;
                    break;

                case Constant.PROFILE_EXIT:
                    choice = true;
                    break;

                default :
                    System.out.println("Entered data not match");
                    break;
                }
            } catch (InputMismatchException exception) {
                CustomLogger.error("Entered data is invalid");
                scanner.next();
            }
        } while(!choice);
    }

    /**
     * Get the sugestion from the 
     * user to add, remove, display, 
     * update and search the account.
     */ 
    public void profileMenu(User user) {   
        boolean choice;
        StringBuilder userControl = new StringBuilder();
        userControl.append("\n Enter 1 for remove user")
                   .append("\n Enter 2 for display the user")
                   .append("\n Enter 3 for update the user")
                   .append("\n Enter 4 for search")
                   .append("\nEnter 5 for post menu")
                   .append("\n Enter 6 for home menu");
        choice = false;
        do {
            try {
                System.out.println(userControl);
                switch (scanner.nextInt()) {
                case Constant.REMOVE:
                    deleteAccount(user);
                    choice = true;
                    break;

                case Constant.DISPLAY:
                    display();
                    break;

                case Constant.UPDATE:
                    update(user);
                    break;

                case Constant.SEARCH:
                    search();
                    break;

                case Constant.POST_MENU: 
                    postView.postMenu(user);
                    choice = true;
                    break;

                case Constant.BACK_TO_HOMEMENU:
                    homeMenu(user);
                    choice = true;
                    break;

                default:
                    CustomLogger.warn("Entered value is Invalid!!");
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error("Enter only Numbers");
                scanner.next();
            }
        } while (!choice);
    }

    /**
     * remove the Account 
     */
    private void deleteAccount(User user) {
        String accountName;
        String password;
        accountName = user.getAccountName();
        password = user.getPassword();

        if (instagramController.deleteAccount(accountName, password)) {
            CustomLogger.info("Account deleted successfully"); 
            instagramView.userInput();      
        } else {
            CustomLogger.warn("Entered invalid data");
        }
    }
      
    /**
     * search the Account    
     */
    private void search() {
        String accountName;
        CustomLogger.info("Enter the account name you want to search");
        accountName = scanner.next();  
        User user = instagramController.search(accountName);

        if (null == user) {
            CustomLogger.info("No account found");
        } else {
            System.out.println(user);   
        } 
    }

    /**
     * displayPost will gets all the post which are posted by the user
     * stores it in the List and prints the post
     * if the List of post is empty then it will print a No post found
     *
     * @param user - the object of the user
     * 

    /**
     * display the Account
     */
    private void display() {
        System.out.println(instagramController.display());
    }

    /**
     * update the Account    
     */
    private void update(User user) {
        String accountName;
        StringBuilder userControl = new StringBuilder();
        int choice;
        accountName = user.getAccountName();
        userControl.append("\n Enter 1 for update user name")
                   .append("\n Enter 2 for update mobile number")
                   .append("\n Enter 3 for update password");
        try {
            System.out.println(userControl);
            choice = scanner.nextInt();
            scanner.skip("\r\n");     
        
            switch (choice) {
            case Constant.UPDATE_USER_NAME:
                String updateUserName;
                updateUserName = instagramView.getUserName();
                user = instagramController.update(accountName, updateUserName,
                                           Constant.UPDATE_USER_NAME);
                break;

            case Constant.UPDATE_MOBILE_NUMBER:
                long mobileNumber;
                mobileNumber = instagramView.getMobileNumber(); 
                user = instagramController.update(accountName, String.valueOf(mobileNumber),
                                           Constant.UPDATE_MOBILE_NUMBER);
                break;

            case Constant.UPDATE_PASSWORD:
                String updatePassword;
                updatePassword = instagramView.getPassword();
                user = instagramController.update(accountName, updatePassword,
                                           Constant.UPDATE_PASSWORD);
                break;

            default:
                userControl.delete(0, userControl.length() - 1);
                userControl.append("Entered value is Invalid!! ")
                           .append("\n enter correct option to update");
                CustomLogger.warn(userControl.toString());
                break;
            }

            if (accountName.equals(user.getAccountName())) {
                CustomLogger.info("account updated Successfully");
            } else {
                CustomLogger.info("account not updated");
            }
 
        } catch (InputMismatchException intputMismatch) {
            CustomLogger.error("Enter only Numbers"); 
            scanner.next();     
        }
    }
}