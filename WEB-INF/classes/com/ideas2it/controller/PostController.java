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
public class PostController extends HttpServlet {
    private PostService postService;

    public PostController() {
        this.postService = new PostServiceImpl();
    }

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to 
     *                   get the request parameters.
     * @param response - This is the response object that
     *                   is used to send data back to the client.
     */
    protected  void doPost(HttpServletRequest request,                            
                           HttpServletResponse response) throws 
                           IOException, ServletException {
        String path = request.getServletPath();

        switch (path) {
        case "/addPost":
            addPost(request, response);
            break;
  
        case "/editPost":
            editPost(request, response);
            break;

        case "/deletePost":
            delete(request, response);
            break;
        }
    }

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to 
     *                   get the request parameters.
     * @param response - This is the response object that 
     *                   is used to send data back to the client.
     */
    protected  void doGet(HttpServletRequest request,                            
                          HttpServletResponse response) throws 
                          IOException, ServletException {
        String path = request.getServletPath();

        switch (path) { 
        case "/viewPost":
            viewPost(request, response);
            break;

        case "/viewAllUserPost":
            viewAllUserPost(request, response);
            break;
        }
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
    public void addPost(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                     ServletException {
        Post post = new Post();

        try {
            post.setTitle(request.getParameter("title"));
            post.setContent(request.getParameter("content"));
            postService.insertPost(post);
            request.setAttribute("Message", Constant.POST_UPLOADED);
            RequestDispatcher requestDispatcher = request
                                            .getRequestDispatcher("homePage.jsp");
            requestDispatcher.forward(request, response);
            }
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", Constant.POST_NOT_UPLOAD);
            requestDispatcher.forward(request, response);
        }    
    }

    /**
     * display the post of the user
     *
     * @param userId
     *        id of the user
     * @return post
     *         post of the users
     */
    public void viewPost(HttpServletRequest request, 
                         HttpServletResponse response) throws IOException,
                                                       ServletException {
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
    public void delete(HttpServletRequest request, 
                       HttpServletResponse response) throws IOException,
                                                      ServletException {
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
    public void update(HttpServletRequest request, 
                       HttpServletResponse response) throws IOException,
                                                      ServletException { 
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
    public Post getPostId(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                     ServletException {
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
    public void viewAllUserPost(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                     ServletException {
        List<Post> listOfPost = null;
         
        try {
            listOfPost = postService.getAllUserPost();
        } catch (InstagramManagementException customException) {
            CustomLogger.error(customException.getMessage());
        }
        return listOfPost;
    }
}