package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.model.User;
import com.ideas2it.view.UserView;
import com.ideas2it.view.PostView;
import com.ideas2it.controller.ProfileController;
import com.ideas2it.controller.PostController;
import com.ideas2it.constant.Constant;
import com.ideas2it.logger.CustomLogger;

/**
 * Shows the home page, based on the user
 * option it takes to the further pages.
 *
 * @version 1.0 22-Nov-2022
 * @author  Yogeshwar S
 */
public class ProfileView {
    private PostController postController;
    private PostView postView;
    private ProfileController profileController;
    private Scanner scanner;
    private UserView userView;

    public ProfileView() {
        this.postController = new PostController();
        this.postView = new PostView();
        this.profileController = new ProfileController();
        this.scanner = new Scanner(System.in);
        this.userView = new UserView();
    }

    /**
     * Based on user choice, allow the user to login the 
     * profile menu.
     *
     * @param User user
     *         details of the user.
     */
    public void showHomeMenu(User user) {
        boolean isRunning = false;
        StringBuilder homeMenu = new StringBuilder();
        homeMenu.append("Enter 1 for profile menu")
                .append("\n Enter 2 for Exit");
        System.out.println(postController.getAllUsersPost());
        
        do {
            try {
                System.out.println(homeMenu);

                switch (scanner.nextInt()) {
                case Constant.PROFILE_MENU: 
                    showProfileMenu(user);
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
    public void showProfileMenu(User user) {   
        boolean isRunning = false;
        StringBuilder profileMenu = new StringBuilder();
        profileMenu.append("\n Enter 1 for display the user")
                   .append("\n Enter 2 for update the user")
                   .append("\n Enter 3 for search")
                   .append("\n Enter 4 for post menu")
                   .append("\n Enter 5 for home menu")
                   .append("\n Enter 6 for remove user");
        do {
            try {
                System.out.println(profileMenu);

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
                    postView.showPostMenu(user);     
                    isRunning = true;
                    break;

                case Constant.BACK_TO_HOMEMENU:
                    showHomeMenu(user);
                    isRunning = true;
                    break;

                case Constant.REMOVE:
                    deactivateAccount(user);   
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
    private void deactivateAccount(User user) {
        String accountName = user.getAccountName();
        String password = user.getPassword();

        if (profileController.deleteAccount(accountName, password)) {
            CustomLogger.info(Constant.ACCOUNT_DELETED); 
            userView.userInput();      
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
        User user = profileController.search(accountName);

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
        System.out.println(profileController.getAllUserAccount());
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
        StringBuilder updateMenu = new StringBuilder();
        User userUpdate = new User();
        updateMenu.append("\n Enter 1 for update user name")
                  .append("\n Enter 2 for update mobile number")
                  .append("\n Enter 3 for update password");

        do {
            try {
                System.out.println(updateMenu);
                scanner.skip("\r\n");     
        
                switch (scanner.nextInt()) {
                case Constant.UPDATE_USER_NAME:
                    String updateUserName;
                    updateUserName = userView.getUserName();
                    userUpdate = profileController.update(accountName, updateUserName,
                                                     Constant.UPDATE_USER_NAME);
                    isRunning = true;
                    break;

                case Constant.UPDATE_MOBILE_NUMBER:
                    long mobileNumber;
                    mobileNumber = userView.getMobileNumber(); 
                    userUpdate = profileController.update(accountName, String.valueOf(mobileNumber),
                                                     Constant.UPDATE_MOBILE_NUMBER);
                    isRunning = true;
                    break;

                case Constant.UPDATE_PASSWORD:
                    String updatePassword;
                    updatePassword = userView.getPassword();
                    userUpdate = profileController.update(accountName, updatePassword,
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