package com.ideas2it.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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
    private CustomLogger logger;

    public PostController() {
        this.postService = new PostServiceImpl();
        this.logger = new CustomLogger(PostController.class);
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
        User user = null;
        Post post = new Post();
        HttpSession session = request.getSession();

        try {
            user = (User)session.getAttribute("user");
            post.setTitle(request.getParameter("title"));
            post.setContent(request.getParameter("content"));
            postService.insertPost(user, post);
            request.setAttribute("Message", Constant.POST_UPLOADED);
            RequestDispatcher requestDispatcher = request
                                            .getRequestDispatcher("homePage.jsp");
            requestDispatcher.forward(request, response);
        } catch (InstagramManagementException customException) {
            logger.error(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                              .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("Error", Constant.POST_NOT_UPLOAD);
            requestDispatcher.forward(request, response);
        }    
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
    public void editPost(HttpServletRequest request, 
                       HttpServletResponse response) throws IOException,
                                                      ServletException {
        Post post = new Post();
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            post.setTitle(request.getParameter("title"));
            post.setContent(request.getParameter("content"));
            postService.update(user,post); 
        } catch (InstagramManagementException exception) {
            logger.error(exception.getMessage());
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
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            postService.getUserPost(user);
        } catch (InstagramManagementException exception) {
            logger.error(exception.getMessage());
        }
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
             postService.updateIsDeleteStatus(request.getParameter("postId"), request.getParameter("userId"));
        } catch (InstagramManagementException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * To get the post id if that id already exist. 
     * 
     * @param  postId 
     *         id of the post
     * @return postId 
     *         if post id already exist.
     */
    public void getPostId(HttpServletRequest request, 
                      HttpServletResponse response) throws IOException,
                                                     ServletException {
        postService.getPostId(request.getParameter("postId"));
    }

}