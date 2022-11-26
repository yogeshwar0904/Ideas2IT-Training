package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ideas2it.constant.Constant;
import com.ideas2it.dao.PostDao;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.service.InstagramService;
import com.ideas2it.model.Post;
import com.ideas2it.model.User;

/**
 * perform the upload, delete, display 
 * operation of user post
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class PostService {
    private PostDao postDao;
    private InstagramService instagramService;

    public PostService() {
        this.postDao = new PostDao();
        this.instagramService = new InstagramService();
    }

    /**
     * upload the post for user
     *   
     * @param String accountName
     *        account name of the user
     * @param string postLocation
     *        location of post taken
     */
    public Post uploadPost(User user, String title, String content) throws InstagramManagementException {
        String postId = UUID.randomUUID().toString();
        Post post = new Post(postId, title, content);
        return postDao.uploadPost(user, post);
    }

    /**
     * Delete particular post of the user
     * based on the postId
     * 
     * @param  postId 
     *         id of the post
     * @return isDeleted - 
     *         true or false based on the response
     */
    public boolean delete(String postId) throws InstagramManagementException { 
        boolean isDeleted = (postDao.delete(postId) > 0) ? true :false;
        return isDeleted;
    }

    /**
     * Gets the post based on there userId 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> displayPost(User user) throws InstagramManagementException {
        List<Post> userPosts;
        userPosts = postDao.displayPost(user);
        
        if (userPosts.isEmpty()) {
            throw new InstagramManagementException(Constant.ERROR_03);
        }      
        return userPosts;
    } 
}