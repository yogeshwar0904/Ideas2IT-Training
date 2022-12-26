package com.ideas2it.service;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.exception.InstagramManagementException;

/**
 * perform the upload, delete, display 
 * operation of user post
 *
 * @version     1.0 10 Dec 2022
 * @author      Yogeshwar
 */
public interface PostService {

    /**
     * insert the post for user.
     *
     * @param user 
     *        details of user
     * @param title 
     *        title of user post
     * @param content 
     *        content of user post
     * @return post
     *        post of user if upload succesfully
     * @throws InstagramManagementException
     *        post not uploaded  
     */
    public Post insertPost(User user, Post post)
                              throws InstagramManagementException;
     
    /**
     * update delete status of particular post of the user
     * based on the postId
     * 
     * @param  postId 
     *         id of the post
     * @return true 
     *         if post deleted succesfully
     * @throws InstagramManagementException
     *         post not deleted 
     */
    public boolean updateIsDeleteStatus(String postId, String userId)
                                           throws InstagramManagementException;

    /**
     * Gets the post based on the user id 
     * 
     * @param userId   
     *        id of the user
     * @return userPosts 
     *        posts of the particular user
     * @throws InstagramManagementException
     *        post not exist 
     */
    public List<Post> getUserPost(User user)
                                     throws InstagramManagementException; 

    /**
     * Gets All the users post
     * 
     * @return listOfPost 
     *         posts of all user
     * @throws InstagramManagementException
     *         if post not exist 
     */
    public List<Post> getAllUserPost() throws InstagramManagementException;

    /**
     * update the post of user
     *
     * @param postId 
     *        post id  of the user
     * @param updateValue
     *        update particular data of the user post
     * @param choice 
     *        choice of the user to update
     * @param userId 
     *        id  of the user
     * @throws InstagramManagementException
     *        post not updated        
     */   
    public int update(User user, Post post) 
                      throws InstagramManagementException; 
 
    /**
     * get the id of user post
     *
     * @param postId 
     *        post id  of the user
     * @return post
     *        particular post of user         
     */ 
    public Post getPostId(String postId);   
}