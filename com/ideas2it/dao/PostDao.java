package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.model.User;
import com.ideas2it.model.Post;

/**
 * To perform create,update,search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class PostDao {

    Connection connection;  
    String query;    
    PreparedStatement statement;

    /**
     * upload the user post
     *
     * @param UserPost userPost
     * @return boolean true if post is uploaded
     */
    public Post uploadPost(User user, Post post) { 
        int count =0;  
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
             .append("post(user_id, post_id, content, title) ")
             .append("VALUES(?,?,?,?)");
            System.out.println("query "+query);

        try {     
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString()); 
            statement.setString(1,user.getUserId());
            statement.setString(2,post.getPostId());
            statement.setString(3,post.getTitle());
            statement.setString(4,post.getContent());
            count = statement.executeUpdate(); 
            statement.close();
            if(count>0) {
return post;
              } else {
return null;}
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return post;
    }

   public List<Post> displayPost(User user) {
   List<Post> posts = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM post")
             .append(" WHERE user_id = ?");
         ResultSet resultSet;
         System.out.println(query);
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,user.getUserId());
            resultSet = statement.executeQuery();            
            while (resultSet.next()){
                Post post = new Post();
                             post.setPostId(resultSet.getString("post_id"));
                                   post.setContent(resultSet.getString("content"));
                                   post.setTitle(resultSet.getString("title"));
                posts.add(post);               
            } 
            statement.close(); 
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return posts;       
    }

    public int delete(String postId) { 
        int noOfRowsDeleted = 0;
        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement("DELETE FROM post WHERE post_id = ?");
            statement.setString(1, postId);
            noOfRowsDeleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsDeleted;
    } 
}