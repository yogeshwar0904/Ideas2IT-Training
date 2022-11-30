/**
 * This an Instagram application where user can 
 * create an profile and post their pictures.
 */

import com.ideas2it.view.InstagramView;

/** Call the User sugestion method to run the 
 * application.
 *
 * @version     1.0 14 Sept 2022
 * @author      Yogeshwar
 */
public class Instagram {     
    public static void main(String[] args) { 
        InstagramView instagramView = new InstagramView();
        instagramView.userInput();
    }
}