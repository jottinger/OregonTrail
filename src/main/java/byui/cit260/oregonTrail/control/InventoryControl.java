
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.control;

import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.Player;
import java.util.Arrays;

import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class InventoryControl {
    


    /* Gets the player's inventory from the current game. 
    * Stores it in inventory to make it available to the controller to manipulate.
    */
    public static InventoryItem[] getItemDatabase() throws InventoryControlException {  
        InventoryItem[] inventory = new InventoryItem[8]; 
        inventory = OregonTrail.getCurrentGame().getInventory(); 
        return inventory;
    }
    
    
    
    /* Gets one item from the player's inventory. 
    * return type will be an InventoryItem. 
    * parameter is the name of the inventory item to be gotten. (needs to be one of InventoryType class list). 
    * variable items is initialized. The datatype is Map with InventoryType InventoryItem key value pair. 
    * item variable is filled with the quantity in stock of the item requested. 
    * Information is validated to make sure not null. 
    * The variable item is returned.*/
    
    public static InventoryItem getItem(InventoryType type) throws InventoryControlException {
        InventoryItem[] items = getItemDatabase();
        
        InventoryItem item = items[type.ordinal()];
        return item;
    }
   
    
    /* Adds 1+ quantity of items to player's inventory.
    * New item variable is created and filled with the item from the player's inventory according to type.
    * The getQuantityInStock method is called for the item and the new quantity is added to it.
    * The total is stored in the item with the setQuantityInStock method.*/
    
    public static void addToInventory(InventoryType type, int quantity) throws InventoryControlException {
        
       InventoryItem item = getItem(type);
       item.setQuantityInStock(item.getQuantityInStock() + quantity);  
    }
    
    
    
    /* Removes 1+ quantity from player's inventory.
    * Public so can be accessed from hunt and game control. Void because it won't return anything. Parameters are 
    * the type from InventoryType class and the quantity of items to be removed.
    * A new item variable is created with datatype of InventoryType class and filled with item from player's inventory.*/
    public static void subtractFromInventory(InventoryType type, double quantity) throws InventoryControlException {
       InventoryItem item = getItem(type); 
       item.setQuantityInStock(item.getQuantityInStock() - quantity);
    }
    
    
    public static double random() throws InventoryControlException { // generate random number for barter success rate.
        return Math.random();
    }
    
    public static String displayInventoryQuantityPrice() throws InventoryControlException {
        
        
        String output = "";
        String name;
        double inStock;
        double price;
        double value;
        int i = 0;
        InventoryItem[] inventory = getItemDatabase();
        if (inventory == null) 
            throw new InventoryControlException("Can not display inventory because player inventory is null. "
                    + "Please start new game to fix problem.");
        for (InventoryItem item : inventory) {
                name = item.getInventoryType().name();
                inStock = item.getQuantityInStock();
                price = item.getInventoryType().getCost();
                price = calcBarterPrice(item.getInventoryType(), InventoryType.Money);
                value = inStock * price;
                output += "\n* " + i + " - " + name + ": Quantity owned: " + inStock + ", Price: $" + price ;
                i++;
        }
        return output;
        
     
    
    }


    public static int barter(InventoryType owned, InventoryType desired, int desiredQuantity) throws InventoryControlException {
        // validate input
        if (owned == null || desired == null) {
            throw new InventoryControlException("Can not calculate price because item type is null. Please enter a valid type.");
        }
        if (desiredQuantity < 0) {
            throw new InventoryControlException("Can not calculate price because desiredQuantity is less than 0. Please enter a positive number.");
        }
        // get desired item and owned item information for player's inventory.
        InventoryItem itemDesired = getItem(desired);
        InventoryItem itemOwned = getItem(owned);
        // get cost from inventoryType for both itemDesired and itemOwned

        int costDesired = desired.getCost(); 
        int costOwned = owned.getCost();
        
        // get percentComplete of player's game.
        double percentComplete = OregonTrail.getCurrentGame().getPercentComplete();
                
            

        double success = random();
        if (owned == InventoryType.Money) {
            success = 100;
        }
        if (success < percentComplete * .5) {
            return 1; // 1 will display message "No one was willing to trade."
        }

            
        // call calcBarterPrice function to figure cost of purchase.
        int costForOne = calcBarterPrice(desired, owned);
        int price = costForOne * desiredQuantity;
        double quantityOwned = itemOwned.getQuantityInStock();
        
        // test to see if player has enough of item to trade. 
        if (itemOwned.getQuantityInStock() < price) {
             
            return 2; // 2 will display message "You do not have enough " + owned + " to complete the transaction.";
        } else {
        // add desired item to inventory and remove desired item from inventory.
        addToInventory(desired, desiredQuantity);
        subtractFromInventory(owned, price);
        return 3; // 3 will display message "Transaction successful.";
        }
    }
    
    //calculate the price of one item for barter or purchase
    public static int calcBarterPrice(InventoryType get, InventoryType give) throws InventoryControlException {
        
        int costForOne = 0;
        // determine barterCoefficient Buy=1, Barter=2 
        int barterCoefficient = 1;
        // If buying, the item will cost 1+ percentcomplete more than base price of item.
        
        

        if (give != InventoryType.Money) {
            //If trading for goods, the item will cost 2 + percentComplete more than base price of item.
            barterCoefficient = 2;
        }
            /* If trading for goods, there will be less chance of the trade happening 
            * the farher along the trail you are. A random number is generated, and compared
            * with the percent complete divided by 2. If the random number is less than half the
            * percentComplete, then the trade will be declined.*/
       int costDesired = get.getCost(); 
        int costOwned = give.getCost();

        double percentComplete = OregonTrail.getCurrentGame().getPercentComplete();
       // calculate price for one item
       int ratio = costDesired / costOwned;
       double scarcity = barterCoefficient + percentComplete;
       double price = ratio * scarcity;
       costForOne = (int) Math.ceil(price);
       if (costForOne < 1) {
           costForOne = 1;
       }

       // return price for one item
       return costForOne;
       
    }

    public static double riverFailureRemove(InventoryItem[] inventory)
                            throws InventoryControlException {
        if (inventory == null) {
            throw new InventoryControlException("Inventory is null. Please start new game to fix problem.");
        }
            
        double quantity;
        double result;
        double sum = 0;

        for(InventoryItem item : inventory){
            quantity = item.getQuantityInStock();
            if (quantity > 0) {
                result = Math.floor(quantity * .2);
                sum += result;
                quantity *= .8;
                quantity = Math.ceil(quantity); 
                item.setQuantityInStock(quantity);
                
                 
            }

        }
        
        return sum;
    }
    
}
