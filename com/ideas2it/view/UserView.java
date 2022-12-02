package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.InstagramController;
import com.ideas2it.controller.PostController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.view.InstagramView;
import com.ideas2it.view.PostView;

public class UserView {
    private InstagramController instagramController;
    private InstagramView instagramView;
    private PostController postController;
    private PostView postView;
    private Scanner scanner;

    public UserView() {
        this.instagramController = new InstagramController();
        this.instagramView = new InstagramView();
        this.postController = new PostController();
        this.postView = new PostView();
        this.scanner = new Scanner(System.in);
    }

    public void homeMenu(User user) {
        boolean isRunning;
        StringBuilder userChoice;
        isRunning = false;
        userChoice = new StringBuilder();
        userChoice.append("Enter 1 for profile menu")
                  .append("\nEnter 2 for Exit");
        System.out.println(postController.getAllUsersPost());

        do {
            try {
                System.out.println(userChoice);
                switch (scanner.nextInt()) {
                case Constant.PROFILE_MENU: 
                    profileMenu(user);
                    isRunning = true;
                    break;

                case Constant.PROFILE_EXIT:
                    isRunning = true;
                    break;

                default :
                    System.out.println("Entered data not match");
                    break;
                }
            } catch (InputMismatchException exception) {
                CustomLogger.error("Entered data is invalid");
                scanner.next();
            }
        } while(!isRunning);
    }

    /**
     * To add, remove, display, 
     * update and search the account of users.
     */ 
    public void profileMenu(User user) {   
        boolean isRunning;
        StringBuilder userControl;
        isRunning = false;
        userControl = new StringBuilder();
        userControl.append("\n Enter 1 for remove user")
                   .append("\n Enter 2 for display the user")
                   .append("\n Enter 3 for update the user")
                   .append("\n Enter 4 for search")
                   .append("\nEnter 5 for post menu")
                   .append("\n Enter 6 for home menu");

        do {
            try {
                System.out.println(userControl);
                switch (scanner.nextInt()) {
                case Constant.REMOVE:
                    deleteAccount(user);
                    isRunning = true;
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
                    isRunning = true;
                    break;

                case Constant.BACK_TO_HOMEMENU:
                    homeMenu(user);
                    isRunning = true;
                    break;

                default:
                    CustomLogger.error("Entered value is Invalid!!");
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.warn("Enter only Numbers");
                scanner.next();
            }
        } while (!isRunning);
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
     * display the Account
     */
    private void display() {
        System.out.println(instagramController.display());
    }

    /**
     * update the Account    
     */
    private void update(User user) {
        boolean isRunning;
        User userUpdate;
        String accountName;
        userUpdate = new User();
        StringBuilder userControl = new StringBuilder();
        accountName = user.getAccountName();
        userControl.append("\n Enter 1 for update user name")
                   .append("\n Enter 2 for update mobile number")
                   .append("\n Enter 3 for update password");
        isRunning = false;

        do {
            try {
                System.out.println(userControl);
                scanner.skip("\r\n");     
        
                switch (scanner.nextInt()) {
                case Constant.UPDATE_USER_NAME:
                    String updateUserName;
                    updateUserName = instagramView.getUserName();
                    userUpdate = instagramController.update(accountName, updateUserName,
                                                     Constant.UPDATE_USER_NAME);
                    isRunning = true;
                    break;

                case Constant.UPDATE_MOBILE_NUMBER:
                    long mobileNumber;
                    mobileNumber = instagramView.getMobileNumber(); 
                    userUpdate = instagramController.update(accountName, String.valueOf(mobileNumber),
                                                     Constant.UPDATE_MOBILE_NUMBER);
                    isRunning = true;
                    break;

                case Constant.UPDATE_PASSWORD:
                    String updatePassword;
                    updatePassword = instagramView.getPassword();
                    userUpdate = instagramController.update(accountName, updatePassword,
                                                     Constant.UPDATE_PASSWORD);
                    isRunning = true;
                    break;

                default:
                    userControl.delete(0, userControl.length() - 1);
                    userControl.append("Entered value is Invalid!! ")
                               .append("\n enter correct option to update");
                    CustomLogger.warn(userControl.toString());
                    break;
                }

                if (user.getPassword().equals(userUpdate.getPassword()) 
                       && user.getUserName().equals(userUpdate.getUserName())
                       && user.getMobileNumber() == userUpdate.getMobileNumber()) {
                    CustomLogger.info("account not updated");  
                } else {
                    CustomLogger.info("account updated Successfully");
                }
 
            } catch (InputMismatchException intputMismatch) {
                CustomLogger.error("Enter only Numbers"); 
                scanner.next();     
            }
        } while (!isRunning);
    }
}