package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ideas2it.constant.Constant;
import com.ideas2it.dao.PostDao;
import com.ideas2it.exception.InstagramManagementException;
import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.service.InstagramService;

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
    public Post uploadPost(User user, String title, String content) 
                    throws InstagramManagementException {
        String postId;
        Post post;
        postId = UUID.randomUUID().toString();
        post = new Post(postId, title, content);
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
    public boolean delete(String postId, String userId) throws InstagramManagementException { 
        boolean isDeleted;
        isDeleted = (postDao.delete(postId, userId) > 0);
        return isDeleted;
    }

    /**
     * Gets the post based on there userId 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> displayPost(String userId) 
                      throws InstagramManagementException {
        List<Post> userPosts;
        userPosts = postDao.displayPost(userId);
        
        if (userPosts.isEmpty()) {
            throw new InstagramManagementException(Constant.ERROR_001);
        }      
        return userPosts;
    } 

    /**
     * Gets All the post
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getAllUserPost() throws InstagramManagementException {
        List<Post> listOfPost;
        listOfPost = postDao.displayAllUserPost();
        
        if (listOfPost.isEmpty()) { 
            throw new InstagramManagementException(Constant.ERROR_001);   
        }
        return listOfPost;   
    }

    /**
     * update the user post
     *
     * @param String postId 
     *        post id  of the user
     * @param int choice
     *        choice of the user
     * @return Post
     *         update the users post        
     */   
    public Post update(String postId, String updateValue,
                           int choice, String userId) throws InstagramManagementException {
        Post post = postDao.getPostId(postId);

        if (null != post) {

            switch (choice) {
            case Constant.UPDATE_POST_CONTENT:
                post.setContent(updateValue); 
                break;
            case Constant.UPDATE_POST_TITLE:
                post.setTitle(updateValue);
                break;
            default:
                break;         
            }
            return postDao.update(post, userId);
        }
        throw new InstagramManagementException(Constant.ERROR_002);
    }

    /**
     * update the user post
     *
     * @param String postId 
     *        post id  of the user
     * @return Post
     *         update the users post        
     */ 
    public Post getPostId(String postId) {
       return postDao.getPostId(postId);
    }
}