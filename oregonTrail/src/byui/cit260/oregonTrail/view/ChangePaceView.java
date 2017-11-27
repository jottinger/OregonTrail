/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.Player;
import java.util.Scanner;
import oregonTrail.OregonTrail;

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
                    +"\nQ - Quit to previous menu"
                    +"\n----------------------------------------------------"
                    +"\nPlease make a selection to change your pace: ");
    }

    /* public void display() {
        System.out.println("\n *** ChangePaceView called ***");
    } */
    
    
    @Override
    public boolean doAction(String choice) {
        int pace = 0;
        
        try {
            pace = Integer.parseInt(choice);
            } catch (NumberFormatException nf) {
            System.out.println("\nYou must enter a valid number. "
            + " Try again");
            }
        
        switch (choice) {
            case "1": 
                setPaceToSlow(pace);
                break;
            case "2": 
                setPaceToMedium(pace);
                break;
            case "3": 
                setPacetoFast(pace);
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
        
        return false;
    }

    private void setPaceToSlow(int pace) {
        OregonTrail.getCurrentGame().getPlayer().setPace(pace);
        displayNextView(pace);
    }

    private void setPaceToMedium(int pace) {
        OregonTrail.getCurrentGame().getPlayer().setPace(pace);
        displayNextView(pace);
    }

    private void setPacetoFast(int pace) {
        OregonTrail.getCurrentGame().getPlayer().setPace(pace);
        displayNextView(pace);
    }

    private void displayNextView(int pace) {
        System.out.println("Your pace has changed to " + pace + ". ");
        GameMenuView gameMenuView = new GameMenuView();
        gameMenuView.display();
    }
}
