/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.service;

import com.ideas2it.instagram.UserRepository.UserRepository;
import com.ideas2it.instagram.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Userserviceimpl implements  user service to implement some function and simple crud operation.
 *
 *  @version 1.0
 *  @since  27-Dec-2022
 *  @author  Yogeshwar S
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        user.setDeactivated(true);
        userRepository.save(user);
    }
}
