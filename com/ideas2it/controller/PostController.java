package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;
import com.ideas2it.model.User;

/**
 * Get the information from PostView and pass 
 * to PostService
 *
 * @version     1.0 14 Sept 2022
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
     * @param String userId
     *        account id of the user
     * @param String content
     *        content of post taken
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
     * @param User user
     *        details of the user
     * @return userPosts
     *         posts of the user
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
     * Delete the post based on the postId 
     * 
     * @param  postId 
     *         id of the post
     * @return bolean 
     *         true or false based 
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
     * @return post
     *         details of post
     *          
     */   
    public Post update(String postId, String updateValue, int choice, String userId) { 
        try {
            return postService.update(postId, updateValue, choice, userId); 
        } catch (InstagramManagementException exception) {
            CustomLogger.error(exception.getMessage());
        } 
        return null; 
    } 

    public Post getPostId(String postId) {
       return postService.getPostId(postId);
    }

    /**
     * displayPost will gets all the post which are posted by the user
     * stores it in the List and prints the post
     * if the List of post is empty then it will print a No post found
     *
     * @param user - the object of the user
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