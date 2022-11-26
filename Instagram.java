/**
 * This an Instagram application where user can 
 * create an profile and post theri pictures.
 */

import com.ideas2it.view.UserView;

/** Call the User sugestion method to run the 
 * application.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class Instagram {     
    public static void main(String[] args) { 
        UserView userView = new UserView();
        userView.userInput();
    }
}