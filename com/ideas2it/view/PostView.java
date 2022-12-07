package com.ideas2it.view;

import java.lang.NullPointerException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.constant.Constant;
import com.ideas2it.controller.PostController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.view.UserView;
import com.ideas2it.model.Post;
import com.ideas2it.model.User;

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
     * account. 
     */ 
    public void postMenu(User user) {
        boolean choice = false;
        UserView userView = new UserView();
        StringBuilder postControl = new StringBuilder();   
        postControl.append(" Enter 1 for add post") 
                   .append("\n Enter 2 for display post")
                   .append("\n Enter 3 for delete post")
                   .append("\n Enter 4 for update post")
                   .append("\n Enter 5 for profile menu");
        do {
            try {
                System.out.println(postControl);
                switch (scanner.nextInt()) {
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
                    userView.profileMenu(user);
                    choice = true;
                    break;

                default:
                    CustomLogger.warn(Constant.NO_FEATURES_EXIST_IN_POST);
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error(Constant.INVALID_OPTION_TO_POST);
                scanner.next();
            }
        } while (!choice);
    }

    /**
     * upload the post for user
     *
     * @param String account name
     *        account name of user.
     */
    private void uploadPost(User user) { 
        Post post = postController.uploadPost(user, getTitle(), getContent());

        if(null == post) {
	    System.out.println("Post not created");
        } else {
            System.out.println("Post created successfully");
        }
    }

    /**
     * display the post
     */ 
    private void displayPost(User user) {
        List<Post> posts = postController.getUserPost(user.getUserId());  
        if (null != posts) {
            System.out.println(posts);
        } else {
            System.out.println("No Post Uploaded yet");            
        }  
    }

    /**
     * To delete the post.
     */ 
    private void deletePost(User user) {
        System.out.println("Enter the post Id");
        scanner.nextLine();
        String postId = scanner.nextLine();

        if (postController.delete(postId, user.getUserId())) {
            System.out.println("Post is deleted");
        } else {
            System.out.println("No post found on that Id to delete");
        }        
    }

    /**
     * update the post    
     */
    private void update(User user) {
        boolean choice = false;
        Post post = null;
        StringBuilder postUpdate = new StringBuilder();
        System.out.println("Enter the post Id to update");
        String postId = scanner.next();

        if (null != postController.getPostId(postId)) { 
            postUpdate.append(" Enter 1 for update post content")
                      .append("\n Enter 2 for update post tittle");

            do {
                try {
                    System.out.println(postUpdate);
                    scanner.skip("\r\n");     
        
                    switch (scanner.nextInt()) {
                    case Constant.UPDATE_POST_CONTENT:
                        String updatePostContent;
                        scanner.nextLine(); 
                        updatePostContent = getContent();
                        post = postController.update(postId, updatePostContent,
                                                 Constant.UPDATE_POST_CONTENT,user.getUserId());
                        choice = true;
                        break;

                    case Constant.UPDATE_POST_TITLE:
                        String updatePostTitle;
                        updatePostTitle = getTitle();
                        post = postController.update(postId,updatePostTitle,
                                                 Constant.UPDATE_POST_TITLE,user.getUserId());
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
                } catch (InputMismatchException intputMismatch) { 
                    CustomLogger.error(inputMismatch.getMessage()); 
                    scanner.next();    
                } catch (NullPointerException exception) { 
                    CustomLogger.error(exception.getMessage());     
                }
            } while (!choice); 
        } else {
            System.out.println("no post to update");
        } 
    }

    private String getContent() {
        System.out.println("Enter your content");
        return scanner.nextLine();
    }

    private String getTitle() {
        System.out.println("Enter your title");
        return scanner.nextLine();
    }
}