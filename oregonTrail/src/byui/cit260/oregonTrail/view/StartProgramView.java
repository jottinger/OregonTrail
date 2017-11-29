/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.model.Player;
import java.util.Scanner;
import oregonTrail.OregonTrail;

/**
 *
 * @author James
 */
public class StartProgramView extends View {
    
    public String displayBanner;

    public StartProgramView(){  // Constructor function. Called from main() in OregonTrail class. 
        //Saves message in promptMessage class instance variable.
        super("\nPlease enter your name: ");
        //calls displayBanner() which  the welcome banner when view is created
        this.displayBanner();
        
    } // returns control back to main() in OregonTrail.java

    public void displayBanner() { // called from constructor function in this class. Prints welcome message.
        this.console.println(
              "\n**************************************************************"
            + "\n*                                                            *"
            + "\n* This game takes place along the 1850’s era Oregon Trail.   *"
            + "\n* In this game, the player takes the place of one of several *" 
            + "\n* characters as he or she embarks along the trek west. Along *"
            + "\n* the way, the player is faced with several challenges and   *"
            + "\n* missions that he or she needs to compete in order to win   *"
            + "\n* the game.                                                  *"
            + "\n*                                                            *"
            + "\n* After starting a game, the user is prompted to select his  *"
            + "\n* or her character type (farmer, blacksmith, merchant).      *"
            + "\n* Each profession comes with it different starting amounts   *"
            + "\n* and ability to earn points. Then the traveler names each   *"
            + "\n* member of his or her party and purchases inventory to be   *"
            + "\n* used along the trail. After selecting the time of year to  *"
            + "\n* embark, the journey begins.                                *"
            + "\n*                                                            *"
            + "\n* Along the trail, the company is faced with many difficult  *"
            + "\n* decisions that could bring great rewards or exact great    *"
            + "\n* cost to the party. They will cross rivers, face sickness   *"
            + "\n* and famine, hunt for food, trade with natives or fellow    *"
            + "\n* travelers and interact with others at each checkpoint.     *"
            + "\n*                                                            *"
            + "\n* The game tests the mettle of even the most hardened        *"
            + "\n* adventure. Can you make it in the 1850’s travel west?      *"
            + "\n* Only time will tell.                                       *"
            + "\n*                                                            *"
            + "\n**************************************************************"
            );
    } // returns control to constructor function.


    @Override
    public boolean doAction(String value) { //called from displayStartProgramView in this class.  Player's name passed in.
        if (value.length() < 2) { // checks name length and prints error message if name is too short
            ErrorView.display(this.getClass().getName(),"Error reading input: Invalid players name:  "
                                                       + "\nThe name must be greater than one character in length");
            return false; //returns false to displayStartProgramView so loop is repeated.
        }
        
        // call createPlayer() control function from GameControl class to create player and set name.
        Player player = GameControl.createPlayer(value);
        
        if (player == null) { //Checks to see if player created. if unsuccessful, print error message.
            ErrorView.display(this.getClass().getName(), "Error creating the player.");
            return false; // if unsuccsful, returns false to displayStartProgramView so loop is repeated.
        }
        
        //Calls displayNextView from this class and passes in player object to display next view. 
        this.displayNextView(player);// 
        
        return true; //success!
    }

    private void displayNextView(Player player) { // called from doAction() in this class
        //dispaly a custom welcome message
        this.console.println("\n==========================================="
                           +"\n Welcome to the game " + player.getName()
                           +"\n We hope you have a lot of fun!"
                           +"\n==========================================="
                           );
        
        //Calls constructor function in MainMenuView to create new mainMenuView object
        MainMenuView mainMenuView = new MainMenuView();
                     
        //Calls displayMainMenuView() from mainMenuView object.
        mainMenuView.display();
        
              
    }
}

