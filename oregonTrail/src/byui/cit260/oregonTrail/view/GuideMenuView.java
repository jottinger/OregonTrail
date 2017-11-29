/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class GuideMenuView extends View {
    private String menu;
    private String promptMessage;

    public GuideMenuView() {
            super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Hire a Guide Menu                                 |"
                    +"\n----------------------------------------------------"
                    +"\nY - Hire a Guide"
                    +"\nN - Go without a Guide"
                    +"\nQ - Return to previous menu"
                    +"\n----------------------------------------------------");
    }
    
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            RiverMenuView riverMenuView = new RiverMenuView();
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName
                if (value.toUpperCase().equals("Q")) // user wants to quit 
                    riverMenuView.display();//exit the game
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }

    @Override
    public boolean doAction(String menuOption) {
         menuOption = menuOption.toUpperCase(); //convert choice to upper case
         
         switch (menuOption) {
             
            case "Y":
                try {
                 this.setRiverGuideYes();
            } catch (InventoryControlException ex) {
                 ErrorView.display(this.getClass().getName(), "Error: " + ex.getMessage());
                    }
                break;
            case "N":
                this.setRiverGuideNo();   
                break;
            default:
                ErrorView.display(this.getClass().getName(),"Error reading input: *** Invalid selection *** Try again");
            }
                 
        return false;
    }

    private void setRiverGuideYes() throws InventoryControlException {
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        double quantity = inventory[InventoryType.Guide.ordinal()].getQuantityInStock();
        if (quantity != 0) {
        this.console.println("\nYou already have a guide. You are ready to cross the river.");    
        } else {
            InventoryControl.addToInventory(InventoryType.Guide, 1);
            InventoryControl.subtractFromInventory(InventoryType.Money, 50);
            this.console.println("\nYou have hired a guide. You are ready to cross the river.");
            RiverMenuView riverMenuView = new RiverMenuView();
            riverMenuView.display();
    }}

    private void setRiverGuideNo() {
        RiverMenuView riverMenuView = new RiverMenuView();
        riverMenuView.display();
    }
}

        