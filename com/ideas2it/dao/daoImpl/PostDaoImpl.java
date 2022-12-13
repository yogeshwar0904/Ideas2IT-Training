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
    public Post insertPost(User user, Post post) {   
        Post currentPost = null;
        int numberOfRowsAffected = 0;
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
            numberOfRowsAffected = statement.executeUpdate();

            if(numberOfRowsAffected > 0) {
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
    public List<Post> getUserPost(String userId) {
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
    public List<Post> getAllUserPost() {
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
    public int updateIsDeleteStatus(String postId, String userId) { 
        int postDeactivateStatus = 0;
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
            postDeactivateStatus = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return postDeactivateStatus;
    } 

    /**
     * {@inheritDoc}
     */
    @Override   
    public int update(Post post, String userId) {
        int postUpdated = 0;
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
            postUpdated = statement.executeUpdate();
            statement.close();
        } catch(SQLException sqlException) {
            CustomLogger.error(sqlException.getMessage()); 
        } finally {
            DatabaseConnection.closeConnection();
        }
        return postUpdated;   
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