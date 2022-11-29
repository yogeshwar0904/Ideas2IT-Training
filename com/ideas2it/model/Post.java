package com.ideas2it.model;

import com.ideas2it.model.User;

/**
 * upload the post for user.
 *
 * @version     1.0 13 Sept 2022
 * @author      Yogeshwar
 */
public class Post {
    private String postId;
    private String content;
    private String title;
    private User user;
    public Post(String postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public Post() {}
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    } 


   public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        StringBuilder post = new StringBuilder();
        post.append("\nPostId : ").append(postId)
            .append("\nPostTitle : ").append(title)
            .append("\nPostContent : ").append(content);

        return post.toString();
    }
}