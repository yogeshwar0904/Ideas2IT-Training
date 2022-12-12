package com.ideas2it.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.service.PostService;
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
public class PostServiceImpl implements PostService {
    private PostDao postDao;

    public PostServiceImpl() {
        this.postDao = new PostDaoImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post insertPost(User user, String title, String content) 
                              throws InstagramManagementException {
        String postId;
        Post post;
        postId = UUID.randomUUID().toString();
        post = new Post(postId, title, content);
        return postDao.insertPost(user, post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateIsDeleteStatus(String postId, String userId) 
                                     throws InstagramManagementException {
        boolean isDeleted = false; 
        List <Post> userPosts = postDao.getUserPost(userId);

        for(Post post : userPosts) {
            if (post.getPostId().equals(postId)) {
                isDeleted = postDao.updateIsDeleteStatus(postId, userId);
                return isDeleted;
            }
        }

        if (false == isDeleted) {
                throw new InstagramManagementException(Constant
                                             .NO_POST_EXIST_TO_DELETE);
        }
        return isDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getUserPost(String userId) 
                                     throws InstagramManagementException {
        List<Post> userPosts = postDao.getUserPost(userId);
        
        if (userPosts.isEmpty()) {
            throw new InstagramManagementException(Constant
                                         .NOT_YET_POST_UPLOADED);
        }
        return userPosts;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAllUserPost() throws InstagramManagementException {
        List<Post> listOfPost = postDao.getAllUserPost();
        
        if (listOfPost.isEmpty()) { 
            throw new InstagramManagementException(Constant
                                         .NO_USER_POST_EXIST_TO_SHOW);   
        }
        return listOfPost;   
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public Post update(String postId, String updateValue, int choice, 
                          String userId) throws InstagramManagementException {
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
        throw new InstagramManagementException(Constant
                                     .NO_POST_EXIST_TO_UPDATE);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Post getPostId(String postId) {
       return postDao.getPostId(postId);
    }
}