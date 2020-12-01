/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.model.Occupation;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
class OccupationView extends View {

    // class instance variables
    private String occupationHelp;

    // constructor function called from displayNextView() in StartGameView
    public OccupationView() {
        super("\n"
                + "\n----------------------------------------------------"
                + "\n| Select an occupation                          |"
                + "\n----------------------------------------------------"
                + "\nB - Blacksmith"
                + "\nF - Farmer"
                + "\nM - Merchant"
                + "\nL - Learn about occupations"
                + "\nQ - Quit"
                + "\n----------------------------------------------------"
                + "\n"
                + "\nPlease Enter Your Choice");
        Occupation blacksmith = Occupation.getBlacksmith();
        Occupation farmer = Occupation.getFarmer();
        Occupation merchant = Occupation.getMerchant();
        this.occupationHelp = "\n**************************************************************"
                + "\n* Occupation Choices"
                + "\n*-------------------------------------------------------------"
                + "\n*                                                            "
                + "\n* B- " + blacksmith.getName()
                + "\n*    " + blacksmith.getDescription()
                + "\n*  "
                + "\n* F- " + farmer.getName()
                + "\n*    " + farmer.getDescription()
                + "\n*"
                + "\n* M- " + merchant.getName()
                + "\n*    " + merchant.getDescription()
                + "\n*"
                + "\n*"
                + "\n**************************************************************"
                + "\n";
    }

    public void displayOccupationHelp() {
        this.console.print(occupationHelp);
    }

    public boolean doAction(String value) { // called from displayOccupationview() in this class
        value = value.toUpperCase();
        Occupation occupation;
        switch (value) {
            case "B":
                occupation = Occupation.Blacksmith;
                this.saveOccupation(occupation);
                 {
                    try {
                        InventoryControl.addToInventory(InventoryType.Money, 100);
                    } catch (InventoryControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }
                }
                break;
            case "F":
                occupation = Occupation.Farmer;
                this.saveOccupation(occupation);
                break;
            case "M":
                occupation = Occupation.Merchant;
                this.saveOccupation(occupation);
                 {
                    try {
                        InventoryControl.addToInventory(InventoryType.Money, 200);
                    } catch (InventoryControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }
                }
                break;
            case "L":
                this.display();
                this.getInput();
                break;
            default:
                ErrorView.display(this.getClass().getName(), "Error reading input: Invalid selection *** Try again");
                break;

        }
        return false; // returns to displayOccoupationView() to repeat loop
    }

    private void saveOccupation(Occupation occupation) {
        try {
            GameControl.setOccupation(occupation);
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
        this.console.println("\n*************************************************"
                + "\n| Occupation chosen: " + OregonTrail.getPlayer().getOccupation().getName()
                + "\n************************************************");
        this.displayNextView();

    }

    private void displayNextView() {

        StartDateView startDateView = new StartDateView();
        startDateView.display();

    }
}
