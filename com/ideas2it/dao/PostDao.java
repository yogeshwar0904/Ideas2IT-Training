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
     * upload the user post
     *
     * @param User user
     *        user details of user
     * @param Post post 
     *        post of user        
     * @return Post post
     *        post of user if post uploaded
     */ 
    public Post uploadPost(User user, Post post);

    /**
     * Display the user post
     *
     * @param String userId 
     *        id of the user 
     * @return List<Post> posts
     *        post of user 
     */ 
    public List<Post> displayPost(String userId);

    /**
     * Display the all user post
     *
     * @return List<Post> posts
     *        post of user 
     */ 
    public List<Post> displayAllUserPost();

   /**
     * delete the post of user
     *
     * @param String postId 
     *        id of the post 
     * @param String userId
     *        id of user
     * @return int noOfRowsDeleted
     *        count of rows if post deleted
     */
    public boolean delete(String postId, String userId); 

    /**
     * update the post of user
     *
     * @param Post post 
     *        post of user
     * @param String userId
     *        id of user        
     * @return Post post
     *         updated post of user
     */   
    public Post update(Post post, String userId);

    /**
     * get id of user post
     *
     * @param String postId
     *        id of user post      
     * @return Post post
     *        post of user
     */ 
    public Post getPostId(String postId);

    /**
     * get id of the user
     *
     * @param String postId 
     *        id of the post 
     * @return String userId
     *        id of user 
     */ 
    public String getUserId(String postId);

}
