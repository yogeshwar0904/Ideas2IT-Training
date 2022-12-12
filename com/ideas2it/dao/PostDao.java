package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;

/**
 * To perform create, update, search and detele for the user post
 *
 * @version    1.0 09 Dec 2022
 * @author     Yogeshwar
 */
public interface PostDao {

    /**
     * insert the user post
     *
     * @param user
     *        details of user
     * @param post 
     *        post of user        
     * @return post
     *        post of user if post uploaded
     */ 
    public Post insertPost(User user, Post post);

    /**
     * get the user post
     *
     * @param userId 
     *        id of the user 
     * @return posts
     *        post of user 
     */ 
    public List<Post> getUserPost(String userId);

    /**
     * get the all user post
     *
     * @return posts
     *        post of user 
     */ 
    public List<Post> getAllUserPost();

   /**
     * update the delete status of user post
     *
     * @param postId 
     *        id of the post 
     * @param userId
     *        id of user
     * @return int noOfRowsDeleted
     *        count of rows if post deleted
     */
    public boolean updateIsDeleteStatus(String postId, String userId); 

    /**
     * update the post of user
     *
     * @param post 
     *        post of user
     * @param userId
     *        id of user        
     * @return post
     *        updated post of user
     */   
    public Post update(Post post, String userId);

    /**
     * get id of user post
     *
     * @param postId
     *        id of user post      
     * @return post
     *        post of user
     */ 
    public Post getPostId(String postId);

    /**
     * get id of the user
     *
     * @param postId 
     *        id of the post 
     * @return userId
     *        id of user 
     */ 
    public String getUserId(String postId);

}
