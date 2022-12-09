package com.ideas2it.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
/**
 * Keep constant value to run the
 * particular features.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */ 
public class Constant {
    public static final int LOGIN = 1;
    public static final int CREATE = 2;
    public static final int EXIT = 3;

    public static final int PROFILE_MENU  = 1;
    public static final int PROFILE_EXIT = 2;

    public static final String VALIDATE_ACCOUNTNAME = ("^[A-Za-z]\\w{5,29}$");
    public static final String VALIDATE_NAME = ("[a-zA-Z]{4,}+$");
    public static final String VALIDATE_MOBILENUMBER = ("^[6-9]{1}[0-9]{9}");
    public static final String VALIDATE_PASSWORD = ("^[a-zA-Z0-9]{4,}[@$&*]{1,}[0-9]{1,3}");

    public static final String ACCOUNTNAME_FORMATE = "Create the Account Name (Start with minimum '5' char,Continue with numbers)";
    public static final String NAME_FORMATE = "Enter your name (Start with charecter minimum four)";
    public static final String MOBILENUMBER_FORMATE = "Enter the Mobile Number (Start  from six total ten digits)";
    public static final String PASSWORD_FORMATE = "Create the Password (contain 7 characters '5' Letters, one Special character and number)";

    public static final int DISPLAY = 1;
    public static final int UPDATE = 2;
    public static final int SEARCH = 3;
    public static final int POST_MENU = 4;
    public static final int BACK_TO_HOMEMENU = 5;
    public static final int REMOVE = 6;

    public static final int UPDATE_USER_NAME = 1;
    public static final int UPDATE_MOBILE_NUMBER = 2;
    public static final int UPDATE_PASSWORD = 3;

    public static final int ADD_POST = 1;
    public static final int DISPLAY_POST = 2;
    public static final int REMOVE_POST = 3;
    public static final int UPDATE_POST= 4;
    public static final int BACK_TO_PROFILEMENU = 5;  

    public static final int UPDATE_POST_CONTENT = 1;  
    public static final int UPDATE_POST_TITLE = 2; 

    public static final String URL = "jdbc:mysql://localhost:3306/instagram";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Printf";

    public static final String NO_ACCOUNT_EXIST_TO_SEARCH = "No user exist to search";
    public static final String UNABLE_TO_UPDATE = "No account found to update";
    public static final String NO_ACCOUNT_EXIST_TO_DELETE = "No account exist to delete";
    public static final String NO_ACCOUNT_EXIST_TO_SHOW = "No account found to show";    

    public static final String NOT_YET_POST_UPLOADED = "Not yet post uploaded";
    public static final String NO_USER_POST_EXIST_TO_SHOW = "No user post to display";
    public static final String NO_POST_EXIST_TO_UPDATE = "No post exist to update";

    public static final String USER_INPUT_MISMATCH = "Entered input data is invalid";
    public static final String USER_INPUT_NOT_EXIST = "Entered input not exist";
    public static final String NO_ACCOUNT_EXIST_TO_LOGIN = "No account is exist to login";
    public static final String ACCOUNT_CREATED = "Account created sucessfully";
    public static final String ACCOUNT_NOT_CREATED = "Account not created";

    public static final String ACCOUNT_NAME_ALREDY_EXIST = "Entered account name alredy exist try with another name";
    public static final String WRONG_ACCOUNTNAME_FORMATE = "Entered wrong format account name try again";
    public static final String WRONG_USERNAME_FORMATE = "Entered wrong format user name try again";
    public static final String WRONG_MOBILENUMBER_FORMATE = "Entered wrong format mobile number try again";
    public static final String WRONG_PASSWORD_FORMATE = "Entered wrong format password try again";

    public static final String HOMEMENU_NO_FEATURES_EXIST = "Entered input data have no features in home page";
    public static final String HOMEMENU_INVALID_OPTION = "Entered input not valid for home page";

    public static final String PROFILEMENU_NO_FEATURES_EXIST = "Entered input data have no features in profile page";
    public static final String PROFILEMENU_INVALID_OPTION = "Entered input not valid for profile page";

    public static final String ACCOUNT_DELETED = "Account deleted successfully";
    public static final String ACCOUNT_NOT_DELETED = "Account not deleted";

    public static final String NO_ACCOUNT_FOUND = "No Account is found in given account name";

    public static final String NO_FEATURES_EXIST_TO_UPDATE = "Entered input data have no features in profile page";
    public static final String INVALID_OPTION_TO_UPDATE = "Entered input not valid to update";

    public static final String NO_FEATURES_EXIST_IN_POST = "Entered input data have no features in post";
    public static final String INVALID_OPTION_TO_POST = "Entered input not valid to post";

    public static final String POST_UPLOADED = "Post created successfully";
    public static final String POST_NOT_UPLOAD = "Post not created";
    public static final String POST_DELETED = " post deleted successfully";
    public static final String POST_NOT_DELETED = "post not deleted";
    public static final String NO_POST = "No Post Uploaded yet";

























}