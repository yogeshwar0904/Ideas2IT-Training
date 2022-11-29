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

    public static final String VALIDATE_ACCOUNTNAME = "^[A-Za-z]\\w{5,29}$";
    public static final String VALIDATE_NAME = ("[a-zA-Z]{4,}+$");
    public static final String VALIDATE_MOBILENUMBER = ("^[6-9]{1}[0-9]{9}");
    public static final String VALIDATE_PASSWORD = "^[a-zA-Z0-9]{4,}[@$&*]{1,}[0-9]{1,3}";
    public static final String VALIDATE_USERINPUT = "^[1-3]\d*$";

    public static final String ACCOUNTNAME_FORMATE = "Create the Account Name (Must be start with charecter minimum five,Continue with numbers)";
    public static final String NAME_FORMATE = "Enter your name (Must start with charecter minimum four)";
    public static final String MOBILENUMBER_FORMATE = "Enter the Mobile Number (Must be start  from six total ten digits)";
    public static final String PASSWORD_FORMATE = "Create the Password (Must contain 7 characters including 5 Letters, one Special character and number)";

    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int DISPLAY = 3;
    public static final int UPDATE = 4;
    public static final int SEARCH = 5;
    public static final int POST_LOGIN = 6;

    public static final int UPDATE_ACCOUNT_NAME = 1;
    public static final int UPDATE_USER_NAME = 2;
    public static final int UPDATE_MOBILE_NUMBER = 3;
    public static final int UPDATE_PASSWORD = 4; 
    public static final int EXIT = 5; 

    public static final int ADD_POST = 1;
    public static final int DISPLAY_POST = 2;
    public static final int  REMOVE_POST = 3;
    public static final int  UPDATE_POST = 4;

    public static final int UPDATE_POST_CONTENT = 1;  
    public static final int UPDATE_POST_TITLE = 2; 

    public static final String ERROR_01 = "No user exist";
    public static final String ERROR_02 = "No account found to update";
    public static final String ERROR_03 = "No account exist to delete";
    public static final String ERROR_04 = "No account found";
    public static final String ERROR_05 = "No user exist to login";    

    public static final String ERROR_001 = "No post to display";
    public static final String ERROR_002 = "No post exist to update";
    public static final String ERROR_003 = "No post found to display";
}