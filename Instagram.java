/**
 * This an Instagram application where user can 
 * create an Account and post their pictures and
 * view the others post.
 */

import com.ideas2it.view.UserView;

/** 
 * Call the User suggestion method to run the 
 * application.
 *
 * @version     2.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class Instagram {     
    public static void main(String[] args) { 
        UserView userView = new UserView();
        userView.userInput();
    }
}  