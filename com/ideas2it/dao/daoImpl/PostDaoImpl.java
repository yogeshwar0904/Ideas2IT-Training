package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.model.User;
import com.ideas2it.dao.PostDao;
import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;

/**
 * To perform create, update, search and detele for the user
 *
 * @version    1.2 03 Nov 2022
 * @author     Yogeshwar
 */
public class PostDaoImpl implements PostDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Post uploadPost(User user, Post post) { 
        int count = 0;  
        Post currentPost = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ")
             .append(" post (user_id, post_id, title, content)")
             .append(" VALUES(?, ?, ?, ?);");

        try {     
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString()); 
            statement.setString(1, user.getUserId());
            statement.setString(2, post.getPostId());
            statement.setString(3, post.getTitle());
            statement.setString(4, post.getContent());
            count = statement.executeUpdate();

            if(count > 0) {
                currentPost = post;
            }
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return currentPost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> displayPost(String userId) {
        List<Post> posts = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM post")
             .append(" WHERE user_id = ? AND is_deleted = 0;");
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
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

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<Post> displayAllUserPost() {
        List<Post> posts = new ArrayList();
        String query = "SELECT * FROM post WHERE is_deleted = 0";
 
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(); 
           
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String postId, String userId) { 
        boolean isDeleted = false;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE post SET is_deleted = 1")
             .append(" WHERE post_id = ?")
             .append(" AND user_id = ?;");
       
        try {
            Connection connection = DatabaseConnection.getConnection();            
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, postId);
            statement.setString(2, userId);
            isDeleted = statement.execute();
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return !isDeleted;
    } 

    /**
     * {@inheritDoc}
     */
    @Override   
    public Post update(Post post, String userId) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE post SET  title = ?, content = ?")
             .append(" WHERE post_id = ?")
             .append(" AND user_id = ? AND is_deleted = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection
                                          .prepareStatement(query.toString());
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getPostId());
            statement.setString(4, userId);
            statement.execute();
            statement.close();
        } catch(SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage()); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return post;   
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Post getPostId(String postId) {
        Post post = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * From post WHERE post_id = ?")
             .append(" AND is_deleted = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = new Post();
                post.setPostId(resultSet.getString("post_id"));
                post.setContent(resultSet.getString("content"));
                post.setTitle(resultSet.getString("title"));
            }
            statement.close();
         } catch(SQLException sqlException) {
             CustomLogger.error(sqlException.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
        return post;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public String getUserId(String postId) {
        String userId = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT user_id From post WHERE post_id = ?")
             .append(" AND is_deleted = 0;");

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getString("user_id");
            }
            statement.close();
         } catch(SQLException sqlException) {
             CustomLogger.error(sqlException.getMessage());
         } finally {
             DatabaseConnection.closeConnection();
         }
        return userId;
    }
}
