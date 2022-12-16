package com.ideas2it.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.service.PostService;
import com.ideas2it.service.serviceImpl.PostServiceImpl;
import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;

/**
 * Based on user request it perform create, update, 
 * delete, display post for user.
 *
 * @version     1.0 12 Oct 2022
 * @author      Yogeshwar
 */
public class PostController {
    PostService postService;

    public PostController() {
        this.postService = new PostServiceImpl();
    }

    /**
     * upload the post for user
     *
     * @param user
     *        details of the user
     * @param title
     *        title of the post
     * @param content
     *        content of post.
     * @return post
     *        post of the user if post created succesfully.
     */
    public Post insertPost(User user, String title, String content) {
        try {
            return postService.insertPost(user, title, content);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * display the post of the user
     *
     * @param userId
     *        id of the user
     * @return post
     *         post of the users
     */
    public List<Post> getUserPost(String userId) {
        try {
            return postService.getUserPost(userId);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * Delete the post based on the user postId 
     * 
     * @param  postId 
     *         id of the post
     * @param userId
     *        id of the user
     * @return true
     *        if post is deleted 
     */
    public boolean updateIsDeleteStatus(String postId, String userId) {
        try {
            return postService.updateIsDeleteStatus(postId, userId);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }

    /**
     * update the post
     *
     * @param postId
     *        post id of user
     * @param updateValue
     *        change detail  of user
     * @param choice
     *        choice of user
     * @param userId
     *        id of the user
     * @return post 
     *        details of post if post updated         
     */   
    public int update(String postId, String updateValue, int choice, String userId) { 
        int updateStatus  = Constant.POST_LOADING;
        try {
            updateStatus  = postService.update(postId, updateValue, choice, userId); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return updateStatus; 
    } 

    /**
     * To get the post id if that id already exist. 
     * 
     * @param  postId 
     *         id of the post
     * @return postId 
     *         if post id already exist.
     */
    public Post getPostId(String postId) {
       return postService.getPostId(postId);
    }

    /**
     * displayPost will gets all the post which are posted by the user
     * stores it in the List and prints the post
     * if the List of post is empty then it will print a no post found
     *
     * @return listOfPost
     *         list of all user post.
     */
    public List<Post> getAllUsersPost() {
        List<Post> listOfPost = null;
         
        try {
            listOfPost = postService.getAllUserPost();
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
        }
        return listOfPost;
    }
}