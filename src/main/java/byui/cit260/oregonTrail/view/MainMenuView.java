/*
 * Comments to help understand.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Occupation;
import java.awt.Point;
import java.io.IOException;
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
                + "\n----------------------------------------------------"
                + "\n| Main Menu                                        |"
                + "\n----------------------------------------------------"
                + "\nN - Start new game"
                + "\nG - Get and start saved game"
                + "\nH - Get help on how to play the game"
                + "\nS - Save game"
                + "\nX - Enter game menu (bypasses regular game play)"
                + "\nE - End game"
                + "\nQ - Quit"
                + "\n----------------------------------------------------"
                + "\n"
                + "\n Please enter your choice:");

    }

    @Override
    public boolean doAction(String value) { //called from displayMainMenuView() in this class. menu choice passed in.
        value = value.toUpperCase(); //convert choice to upper case

        switch (value) {
            case "N":
                //Calls startNewGame() in this class. create and start a new game
                try {
                    try {
                        this.startNewGame();
                    } catch (MapControlException me) {
                        ErrorView.display(this.getClass().getName(), "Error: " + me.getMessage());
                    }
                } catch (GameControlException ge) {
                    ErrorView.display(this.getClass().getName(), "Error: " + ge.getMessage());
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
            /*case "P": // Bypasses all of start up menu for testing.
                try{
                    try { 
                this.startNewGameFast();
                    } catch (MapControlException me) {
                        ErrorView.display(this.getClass().getName(), "Error: " + me.getMessage());
                    }
                } catch (GameControlException ge) {
                    ErrorView.display(this.getClass().getName(), "Error: " + ge.getMessage());
                }
                break; */
            case "X":
                Game game = OregonTrail.getCurrentGame();
                if (game == null) {
                    try {
                        try {
                            //create a new game
                            int returnValue = GameControl.createNewGame(OregonTrail.getPlayer()); //Calls createNewGame() from GameControl and passes in player object.
                            if (returnValue < 0) { //Checks to see if player created. if unsuccessful, print error message.
                            }
                        } catch (MapControlException me) {
                            ErrorView.display(this.getClass().getName(), "Error: " + me.getMessage());
                        }
                    } catch (GameControlException ge) {
                        ErrorView.display(this.getClass().getName(), "Error: " + ge.getMessage());
                    }
                    try {
                        GameControl.setCompanionName("Bob");
                        GameControl.setCompanionName("Alice");
                        GameControl.setCompanionName("Fred");
                        GameControl.setOccupation(Occupation.Farmer);
                        try {
                            GameControl.setStartDate(61);
                        } catch (MapControlException ex) {
                            ErrorView.display(this.getClass().getName(), ex.getMessage());
                            return false;
                        }
                    } catch (GameControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }
                
                Location location;
                try {
                    location = MapControl.getCurrentLocation();
                } catch (MapControlException ex) {
                    ErrorView.display(this.getClass().getName(), ex.getMessage());
                    return false;
                }
                location.getScene().setActivityDone(true);
                this.console.println("\n****************************************************"
                        + "\n Quick Start"
                        + "\n****************************************************"
                        + "\n----------------------------------------------------"
                        + "\n| Companions                                        |"
                        + "\n----------------------------------------------------"
                        + "\nYou: " + OregonTrail.getCurrentGame().getPlayer().getName()
                        + "\nCompanion 1: " + OregonTrail.getCurrentGame().getCompanion1()
                        + "\nCompanion 2: " + OregonTrail.getCurrentGame().getCompanion2()
                        + "\nCompanion 3: " + OregonTrail.getCurrentGame().getCompanion3()
                        + "\n----------------------------------------------------"
                        + "\n| Occupation chosen: " + OregonTrail.getPlayer().getOccupation().getName()
                        + "\n----------------------------------------------------"
                        + "\n| StartDate: MARCH 1;"
                        + "\n****************************************************");
                }
                GameMenuView gameMenuView = new GameMenuView();
                gameMenuView.display();
                break;
            case "E":
                EndGameView endGameView = new EndGameView();
                endGameView.display();
            default: // Print out error message and exit loop.
                ErrorView.display(this.getClass().getName(), "Error reading input: Invalid selection *** Try again");
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
        Point coordinates = new Point(0, 0);
        ConfirmMoveView confirmView = new ConfirmMoveView(0, 0, coordinates); // creates new companionView object by calling construtor function in companionView.
        confirmView.welcomeBanner(); //calls companionView() in companionView
        confirmView.deliverNextView();
        return true; //success!*/
    }

    private boolean startNewGameFast() throws GameControlException, MapControlException {
        //create a new game
        int returnValue = GameControl.createNewGame(OregonTrail.getPlayer()); //Calls createNewGame() from GameControl and passes in player object.
        if (returnValue < 0) { //Checks to see if player created. if unsuccessful, print error message.
            throw new MapControlException("Unable to create new game. Player is NULL.");
        }
        GameControl.setCompanionName("Bob");
        GameControl.setCompanionName("Alice");
        GameControl.setCompanionName("Fred");
        GameControl.setOccupation(Occupation.Farmer);
        GameControl.setStartDate(61);

        this.console.println("\n****************************************************"
                + "\n Quick Start"
                + "\n****************************************************"
                + "\n----------------------------------------------------"
                + "\n| Companions                                        |"
                + "\n----------------------------------------------------"
                + "\nYou: " + OregonTrail.getCurrentGame().getPlayer().getName()
                + "\nCompanion 1: " + OregonTrail.getCurrentGame().getCompanion1()
                + "\nCompanion 2: " + OregonTrail.getCurrentGame().getCompanion2()
                + "\nCompanion 3: " + OregonTrail.getCurrentGame().getCompanion3()
                + "\n----------------------------------------------------"
                + "\n| Occupation chosen: " + OregonTrail.getPlayer().getOccupation().getName()
                + "\n----------------------------------------------------"
                + "\n| StartDate: MARCH 1;"
                + "\n****************************************************");

        GameMenuView gameMenuView = new GameMenuView(); // creates new companionView object by calling construtor function in companionView.
        gameMenuView.display(); //calls companionView() in companionView

        return true; //success!*/
    }

    private void startExistingGame() {
        this.console.println("\nEnter the file path to saved game.");
        String filePath = this.getGameInput();

        try {
            // start saved game
            GameControl.getSavedGame(filePath);

        } catch (Exception ex) {
            ErrorView.display("MainMenuView", ex.getMessage());
        }
        // display game menu

        try {
            SceneView sceneView = new SceneView();
            sceneView.display();
        } catch (MapControlException ex) {
            Logger.getLogger(MainMenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void displayHelpMenu() { //Called from doAction() case H in this class
        HelpMenuView helpMenuView = new HelpMenuView(); // creates new helpMenuView object by calling constructor function in HelpMenuView
        helpMenuView.display(); // calls DisplayHelpMenuView() from helpMenuView object.
        this.display(); // if user quits main menu, control returns here and displays the main menu.
    }

    private void saveGame() { // called from doOption() in this class
        this.console.println("\nEnter the file path for file where the game is to be saved.");
        String filePath = this.getGameInput();
        try {
            // save game to specified file
            GameControl.saveGame(OregonTrail.getCurrentGame(), filePath);
        } catch (Exception ex) {
            ErrorView.display("mainMenuView", ex.getMessage());
        }
        this.console.println("\nGame saved successfully.");
        
    }

    private String getGameInput() {
        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        try {
            while (!valid) {
                //loop while an invalid value is entered

                value = keyboard.readLine(); //get next line typed on keyboard and store in value
                value = value.trim(); //trim off leading and trailing blanks

                if (value.length() < 1) { //if value is blank print error message, starts loop again
                    ErrorView.display(this.getClass().getName(),
                            "\nInvalid value: value cannot be blank"
                            + "\n Enter game file path");
                    continue;
                }

                break; //end the loop
            }
        } catch (IOException ex) {
            ErrorView.display(this.getClass().getName(),
                    "Error reading input: " + ex.getMessage());
        }

        return value; //return the value entered to displayStartProgramView()
    }
}
