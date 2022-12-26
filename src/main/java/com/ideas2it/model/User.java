package com.ideas2it.model;
 
import java.util.List;

import com.ideas2it.model.Post;

/**
 * Create the account for user.
 *
 * @version     1.0 13 Sept 2022
 * @author      Yogeshwar
 */
public class User {
    private String userId;
    private String accountName;
    private String userName;
    private String mobileNumber;
    private String password;
    private List<Post> posts;
  
    public User() {}

    public User(String accountName, String userName,
                String mobileNumber, String password) {
        this.userId = userId;
        this.accountName = accountName;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;    
    } 
   
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
 
    public String getUserName() {
        return userName;   
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;   
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * To show the details of user
     * 
     * @param string object of user 
     */ 
    public String toString() {
        StringBuilder showResult = new StringBuilder();
        showResult.append("\nAccount Name = ").append(getAccountName())
                  .append("\nUser Name = ").append(getUserName())
                  .append("\nMobile Number= ").append(getMobileNumber());
        return showResult.toString();
    }
}