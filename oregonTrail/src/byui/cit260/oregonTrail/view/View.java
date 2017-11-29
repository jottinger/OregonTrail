/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author hannahwilliams
 */

public abstract class View implements ViewInterface {
    
    protected String promptMessage;
    
    protected final BufferedReader keyboard = OregonTrail.getInFile();
    protected final PrintWriter console = OregonTrail.getOutFile();
     
    public View() {
    }
    
    public View(String message) {
        this.promptMessage = message;   
    }
    
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName
                if (value.toUpperCase().equals("Q")) // user wants to quit
                    return; //exit the game
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }
    
    public String getInput() { // called from displayStartProgramView() in this class.
        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        try {
        while (!valid) { 
            //loop while an invalid value is entered
            this.console.println("\n" + this.promptMessage); // print out the message asking for name stored in class instance variable.
            
            value = keyboard.readLine(); //get next line typed on keyboard and store in value
            value = value.trim(); //trim off leading and trailing blanks
            
            if (value.length() < 1) { //if value is blank print error message, starts loop again
                ErrorView.display(this.getClass().getName(),
                        "\nInvalid value: value cannot be blank");
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



