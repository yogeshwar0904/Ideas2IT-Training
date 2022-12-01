package com.ideas2it.view;

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
        boolean choice;
        StringBuilder postControl = new StringBuilder();   
        postControl.append(" Enter 1 for add post") 
                   .append("\n Enter 2 for display post")
                   .append("\n Enter 3 for remove post")
                   .append("\n Enter 4 for update post");
        choice = false;
        do {
            try {
                System.out.println(postControl);
                switch (scanner.nextInt()) {
                case Constant.ADD_POST:
                    uploadPost(user);
                    choice = true;
                    break;

                case Constant.DISPLAY_POST:
                    displayPost(user);
                    choice = true;
                    break;

                case Constant.REMOVE_POST:
                    deletePost(user);
                    choice = true;
                    break;

                case Constant.UPDATE_POST:
                    update(user);
                    choice = true;
                    break;

                default:
                    CustomLogger.warn("Entered value is Invalid!!");
                    break;
                }
            } catch (InputMismatchException inputMismatch) {
                CustomLogger.error("Enter only Numbers");
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
        String title;
        String content;
        Post post;
        UserView userView = new UserView();
        title = getTitle();
	content = getContent();
        post = postController.uploadPost(user, title, content);

        if(null == post) {
	    System.out.println("Post not created");
        } else {
            System.out.println("Post created successfully");
            userView.profileMenu(user);
        }
    }

    /**
     * display the post
     */ 
    private void displayPost(User user) {
        List<Post> posts;
        posts = postController.getUserPost(user.getUserId()); 
        UserView userView = new UserView();  
        if (null != posts) {
            System.out.println(posts);
            userView.profileMenu(user);
        } else {
            System.out.println("No Post Uploaded yet");            
        }  
    }

    /**
     * To delete the post.
     */ 
    private void deletePost(User user) {
        String postId;
        System.out.println("Enter the post Id");
        postId = scanner.nextLine();
        UserView userView = new UserView();
        if (postController.delete(postId)) {
            System.out.println("Post is deleted");
            userView.profileMenu(user);
        } else {
            System.out.println("Something went wrong");
            userView.profileMenu(user);
        }        
    }

    /**
     * update the post    
     */
    private void update(User user) {
        StringBuilder postUpdate = new StringBuilder();
        int choice;
        System.out.println("Enter the post Id to update");
        String postId = scanner.nextLine();
        Post post = null; 
        UserView userView = new UserView();
        try {
            postUpdate.append(" Enter 1 for update post content")
                      .append("\n Enter 2 for update post tittle");

            System.out.println(postUpdate);
            scanner.skip("\r\n");     
        
            switch (scanner.nextInt()) {

            case Constant.UPDATE_POST_CONTENT:
                String updatePostContent;
                updatePostContent = getContent();
                post = postController.update(postId, updatePostContent,
                                           Constant.UPDATE_POST_CONTENT);
                break;

            case Constant.UPDATE_POST_TITLE:
                String updatePostTitle;
                updatePostTitle = getTitle();
                post = postController.update(postId, updatePostTitle,
                                                  Constant.UPDATE_POST_TITLE);
                break;

            default:
                postUpdate.delete(0, postUpdate.length() - 1);
                postUpdate.append("Entered value is Invalid!! ")
                           .append("\n enter correct option to update");
                CustomLogger.warn(postUpdate.toString());
                break;
            }

            if (postId.equals(post.getPostId())) {
                CustomLogger.info("post updated Successfully");
                userView.homeMenu(user);
            } else {
                CustomLogger.info("post not updated");
            }
 
        } catch (InputMismatchException intputMismatch) { 
            CustomLogger.error("Enter only Numbers"); 
            scanner.next();     
        }
    }

    private String getContent() {
        String content;
        System.out.println("Enter your content");
        content = scanner.next();
        return content;
    }

    private String getTitle() {
        String title;
        System.out.println("Enter your title");
        title = scanner.nextLine();
        return title;
    }

    /**
     * display the Account
     */
    private void display() {
        System.out.println(postController.display());
    }

    private boolean isPostAvailable(String id) {
        Post post;
        boolean isAvailable = false;
        post = postController.getPostId(id);

        if (null != post) {
            isAvailable = true;
        } 
        return isAvailable;
    }
}