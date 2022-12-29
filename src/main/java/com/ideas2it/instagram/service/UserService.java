/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.service;

import java.util.List;

import com.ideas2it.instagram.model.User;
import com.ideas2it.instagram.exception.CustomException;

/**
 *  Creates the account for user and manipulate the user account by update, get and delete
 *  to the instagramapp.
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
     * @throws CustomException - if  account is empty.
     */
    List<User> getAll() throws CustomException;

    /**
     * Update the user details.
     *
     * @param user details to update.
     * @return user - if user updated successfully.
     * @throws CustomException - if no account exist to update.
     */
    User update(User user) throws CustomException;

    /**
     * Get the details of user for given id.
     *
     * @param id of the particular user.
     * @return user of given id.
     * @throws CustomException - if no account exist on given user id.
     */
    User getById(Long id) throws CustomException;

    /**
     * Delete the user for given id.
     *
     * @param id - to delete the user.
     * @throws CustomException - if no account exist to delete on given user id.
     */
    void delete(Long id) throws CustomException;
}
