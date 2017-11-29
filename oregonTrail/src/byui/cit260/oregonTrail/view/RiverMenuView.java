/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.control.RiverControl;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.RiverControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class RiverMenuView extends View {
    private String menu;
    private String promptMessage;
    
    public RiverMenuView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| River Menu                                       |"
                    +"\n----------------------------------------------------"
                    +"\n1 - Ford the river"
                    +"\n2 - Hire a Guide for $50"
                    +"\n3 - Save game"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------");
    }
    
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            GameMenuView gameMenuView = new GameMenuView();
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName
                if (value.toUpperCase().equals("Q")) // user wants to quit 
                    gameMenuView.display();//exit the game
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }
    
    @Override
    public boolean doAction(String choice) {
        
        int number = 0;
        try {
            number = parseInt(choice);
        } catch (NumberFormatException nf) {
            System.out.println("\nYou must enter a valid number. Try again or enter Q to quit.");
            getInput();
        }
        
        switch (number) {
            case 1: {
            try {
                //ford the river
                this.fordRiver();
            } catch (InventoryControlException ex) {
                System.out.println(ex.getMessage());
            }
        }
                break;
            case 2: //hire a guide
                this.hireGuide();
                break;
            case 3: //save the current game
                this.saveGame();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
        }
        
        return false;
    }

    private void fordRiver() throws InventoryControlException {
        //ford the river
        int riverHeight = getRiverHeight();
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        double guide = inventory[InventoryType.Guide.ordinal()].getQuantityInStock();
        long currentRiverWeather = getRiverWeather();
        int success = 0;
        try {
            RiverControl.calcRiverSuccessProbability(riverHeight, guide, currentRiverWeather);
        } catch (RiverControlException me) {
            System.out.println(me.getMessage());
        }
        if (success == 1) {
            this.riverYes();
        }
    
        else if (success == 0) {
            this.riverNo(inventory);
        }
        else {
            System.out.println("\n There was an error fording the river. Try again");
            this.display();
        }

            
    }
    private void saveGame() {
        System.out.println("*** saveGame() function called ***");
    }

    private void quitGame() {
        System.out.println("*** quitGame() function called ***");
    }

    private void hireGuide() {
        GuideMenuView guideView = new GuideMenuView();
        guideView.display();
    }

    private int getRiverHeight() {

        int height = 0;
        return height;
    }

    private long getRiverWeather() {
        System.out.println("*** getRiverWeather() function called ***");
        long weather = 0;
        return weather;
    }

    private void riverYes() {
        System.out.println("\n*************************************************"
                          + "\n| Congratulations! "
                          + "\n| Your attempt to cross the river succeeded."
                          + "\n************************************************");
            GameMenuView gameMenuView = new GameMenuView();
            gameMenuView.display();
        }

    private void riverNo(InventoryItem[] inventory) throws InventoryControlException {
        double lost = 0;
        try {
            lost = InventoryControl.riverFailureRemove(inventory);
        } catch (InventoryControlException ie) {
            System.out.println(ie.getMessage());
            MainMenuView mainMenuView = new MainMenuView();
            mainMenuView.display();
        }

            System.out.println("\n*************************************************"
                          + "\n| Your attempt to cross the river failed."
                          + "\n| 20% of your inventory fell in the river."
                          + "\n| You lost " + lost + " items."
                          + "\n************************************************"
                          + "\n"
                          + "\n************************************************"
                          + "\n* Item: New Inventory Totals"
                          + "\n************************************************"
                          + "\n* Item: Quantity in Inventory, Value");
            /*String name;
            double inStock;
            int i = 0;
            for (InventoryItem item : inventory) {
                name = item.getInventoryType().name();
                inStock = item.getQuantityInStock();
                
            System.out.println("\n* " + name + ": " + inStock); 
            }*/
            String playerInventory = InventoryControl.displayInventoryQuantityPrice();
            System.out.print(playerInventory);
            System.out.println("\n* " 
                + "\n************************************************");
            this.display();
            }
        }



