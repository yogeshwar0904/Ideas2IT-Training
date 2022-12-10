package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.dao.daoImpl.PostDaoImpl;
import com.ideas2it.dao.PostDao;
import com.ideas2it.constant.Constant;
import com.ideas2it.exception.InstagramManagementException;

/**
 * perform the upload, delete, display 
 * operation of user post
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class PostService {
    private PostDao postDao;

    public PostService() {
        this.postDao = new PostDaoImpl();
    }

    /**
     * upload the post for user.
     *
     * @param User user 
     *        details of user
     * @param String title 
     *        title of user post
     * @param String content 
     *        content of user post
     * @return Post post
     *        post of user if upload succesfully
     * @throws InstagramManagementException
     *        post not uploaded  
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
     * @return boolean true 
     *         if post deleted succesfully
     * @throws InstagramManagementException
     *         post not deleted 
     */
    public boolean delete(String postId, String userId) 
                             throws InstagramManagementException { 
       // Post post = postDao.getPostId(postId);
        String post = postDao.getUserId(postId);
       // if (null != post) {
         if (post == postId) {
            return postDao.delete(postId, userId);
        } else {
           throw new InstagramManagementException(Constant
                                        .NO_POST_EXIST_TO_DELETE);
        }
    }

    /**
     * Gets the post based on the user id 
     * 
     * @param  String userId   
     *         id of the user
     * @return List<Post> userPosts 
     *         posts of the particular user
     * @throws InstagramManagementException
     *         post not exist 
     */
    public List<Post> displayPost(String userId) 
                                     throws InstagramManagementException {
        List<Post> userPosts = postDao.displayPost(userId);
        
        if (userPosts.isEmpty()) {
            throw new InstagramManagementException(Constant
                                         .NOT_YET_POST_UPLOADED);
        }      
        return userPosts;
    } 

    /**
     * Gets All the users post
     * 
     * @return List<Post> listOfPost 
     *         posts of all user
     * @throws InstagramManagementException
     *         if post not exist 
     */
    public List<Post> getAllUserPost() throws InstagramManagementException {
        List<Post> listOfPost = postDao.displayAllUserPost();
        
        if (listOfPost.isEmpty()) { 
            throw new InstagramManagementException(Constant
                                         .NO_USER_POST_EXIST_TO_SHOW);   
        }
        return listOfPost;   
    }

    /**
     * update the post of user
     *
     * @param String postId 
     *        post id  of the user
     * @param String updateValue
     *        update particular data of the user post
     * @param int choice 
     *        choice of the user to update
     * @param String userId 
     *        id  of the user
     * @throws InstagramManagementException
     *        post not updated        
     */   
    public Post update(String postId, String updateValue, int choice, 
                          String userId) throws InstagramManagementException {
        Post post = postDao.getPostId(postId);
       //Post post = postDao.displayPost(userId);
        List<Post> posts = postDao.displayPost(userId);
       if ( null != post) {
         // if ( posts.contains(postId)) {
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
        throw new InstagramManagementException(Constant
                                     .NO_POST_EXIST_TO_UPDATE);
    }

    /**
     * get the id of user post
     *
     * @param String postId 
     *        post id  of the user
     * @return Post post
     *         particular post of user         
     */ 
    public Post getPostId(String postId) {
       return postDao.getPostId(postId);
    }
}