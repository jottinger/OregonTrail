/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.Player;
import java.util.Scanner;

/**
 *
 * @author Dresen_HP
 */
public class ChangePaceView extends View{
    int pace = 1;//will need to implent the player.getPace() at some point
  
    public ChangePaceView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Change Pace Menu                                 |"
                    +"\n----------------------------------------------------"
                    +"\n1 - Slow (10 miles per day)"
                    +"\n2 - Medium (15 miles per day)"
                    +"\n3 - Fast (21 miles per day)"
                    +"\n----------------------------------------------------"
                    +"\nPlease make a selection to change your pace: ");
    }

    /* public void display() {
        System.out.println("\n *** ChangePaceView called ***");
    } */
    
    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase(); //convert choice to upper case
        
        Scanner keyboard = new Scanner(System.in); //get infile for keyboard
        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        
        while(!valid) {
        choice = keyboard.nextLine();
        valid = true;
        
        try {
          int pace = Integer.parseInt(choice);
        } catch (NumberFormatException nf) {
        System.out.println("\nYou must enter a valid number. "
        + " Try again");
        valid = false;
        } 
        
        switch (choice) {
            case "1": //ford the river
                this.setPaceToSlow();
                break;
            case "2": //hire a guide
                this.setPaceToMedium();
                break;
            case "3": //save the current game
                this.setPacetoFast();
                break;
            case "Q": //quit the menu
                this.quitGame();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
                this.display();
        }
    }
        return false;
    }
    
    private void setPaceToSlow() {
        pace = 1;
        this.pace = new Player().setPace();
        
    }

    private void setPaceToMedium() {
        pace = 2;
        this.pace = new Player().setPace();
    }

    private void setPacetoFast() {
        pace = 3;
        this.pace = new Player().setPace();
    }

    private void quitGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
