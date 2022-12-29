/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.instagram.model.User;
import com.ideas2it.instagram.userrepository.UserRepository;
import com.ideas2it.instagram.constant.Constant;
import com.ideas2it.instagram.exception.CustomException;

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
    public List<User> getAll() throws CustomException {
        List<User> listOfUser = userRepository.findAll();

        if (!listOfUser.isEmpty()) {
            return listOfUser;
        }
        throw  new CustomException(Constant.ACCOUNT_IS_EMPTY);
    }

    @Override
    public User getById(Long id) throws CustomException {
        return userRepository.findById(id).orElseThrow(()->
                new CustomException(Constant.NO_USER_EXIST_TO_SHOW));
    }

    @Override
    public User update(User user) throws CustomException {
        User existingUser = getById(user.getId());

        if (null == user.getUserName()) {
            user.setUserName(existingUser.getUserName());
        }

        if (null == user.getAccountName()) {
            user.setAccountName(existingUser.getAccountName());
        }

        if (null == user.getMobileNumber()) {
            user.setMobileNumber(existingUser.getMobileNumber());
        }

        if (null == user.getPassword()) {
            user.setPassword(existingUser.getPassword());
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws CustomException {
        User user = getById(id);

        if (null != user) {
            userRepository.deleteById(id);
        }
    }
}
