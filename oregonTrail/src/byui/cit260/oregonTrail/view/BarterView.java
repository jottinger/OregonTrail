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
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public BarterView() throws InventoryControlException {
        super("\n"
                    +"\n---------------------------------------------------------------"
                    +"\n| Available Inventory to Barter                               |"
                    +"\n---------------------------------------------------------------"
                    + InventoryControl.displayInventoryQuantityPrice()
                    +"\n---------------------------------------------------------------"
                    +"\n* H - Display barter help screen"
                    +"\n* Q - Close and return to Barter Main Menu"
                    +"\n---------------------------------------------------------------"
                    +"\n Enter selection to barter for that item (Q to quit this menu):");
    }
    

    @Override
    public boolean doAction(String choice) {
       int number = 0;
       InventoryType type;
       
       if (choice == "Q"){
           displayBarterHelp();
       }
       
        try {
            number = Integer.parseInt(choice);
            } catch (NumberFormatException nf) {
            System.out.println("\nPlease enter a valid number. "
            + " Try again");
            }
        
        if (number == 7) {
            System.out.println("\nYou can not purchase money. Enter another choice or enter Q to quit.");
            getInput();
        }
        if (number == 3) {
            System.out.println("\nYou cannot purchase a guide at this time. Make another selection.");
            getInput();
        }
        InventoryItem[] items = OregonTrail.getCurrentGame().getInventory();
        type = items[number].getInventoryType();
        choice = requestQuantity(type);
        
        try {
            boolean quantity = getPrice(type, choice);
        } catch (InventoryControlException ex) {
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    private String requestQuantity(InventoryType type) {
        Scanner keyboard = new Scanner(System.in); //get infile for keyboard
        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        
        while (!valid) { //loop while an invalid value is entered
            System.out.println("\nHow many " + type.name() + " would you like to purchase?"); // print out the message asking for name stored in class instance variable.
            
            value = keyboard.nextLine(); //get next line typed on keyboard and store in value
            value = value.trim(); //trim off leading and trailing blanks
            
            if (value.length() < 1) { //if value is blank print error message, starts loop again
                System.out.println("\nInvalid value: value cannot be blank");
                continue;
            }
            
            break; //end the loop
        }
        
        return value; 
    }

    private boolean getPrice(InventoryType type, String choice) throws InventoryControlException {
        int quantity = 0;
        int price = 0;
        try {
            quantity = parseInt(choice);
        } catch (NumberFormatException nf) {
            System.out.println("\nYou must enter a valid number. Try again or enter Q to quit.");
            requestQuantity(type);   
        }
        
        try {
            price = InventoryControl.calcBarterPrice(type, InventoryType.Money);
        } catch (InventoryControlException ex) {
            System.out.println(ex.getMessage());
        }
        String sale = confirmSale(type, quantity, price);
        boolean finalize = finalizeSale(type, quantity, price, sale);
       return false; 
    } 

    private String confirmSale(InventoryType type, int quantity, int price) {
        Scanner keyboard = new Scanner(System.in); //get infile for keyboard
        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        
        while (!valid) { //loop while an invalid value is entered
            System.out.println("\n" + quantity + " " + type.name() + " will cost $" + price * quantity + "."
        + "\nConfirm sale? Y/N"); // print out the message asking for name stored in class instance variable.
            
            value = keyboard.nextLine(); //get next line typed on keyboard and store in value
            value = value.trim(); //trim off leading and trailing blanks
            
            if (value.length() < 1) { //if value is blank print error message, starts loop again
                System.out.println("\nInvalid value: value cannot be blank");
                continue;
            }
            
            break; //end the loop
        }
        
        return value; 
    }

    private boolean finalizeSale(InventoryType type, int quantity, int price, String sale) throws InventoryControlException {
        sale = sale.toUpperCase();
        int result;
        switch (sale){
        
            case "Y":
                result = InventoryControl.barter(InventoryType.Money, type, quantity);
                

                if (result == -1) {
                    System.out.println("\nThere was an error completing the sale. Try again");
                    display();
                    break;
                } else if (result == 1) {
                    System.out.println("\nThis item is out of stock. Try again.");
                    display(); 
                    break;
                } else if (result == 2) {
                    System.out.println("\nYou do not have enough money to complete the sale. "
                            + "Purchase something else or press Q to exit menu.");
                    display(); 
                    break;
                } else if (result == 3) {
                    System.out.println("\nTransaction successful. "
                            + "Make another purchase or press Q to exit menu.");
                    String inventory = InventoryControl.displayInventoryQuantityPrice();
                    PurchaseGoodsView purchaseGoodsView = new PurchaseGoodsView(inventory);
                    purchaseGoodsView.display();
                    break;
                } else {
                    System.out.println("\nThere was an error completing the sale. Try again");
                    display();
                    break;
                }
            case "N":

                System.out.println("\nPurchase cancelled.");
                display();
            default:
                System.out.println("\nInvalid entry. Please enter Y or N.");
                confirmSale(type, quantity, price);
            
        } return false;
        
    } 

    private void displayBarterHelp() {
        System.out.println("*** displayBarterHelp() method called ***");
    }
        
    }
