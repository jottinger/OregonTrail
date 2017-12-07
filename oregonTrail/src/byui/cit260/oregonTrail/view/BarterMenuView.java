/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class BarterMenuView extends View {
    // class instance variables
    private String menu; // barter menu
    private String promptMessage; // enter choice prompt
    
    // constructor funtion called from GameMenuView
    public BarterMenuView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Barter Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nD - Display your inventory items"
                    +"\nB - Barter items"
                    +"\nC - Cancel"
                    +"\n----------------------------------------------------");
    }

    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase(); //convert choice to upper case
        
        switch (choice) {
            case "D": //display current inventory
                displayInventoryItems();
                break;
            case "B": //display barter items

                BarterView barterView;
        try {
            barterView = new BarterView();
            barterView.display();
        } catch (InventoryControlException ex) {
            ErrorView.display(this.getClass().getName(), "Error message: " + ex.getMessage());
        }
                

                break;
            default:
                ErrorView.display(this.getClass().getName(), "\n*** Invalid selection *** Try again");
        }
        
        return false;
    }

    private void displayInventoryItems() {
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        String name;
        double inStock;
        for (InventoryItem item : inventory) {
            name = item.getInventoryType().name();
            inStock = item.getQuantityInStock();
            this.console.println("\n* " + name + ": " + inStock);
        }
        this.console.println("\n* " 
                + "\n************************************************");
            this.display();
    }
    
}
