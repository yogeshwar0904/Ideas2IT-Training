/**
 * copyrights ideas2it.
 */
package com.ideas2it.instagram.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *  Used to get and store user details in instagramapp.
 *
 *  @version 1.0
 *  @since 27-Dec-2022
 *  @author  Yogeshwar S
 */
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    private String userName;
    private String mobileNumber;
    private String password;
    private boolean isDeactivated;
}
