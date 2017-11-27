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
 * @author jordan
 */

/*
    STUFF LEFT TO DO FOR THIS
    - display inventory and price
    - have selection for what to purchase
    - selection for how many you want to purchase
    - selection for how you want to pay (default to money for now)
    - find out price by calling PriceForOne for each inventory item - that is in the inventory control
    - Check to see if the player has enough money
    - Confirm purchase or cancel
    - if confirmed, add inventory and remove money
 */

class BarterView extends View {
    
    public BarterView() {
        super("\n"
                + "\n----------------------------------------------------"
                + "\n|Barter Menu                                       |"
                + "\n----------------------------------------------------"
                + "\n1 - Display inventory items for barter"
                + "\n2 - Display your inventory items"
                + "\n3 - Display barter help menu"
                + "\nQ - Quit to previous menu");
    }
    

    @Override
    public boolean doAction(String choice) {
       
        try {
            int choiceNumber = Integer.parseInt(choice);
            } catch (NumberFormatException nf) {
            System.out.println("\nYou must enter a valid number. "
            + " Try again");
            }
        
        switch (choice) {
            case "1": 
                displayBarterItems();
                break;
            case "2": 
                displayInventoryItems();
                break;
            case "3": 
                displayBarterHelp();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
        
        return false;
    }



    private void displayBarterItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        private void displayInventoryItems() {
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        String name;
        double inStock;
        for (InventoryItem item : inventory) {
            name = item.getInventoryType().name();
            inStock = item.getQuantityInStock();
            System.out.println("\n* " + name + ": " + inStock);
        }
        System.out.println("\n* " 
                + "\n************************************************");
            this.display();
    }

    private void displayBarterHelp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
