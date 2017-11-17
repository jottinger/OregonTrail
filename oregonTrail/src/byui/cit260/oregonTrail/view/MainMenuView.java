/*
 * Comments to help understand.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Game;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class MainMenuView extends View {

        
    // constructor function Called from displayNextView() in StartProgramView. 
    public MainMenuView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Main Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nN - Start new game"
                    +"\nG - Get and start saved game"
                    +"\nH - Get help on how to play the game"
                    +"\nS - Save game"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\n Please enter your choice:");
   
    } // returns control back to displayNextView() in StartProgramView

    @Override
    public boolean doAction(String value) { //called from displayMainMenuView() in this class. menu choice passed in.
        value = value.toUpperCase(); //convert choice to upper case
        
        switch (value) {
            case "N": {
                //Calls startNewGame() in this class. create and start a new game
                try{
                    try { 
                        this.startNewGame();
                    } catch (MapControlException me) {
                        System.out.println(me.getMessage());
                    }
                } catch (GameControlException ge) {
                    System.out.println(ge.getMessage());
                }
        }
                break;
            case "G": //Calls startExistingGame() in this class. get and start an existing game
                this.startExistingGame();
                break;
            case "H": //Calls displayHelpMenu() in this class. display the help menu
                this.displayHelpMenu();
                break;
            case "S": //Calls saveGame() in this class. save the current game
                this.saveGame();
                break;
            default: // Print out error message and exit loop.
                System.out.println("\n*** Invalid selection *** Try again");
                break;
        }
        
        return false; // this return will be reached only if invalid option is entered.
                      // false will be returned to displayMainMenuView() triggering repeat of do-while loop.
    }

    private boolean startNewGame() throws GameControlException, MapControlException { // Called from doAction() case N in this class.
            

            //create a new game
            int returnValue = GameControl.createNewGame(OregonTrail.getPlayer()); //Calls createNewGame() from GameControl and passes in player object.
            if (returnValue < 0) { //Checks to see if player created. if unsuccessful, print error message.
            throw new MapControlException("Unable to create new game. Player is NULL.");
        }

            CompanionView companionView = new CompanionView(); // creates new companionView object by calling construtor function in companionView.
            companionView.display(); //calls companionView() in companionView
            
            return true; //success!*/
            
        
        
  
    }

    private void startExistingGame() {
        System.out.println("*** startExistingGame() function called ***");
    }
    
    private void displayHelpMenu() { //Called from doAction() case H in this class
        HelpMenuView helpMenuView = new HelpMenuView(); // creates new helpMenuView object by calling constructor function in HelpMenuView
        helpMenuView.display(); // calls DisplayHelpMenuView() from helpMenuView object.
        this.display(); // if user quits main menu, control returns here and displays the main menu.
    }
    

        private void saveGame() { // called from doOption() in this class
        System.out.println("*** saveGame() function called ***");
    }
}
