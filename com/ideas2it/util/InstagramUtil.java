package com.ideas2it.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;   

import com.ideas2it.constant.Constant;

/**
 * To validate the user name, account name,
 * mobileNumber and password.
 */
public class InstagramUtil {  
  
    /**
     * validate account name of user.
     * 
     * @param accountName
     *        account name of the user
     * @return true if account name is valid
     */
    public static boolean isValidAccountName(String accountName) { 
        return Pattern.matches(Constant.VALIDATE_ACCOUNTNAME, accountName);
    }
  
    /**
     * validate name of user.
     * 
     * @param name
     *        name of the user
     * @return true if name of user is valid
     */  
    public static boolean isValidName(String name) {
        return Pattern.matches(Constant.VALIDATE_NAME, name);
    }

    /**
     * validate mobile number of user.
     * 
     * @param mobileNumber
     *        name of the user
     * @return true if mobile number of user is valid
     */ 
    public static boolean isValidMobileNumber(long mobileNumber) {
        return Pattern.matches(Constant.VALIDATE_MOBILENUMBER, 
                               Long.toString(mobileNumber));
    }
 
    /**
     * validate password of user.
     * 
     * @param password
     *        password of the user
     * @return true if password of user is valid
     */  
    public static boolean isValidPassword(String password) {
        return Pattern.matches(Constant.VALIDATE_PASSWORD, password);
    }
}