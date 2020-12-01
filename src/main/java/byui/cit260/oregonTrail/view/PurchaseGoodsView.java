/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.FortScene;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Location;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class PurchaseGoodsView extends View {

    public PurchaseGoodsView() throws InventoryControlException {
        super("\n"
                + "\n----------------------------------------------------"
                + "\n| Player Inventory                                 |"
                + "\n----------------------------------------------------"
                + InventoryControl.displayInventoryQuantityPrice()
                + "\n"
                + "\nQ - Quit to game menu view"
                + "\n----------------------------------------------------"
                + "\n What would you like to purchase?");

    }

    @Override
    public void display() {  //called from main() in OregonTrail.java
        boolean done = false; // set flag to not done
        do {
            //prompt for and get player's name
            String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName

            if (value.toUpperCase().equals("Q")) // user wants to quit
            {
                try {
                    SceneView sceneView = new SceneView();
                    sceneView.display();//exit the game
                } catch (MapControlException ex) {
                    ErrorView.display(this.getClass().getName(), ex.getMessage());
                    return;
                }
            }

            //do the requested action and display the next view
            done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
        } while (!done);
    }

    @Override
    public boolean doAction(String value) {
        try {
            // set activity to done.
            Location location = MapControl.getCurrentLocation();
            location.getScene().setActivityDone(true);
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
        int number = 0;
        InventoryType type;

        try {
            number = parseInt(value);
        } catch (NumberFormatException nf) {
            ErrorView.display(this.getClass().getName(), "Error reading input: You must enter a valid number. \nTry again or enter Q to quit.");
            display();
        }
        if (number == 7) {
            ErrorView.display(this.getClass().getName(), "Error reading imput: You can not purchase money. \nEnter another choice or enter Q to quit.");
            display();
        } else if (number < 0 || number > 7) {
            ErrorView.display(this.getClass().getName(), "Error reading imput: Invalid entry. \nChoose a nubmer between 0-7or enter Q to quit.");
            display();
        } else if (number == 3) {
            ErrorView.display(this.getClass().getName(), "Error reading input: You should only purchase 1 guide at a time."
                    + "Guide will stay in inventory for 1 location.");
        }
        InventoryItem[] items = OregonTrail.getCurrentGame().getInventory();
        type = items[number].getInventoryType();
        boolean choice = requestQuantity(type);

        /*try {
            boolean quantity = getPrice(type, choice);
        } catch (InventoryControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }*/
        return false;
    }

    private boolean requestQuantity(InventoryType type) {

        String value = ""; //create variable value to be returned
        boolean valid = false; //initialize to not valid
        try {
            while (!valid) { //loop while an invalid value is entered
                this.console.println("\nHow many " + type.name() + " would you like to purchase?"); // print out the message asking for name stored in class instance variable.

                value = this.keyboard.readLine(); //get next line typed on keyboard and store in value
                value = value.trim(); //trim off leading and trailing blanks

                if (value.length() < 1) { //if value is blank print error message, starts loop again
                    ErrorView.display(this.getClass().getName(), "Error reading input: Invalid value: value cannot be blank");
                    continue;
                }
                if (value.toUpperCase().equals("Q")) // user wants to quit
                {
                    display();//exit the game
                }
                try {
                    boolean quantity = getPrice(type, value);
                } catch (InventoryControlException ex) {
                    this.console.println(ex.getMessage());
                }

                break; //end the loop
            }

        } catch (Exception e) {
            this.console.println("Error reading input: " + e.getMessage());
        }
        return valid;
    }

    private boolean getPrice(InventoryType type, String choice) throws InventoryControlException {
        int quantity = 0;
        int price = 0;
        try {
            quantity = parseInt(choice);
        } catch (NumberFormatException nf) {
            ErrorView.display(this.getClass().getName(), "Error reading input: You must enter a valid number. Try again or enter Q to quit.");
            requestQuantity(type);
        }

        try {
            price = InventoryControl.calcBarterPrice(type, InventoryType.Money);
        } catch (InventoryControlException ex) {
            ErrorView.display(this.getClass().getName(), "Error reading data: " + ex.getMessage());
        }
        String sale = confirmSale(type, quantity, price);
        boolean finalize = finalizeSale(type, quantity, price, sale);
        return false;
    }

    private String confirmSale(InventoryType type, int quantity, int price) {
        String value = ""; //create variable value to be returned
        
        boolean valid = false; //initialize to not valid
        try {
            while (!valid) {
                //loop while an invalid value is entered
                this.console.println("\n" + quantity + " " + type.name() + " will cost $" + price * quantity + "."
                        + "\nConfirm sale? Y/N"); // print out the message asking for name stored in class instance variable.

                value = this.keyboard.readLine(); //get next line typed on keyboard and store in value
                value = value.trim(); //trim off leading and trailing blanks

                if (value.length() < 1) { //if value is blank print error message, starts loop again
                    ErrorView.display(this.getClass().getName(), "Error reading input: Invalid value: value cannot be blank");
                    continue;

                }
                break; //end the loop

            }
        } catch (IOException ex) {
            ErrorView.display(this.getClass().getName(),
                    "Error reading input: " + ex.getMessage());
        }

        return value;
    }

    private boolean finalizeSale(InventoryType type, int quantity, int price, String sale) throws InventoryControlException {
        sale = sale.toUpperCase();
        int result = 0;
        switch (sale) {

            case "Y":
                try {
                    result = InventoryControl.barter(InventoryType.Money, type, quantity);
                } catch (InventoryControlException ex) {
                    ErrorView.display(this.getClass().getName(), "Error: " + ex.getMessage());
                    requestQuantity(type);

                }

                if (result == 1) {
                    ErrorView.display(this.getClass().getName(), "Error: This item is out of stock. Try again.");
                    display();
                    break;
                } else if (result == 2) {
                    ErrorView.display(this.getClass().getName(), "Error: You do not have enough money to complete the sale. "
                            + "\nPurchase something else or press Q to exit menu.");
                    display();
                    break;
                } else if (result == 3) {
                    this.console.println("\nTransaction successful. "
                            + "Make another purchase or press Q to exit menu.");
                    String inventory = "";

                    {
                        try {
                            inventory = InventoryControl.displayInventoryQuantityPrice();
                        } catch (InventoryControlException ex) {
                            ErrorView.display(this.getClass().getName(),
                                    "Error reading input: " + ex.getMessage());
                        }
                    }
                    PurchaseGoodsView purchaseGoodsView = new PurchaseGoodsView();
                    purchaseGoodsView.display();
                    break;
                } else {
                    ErrorView.display(this.getClass().getName(), "There was an error completing the sale. Try again");
                    display();
                    break;
                }
            case "N":

                this.console.println("\nPurchase cancelled.");
                display();
            default:
                ErrorView.display(this.getClass().getName(), "Error reading input: Invalid entry. Please enter Y or N.");
                confirmSale(type, quantity, price);

        }
        return false;

    }

}
