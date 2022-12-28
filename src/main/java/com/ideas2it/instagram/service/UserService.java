/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.service;

import com.ideas2it.instagram.model.User;

import java.util.List;
/**
 *  Creates the account for user and manipulate the user account by update, get and delete
 *  to the database.
 *
 *  @version 1.0
 *  @since  27-Dec-2022
 *  @author  Yogeshwar S
 */
public interface UserService {

    /**
     * Add the user details in instagramapp via service layer.
     *
     * @param user - the user contains user details.
     * @return user account - if account created successfully.
     */
    User create(User user);

    /**
     * Get all the user details
     *
     * @return list of user - active user in the instagramapp.
     */
    List<User> getAll();

    /**
     * Update the user details.
     *
     * @param user details to update.
     * @return user - if user updated successfully
     */
    User update(User user);

    /**
     * Get the details of user for given id.
     *
     * @param id of the particular user.
     * @return user of given id.
     */
    User getById(Long id);

    /**
     * Delete the user for given id.
     *
     * @param id - to delete the user.
     */
    void delete(Long id);
}
