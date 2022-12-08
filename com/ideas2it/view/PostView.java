package com.ideas2it.view;

import java.text.ParseException;
import java.lang.NullPointerException;
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
     * @param User user
     *        details of user.
     */ 
    public void showPostMenu(User user) {
        boolean choice = false;
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
                    uploadPost(user);
                    break;

                case Constant.DISPLAY_POST:
                    displayPost(user);
                    break;

                case Constant.REMOVE_POST:
                    deletePost(user);
                    break;

                case Constant.UPDATE_POST:
                    update(user);
                    break;

                case Constant.BACK_TO_PROFILEMENU:
                    profileView.showProfileMenu(user);
                    choice = true;
                    break;

                default:
                    CustomLogger.warn(Constant.NO_FEATURES_EXIST_IN_POST);
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error(Constant.INVALID_OPTION_TO_POST);
                scanner.next();
            } catch (Exception exception) {
                CustomLogger.error("exception");
            }
        } while (!choice);
    }

    /**
     * upload the post for user
     *
     * @param User user
     *        details of user.
     */
    private void uploadPost(User user) { 
        Post post = postController.uploadPost(user, getTitle(), getContent());

        if(null == post) {
	    CustomLogger.info(Constant.POST_NOT_UPLOAD);
            System.out.println(Constant.POST_NOT_UPLOAD);
        } else {
            CustomLogger.info(Constant.POST_UPLOADED);
        }
    }

    /**
     * display the post
     *
     * @param User user
     *        details of user.
     */ 
    private void displayPost(User user) {
        List<Post> posts = postController.getUserPost(user.getUserId());  

        if (null != posts) {
            System.out.println(posts);
        } else {
            CustomLogger.info(Constant.NO_POST);            
        }  
    }

    /**
     * To delete the post.
     *
     * @param User user
     *        details of user.
     */ 
    private void deletePost(User user) {
        System.out.println("Enter the post Id");
        scanner.nextLine();
        String postId = scanner.nextLine();

        if (postController.delete(postId, user.getUserId())) {
            CustomLogger.info(Constant.POST_DELETED);
        } else {
            CustomLogger.info(Constant.POST_NOT_DELETED);
        }        
    }

    /**
     * update the post 
     *
     * @param User user
     *        details of user.   
     */
    private void update(User user) {
        boolean choice = false;
        Post post = null;
        StringBuilder postUpdateMenu = new StringBuilder();
        System.out.println("Enter the post Id to update");
        String postId = scanner.nextLine();

        if (null != postController.getPostId(postId)) { 
            postUpdateMenu.append(" Enter 1 for update post content")
                      .append("\n Enter 2 for update post tittle");
           
            do {
                try {
                    System.out.println(postUpdateMenu);
                    scanner.skip("\r\n");     
        
                    switch (scanner.nextInt()) {
                    case Constant.UPDATE_POST_CONTENT:
                        scanner.nextLine(); 
                        String updatePostContent = getContent();
                        post = postController.update(postId, updatePostContent,
                                                     Constant.UPDATE_POST_CONTENT,
                                                     user.getUserId());
                        choice = true;
                        break;

                    case Constant.UPDATE_POST_TITLE:
                        String updatePostTitle = getTitle();
                        post = postController.update(postId, updatePostTitle,
                                                     Constant.UPDATE_POST_TITLE,
                                                     user.getUserId());
                        choice = true;
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
                } catch (NullPointerException exception) { 
                    CustomLogger.error(exception.getMessage());     
                }
            } while (!choice); 
        } else {
            CustomLogger.info("no post to update");
        } 
    }
    /**
     * creates content for post.
     *
     * @return String content
     *         content of post.
     */
    private String getContent() {
        System.out.println("Enter your content");
        return scanner.nextLine();
    }

    /**
     * creates title for post.
     *
     * @return String title
     *        title of post.
     */
    private String getTitle() {
        System.out.println("Enter your title");
        return scanner.nextLine();
    }
}