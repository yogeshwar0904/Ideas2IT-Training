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

/**
 * Shows the home page, based on the user
 * option it takes to the further pages.
 *
 * @version 1.0 22-Nov-2022
 * @author  Yogeshwar S
 */
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

    /**
     * Based on user choice, allow the user to login the 
     * profile menu.
     *
     * @param User user
     *         details of the user.
     */
    public void homeMenu(User user) {
        boolean isRunning = false;
        StringBuilder userChoice = new StringBuilder();
        userChoice.append("Enter 1 for profile menu")
                  .append("\n Enter 2 for Exit");
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
                    CustomLogger.warn(Constant.HOMEMENU_NO_FEATURES_EXIST);
                    break;
                }
            } catch (InputMismatchException exception) {
                CustomLogger.error(Constant.HOMEMENU_INVALID_OPTION);
                scanner.next();
            }
        } while(!isRunning);
    }

    /**
     * Based on user choice To add, remove, display,  
     * update and search the account of user and 
     * post their pictures.
     *
     * @param User user
     *         details of the user.
     */ 
    public void profileMenu(User user) {   
        boolean isRunning = false;
        StringBuilder userControl = new StringBuilder();
        userControl.append("\n Enter 1 for display the user")
                   .append("\n Enter 2 for update the user")
                   .append("\n Enter 3 for search")
                   .append("\n Enter 4 for post menu")
                   .append("\n Enter 5 for home menu")
                   .append("\n Enter 6 for remove user");
        do {
            try {
                System.out.println(userControl);

                switch (scanner.nextInt()) {
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

                case Constant.REMOVE:
                    deleteAccount(user);
                    isRunning = true;
                    break;

                default:
                    CustomLogger.warn(Constant.PROFILEMENU_NO_FEATURES_EXIST);
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error(Constant.PROFILEMENU_INVALID_OPTION);
                scanner.next();
            }
        } while (!isRunning);
    }

    /**
     * To deactivate the account of user. 
     *
     * @param User user
     *         details of the user.
     */
    private void deleteAccount(User user) {
        String accountName = user.getAccountName();
        String password = user.getPassword();

        if (instagramController.deleteAccount(accountName, password)) {
            CustomLogger.info(Constant.ACCOUNT_DELETED); 
            instagramView.userInput();      
        } else {
            CustomLogger.info(Constant.ACCOUNT_NOT_DELETED);
        }
    }
      
    /**
     * To search the particular user account.    
     */
    private void search() {
        System.out.println("Enter the account name you want to search");
        String accountName = scanner.next();  
        User user = instagramController.search(accountName);

        if (null == user) {
            CustomLogger.info(Constant.NO_ACCOUNT_FOUND);
        } else {
            System.out.println(user);   
        } 
    }

    /**
     * display the Account.
     */
    private void display() {
        System.out.println(instagramController.display());
    }

    /**
     * update the Account  
     *
     * @param User user
     *         details of the user.  
     */
    private void update(User user) {
        boolean isRunning = false;
        String accountName = user.getAccountName();
        StringBuilder userControl = new StringBuilder();
        User userUpdate = new User();
        userControl.append("\n Enter 1 for update user name")
                   .append("\n Enter 2 for update mobile number")
                   .append("\n Enter 3 for update password");

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
                    CustomLogger.warn(Constant.NO_FEATURES_EXIST_TO_UPDATE);
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
                CustomLogger.error(Constant.INVALID_OPTION_TO_UPDATE); 
                scanner.next();     
            }
        } while (!isRunning);
    }
}