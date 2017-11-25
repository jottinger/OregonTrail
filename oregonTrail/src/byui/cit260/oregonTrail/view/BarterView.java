/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.InventoryItem;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class BarterView extends View {
    // class instance variables
    private String menu; // barter menu
    private String promptMessage; // enter choice prompt
    
    // constructor funtion called from GameMenuView
    public BarterView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Barter Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nD - Display your invenotory items"
                    +"\nC - Cancel"
                    +"\n----------------------------------------------------");
    }

    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase(); //convert choice to upper case
        
        switch (choice) {
            case "D": //dsiplay current inventory
                this.displayInventoryItems();
                break;
            case "C": //hire a guide
                this.cancelBarter();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
        }
        
        return false;
    }

    private void displayInventoryItems() {
        InventoryItem[] inventoryArray = OregonTrail.getCurrentGame().getInventory();
        for (int i = 0; 1 <inventoryArray.length; i++) {
        if (i > 0) {
            System.out.println(", ");
        }
        System.out.print(inventoryArray[i]);
    }

    }

    private void cancelBarter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
