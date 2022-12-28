/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.userrepository;

import com.ideas2it.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository extends predefined Jpa repository to implement some function and simple crud operation.
 *
 *  @version 1.0
 *  @since  27-Dec-2022
 *  @author  Yogeshwar S
 */
@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
}
