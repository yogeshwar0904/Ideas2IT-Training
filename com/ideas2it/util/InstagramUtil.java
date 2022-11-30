package com.ideas2it.util;

import java.util.regex.Pattern;   
import java.util.regex.Matcher;

import com.ideas2it.constant.Constant;

/**
 * To validate the user name, account name,
 * mobileNumber and password.
 */
public class InstagramUtil {  

    public static boolean isValidAccountName(String accountName) {
        return Pattern.matches(Constant.VALIDATE_ACCOUNTNAME, accountName);
    }
    
    public static boolean isValidName(String name) {
        return Pattern.matches(Constant.VALIDATE_NAME, name);
    }

    public static boolean isValidMobileNumber(long mobileNumber) {
        return Pattern.matches(Constant.VALIDATE_MOBILENUMBER, Long.toString(mobileNumber));
    }
  
    public static boolean isValidPassword(String password) {
        return Pattern.matches(Constant.VALIDATE_PASSWORD, password);
    }
}