package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.service.PostService;
import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;

/**
 * Based on user request it perform create  
 * post for user.
 *
 * @version     1.0 12 Oct 2022
 * @author      Yogeshwar
 */
public class PostController {
    PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    /**
     * upload the post for user
     *
     * @param user
     *        details of the user
     * @param String title
     *        title of the post
     * @param String content
     *        content of post.
     * @return post
     *        post of the user else null.
     */
    public Post uploadPost(User user, String title, String content) {
        try {
            return postService.uploadPost(user, title, content);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * display the post of the user
     *
     * @param String userId
     *        id of the user
     * @return List<Post>
     *         post of the users
     */
    public List<Post> getUserPost(String userId) {
        List<Post> posts;
        posts = null; 

        try {
            posts = postService.displayPost(userId);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return posts;
    }

    /**
     * Delete the post based on the user postId 
     * 
     * @param String userId
     *        id of the user
     * @param  postId 
     *         id of the post
     * @return boolean 
     *         true if post is deleted 
     */
    public boolean delete(String postId, String userId) {
        try {
            return postService.delete(postId, userId);
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        }
        return false;
    }

    /**
     * update the post
     *
     * @param string postId
     *        post id of user
     * @param string updateValue
     *        change detail  of user
     * @param int choice
     *        choice of user
     * @param String userId
     *        id of the user
     * @return Post post 
     *         details of post if post updated         
     */   
    public Post update(String postId, String updateValue, int choice, String userId) { 
        try {
            return postService.update(postId, updateValue, choice, userId); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return null; 
    } 


    /**
     * To get the post id if that id already exist. 
     * 
     * @param  postId 
     *         id of the post
     * @return String postId 
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
     * @return List<Post>
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