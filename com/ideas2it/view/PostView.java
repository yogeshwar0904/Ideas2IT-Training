package com.ideas2it.view;

import java.lang.NumberFormatException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.view.ProfileView;
import com.ideas2it.controller.PostController;
import com.ideas2it.constant.Constant;
import com.ideas2it.logger.CustomLogger;

/**
 * add the post for user
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class PostView {
    private PostController postController;
    private Scanner scanner;

    public PostView() {
        this.postController = new PostController();
        this.scanner = new Scanner(System.in); 
    }

    /**
     * Adds post in user
     * account 
     *
     * @param user
     *        details of user.
     */ 
    public void showPostMenu(User user) {
        boolean isRunning = false;
        ProfileView profileView = new ProfileView();
        StringBuilder postMenu = new StringBuilder();   
        postMenu.append(" Enter 1 for add post") 
                .append("\n Enter 2 for display post")
                .append("\n Enter 3 for delete post")
                .append("\n Enter 4 for update post")
                .append("\n Enter 5 for profile menu");
        do {
            try {
                System.out.println(postMenu);
                
                switch (Integer.parseInt(scanner.nextLine())) {
                case Constant.ADD_POST:
                    insertPost(user);
                    break;

                case Constant.DISPLAY_POST:
                    getUserPost(user);
                    break;

                case Constant.REMOVE_POST:
                    deletePost(user);
                    break;

                case Constant.UPDATE_POST:
                    update(user);
                    break;

                case Constant.BACK_TO_PROFILEMENU:
                    profileView.showProfileMenu(user);
                    isRunning = true;
                    break;

                default:
                    CustomLogger.warn(Constant.NO_FEATURES_EXIST_IN_POST);
                    break;
                }
            } catch (NumberFormatException numberFormateException) {
                CustomLogger.error(Constant.INVALID_OPTION_TO_POST);
            }
        } while (!isRunning);
    }

    /**
     * upload the post for user
     *
     * @param user
     *        details of user.
     */
    private void insertPost(User user) { 
        Post post = postController.insertPost(user, getTitle(), getContent());

        if(null == post) {
	    CustomLogger.info(Constant.POST_NOT_UPLOAD);
        } else {
            CustomLogger.info(Constant.POST_UPLOADED);
        }
    }

    /**
     * display the post
     *
     * @param user
     *        details of user.
     */ 
    private void getUserPost(User user) {
        List<Post> posts = postController.getUserPost(user.getUserId());  

        if (posts != null) {
            System.out.println(posts);
        } else {
            CustomLogger.info(Constant.NO_POST);            
        }  
    }

    /**
     * To delete the post.
     *
     * @param user
     *        details of user.
     */ 
    private void deletePost(User user) {
        System.out.println("Enter the post Id");
        String postId = scanner.nextLine();

        if (postController.updateIsDeleteStatus(postId, user.getUserId())) {
            CustomLogger.info(Constant.POST_DELETED);
        } else {
            CustomLogger.info(Constant.POST_NOT_DELETED);
        }        
    }

    /**
     * update the post 
     *
     * @param user
     *        details of user.   
     */
    private void update(User user) {
        boolean isRunning = false;
        StringBuilder postUpdateMenu = new StringBuilder();
        System.out.println("Enter the post Id to update");
        String postId = scanner.nextLine();
        List <Post> userPosts = postController.getUserPost(user.getUserId());
  
        for (Post post : userPosts) {

            if (post.getPostId().equals(postId)) {
                postUpdateMenu.append(" Enter 1 for update post content")
                              .append("\n Enter 2 for update post tittle");
           
                do {
                    try {
                        System.out.println(postUpdateMenu);     

                        switch (scanner.nextInt()) {
                        case Constant.UPDATE_POST_CONTENT:
                            scanner.nextLine();
                            String updatePostContent = getContent();
                            post = postController.update(postId, updatePostContent,
                                                      Constant.UPDATE_POST_CONTENT,
                                                      user.getUserId());
                            isRunning = true;
                            break;

                        case Constant.UPDATE_POST_TITLE:
                            scanner.nextLine();
                            String updatePostTitle = getTitle();
                            post = postController.update(postId, updatePostTitle,
                                                      Constant.UPDATE_POST_TITLE,
                                                      user.getUserId());
                            isRunning = true;
                            break;

                        default:
                            CustomLogger.warn("No features to update in post");
                            break;
                        }

                        if (postId.equals(post.getPostId())) {
                            CustomLogger.info("post updated Successfully");
                        } else {
                            CustomLogger.info("post not updated");
                        }
                    } catch (InputMismatchException inputMismatch) { 
                        CustomLogger.error(inputMismatch.getMessage());
                        scanner.nextLine();     
                    }     
                } while (!isRunning); 
            } else {
                CustomLogger.info("no post to update");
            } 
        }
    }

    /**
     * creates content for post.
     *
     * @return content
     *         content of post.
     */
    private String getContent() {
        System.out.println("Enter your content");
        return scanner.nextLine();
    }

    /**
     * creates title for post.
     *
     * @return title
     *         title of post.
     */
    private String getTitle() {
        System.out.println("Enter your title");
        return scanner.nextLine();
    }
}