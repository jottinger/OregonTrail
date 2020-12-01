/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.model.Game;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
class CompanionView extends View {
    //class instance variables



    
    public CompanionView() { // called from startNewGame() in MainMenuView
        super("\nPlease enter the name of your companion.");

}
    @Override
    public void display() { // called from startNewGame() in MainMenuView
        
        String companion;
        do {
            this.console.print(getCompanionList()); // calls getComanionList() in this class
            companion = this.getInput(); // calls getInput() from this class.
            if (companion.toUpperCase().equals("Q")) 
                return; //exit to startNewGame() in MainMenuView

            this.doAction(companion); // calls doAction() in this class
        } while (OregonTrail.getCurrentGame().getCompanion3() == "");
        this.console.print(getCompanionList());
        this.displayNextView();
        
        
    }
    
    private String getCompanionList() { // called from display() in this class
        String companion1 = OregonTrail.getCurrentGame().getCompanion1(); 
            String companion2 = OregonTrail.getCurrentGame().getCompanion2();
            String companion3 = OregonTrail.getCurrentGame().getCompanion3();
            String companionList = ("\n"
                        +"\n----------------------------------------------------"
                        +"\n| Companions                                        |"
                        +"\n----------------------------------------------------"
                        +"\nYou: " + OregonTrail.getCurrentGame().getPlayer().getName()
                        +"\nCompanion 1: " + companion1              
                        +"\nCompanion 2: " + companion2
                        +"\nCompanion 3: " + companion3
                        +"\n----------------------------------------------------");
            return companionList; // returns companionList to display()
    }
        
    @Override
    public boolean doAction(String value) { // called from display() in this class
        if (value.length() < 2) {  // checks name length and prints error
            ErrorView.display(this.getClass().getName(),"Error reading input: Invalid value:"
            + "\nThe name must be greater than one character in length");
            this.display(); // calls getInput() from this class to prompt user to re-enter name.
        }
        
        try {
            GameControl.setCompanionName(value); // calls setCompaninName() in GameControl
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
        return false; // returns false to display() in this class
    } 
    
    private void displayNextView() {
        OccupationView occupationView = new OccupationView();
        occupationView.display();
    }
}
/*GameMenuView gameMenu = new GameMenuView();
        gameMenu.displayMenu(); //calls displayMenu() from game menu object.*/