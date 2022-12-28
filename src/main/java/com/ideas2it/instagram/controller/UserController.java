/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.controller;

import com.ideas2it.instagram.model.User;
import com.ideas2it.instagram.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  Creates the account for user and manipulate the user account by update,get and delete
 *  to the database via service layer.
 *
 *  @version 1.0
 *  @since 27-Dec-2022
 *  @author  Yogeshwar S
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Add the user details in database via service layer.
     *
     * @param user - the user contains user details.
     * @return user account - if account created successfully.
     */
    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService
                .create(user));
    }

    /**
     * Get all the user details
     *
     * @return list of user - active user in the database.
     */
    @GetMapping
    public ResponseEntity <List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    /**
     * Get the details of user for given id.
     *
     * @param id of the particular user.
     * @return user of given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity <User> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService
                .getById(id));
    }

    /**
     * Update the user details.
     *
     * @param user details to update.
     * @return user - if user updated successfully
     */
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService
                .update(user));
    }

    /**
     * Delete the user for given id.
     *
     * @param id - to delete the user
     * @return Success message if user deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}