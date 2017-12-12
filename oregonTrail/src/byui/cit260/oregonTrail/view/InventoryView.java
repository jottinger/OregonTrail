/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import static byui.cit260.oregonTrail.control.InventoryControl.calcBarterPrice;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */

/*  STUFF LEFT TO DO FOR THIS

    The view layer class responsible for printing the report must:
    - DONE Display menu options (view inventory, print inventory to file, return to previous menu)
    - DONE Prompt for and gets the file path from the end user.
    - DONE Call a separate View Layer function to actually print the report.
    - DONE Print a success message after the report has been printed.

    The function responsible for printing the report must:
    1. DONE Create and use a Character Stream class (You can not use a Byte Stream class)
    2. DONE Catch all run time errors and call the ErrorView.display() function report the error
    3. DONE Use a for statement to iterate through all of the items in the list
    4. DONE Close the file stream
*/

public class InventoryView extends View {
    // class instance variables
    private InventoryItem inventory; // inventory list
    private String promptMessage; // enter choice prompt
    
    // constructor function called from ??? TODO: Would this be called from game menu or from startGame?
    public InventoryView() {
        super("\n"
                    +"\n---------------------------------------------------------------"
                    +"\n| Inventory Menu                                              |"
                    +"\n---------------------------------------------------------------"
                    +"\nV - View inventory items on screen"
                    +"\nP - Save current inventory items to file"
                    +"\nQ - Return to previous menu"
                    +"\n---------------------------------------------------------------"
                    +"\n Enter your selection: ");
    }
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName

                if (value.toUpperCase().equals("Q")) // user wants to quit
                    //return; //exit the game
                    try {
            SceneView sceneView = new SceneView();
            sceneView.display();
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return;
        }
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }
    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase();
        
        switch (choice) {
            case "V":
        {
            viewInventoryItems();
        }
                break;
            case "P":
        {
            try {
                printInventoryItems();
            } catch (IOException ex) {
                ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
            }
        }
               break;
        }
        
        return false;
    }
    
    public void viewInventoryItems() {
        this.console.println("hello world");
    }

    
    private void printInventoryItems() throws IOException {
        /*FileWriter outFile = null;
        String fileLocation = "current_inventory.txt";
        
        try  {
            outFile = new FileWriter(fileLocation);
            outFile.write("Here is your list of current inventory items");
            outFile.flush();
        } catch (IOException ex) {
            ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
        } finally {
            if (outFile != null) {
                try {
                outFile.close();
                } catch (IOException ex2) {
                    ErrorView.display(this.getClass().getName(), "Error reading input: " + ex2.getMessage());
                }
            }
        }*/
        PrintInventoryView printInventoryView = new PrintInventoryView();
        printInventoryView.display();
    }


}
