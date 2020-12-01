/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Location;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class StartDateView extends View{
    
    // constructor function called from displayNextView() in StartGameView
    public StartDateView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Choose the day to start traveling                |" 
                    +"\n| Choice will affect weather and conditions        |"
                    +"\n----------------------------------------------------"
                    +"\nM - March 1"
                    +"\nJ - June 1"
                    +"\nS - September 1"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\nPlease enter your choice.");
    }

    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();
        int startDate = 1;
        
        switch (value) {
            case "M":
                startDate = 61;
                this.saveStartDate(startDate);
                break;
            case "J":
                startDate = 151;
                this.saveStartDate(startDate);
                break;
            case "S":
                startDate = 241;
                this.saveStartDate(startDate);
                break;
            default:
                ErrorView.display(this.getClass().getName(),"Error reading input: Invalid selection: Try again.");
                break;   
        }
        return false;
    } 

    private void displayNextView() {
        
            
            this.console.println("\n****************************************************"
                    + "\n You are almost ready to begin"
                    +"\n****************************************************"
                    +"\n----------------------------------------------------"
                    +"\n| Companions                                        |"
                    +"\n----------------------------------------------------"
                    +"\nYou: " + OregonTrail.getCurrentGame().getPlayer().getName()
                    +"\nCompanion 1: " + OregonTrail.getCurrentGame().getCompanion1()
                    +"\nCompanion 2: " + OregonTrail.getCurrentGame().getCompanion2()
                    +"\nCompanion 3: " + OregonTrail.getCurrentGame().getCompanion3()
                    +"\n----------------------------------------------------"
                    + "\n| Occupation chosen: " + OregonTrail.getPlayer().getOccupation().getName()
                    + "\n----------------------------------------------------"
                    + "\n| StartDate: MARCH 1;"
                    +"\n****************************************************"
                    +"\n"
                    +"\nIt's time to purchase supplies for the road.");
        try {
            PurchaseGoodsView purchaseGoods = new PurchaseGoodsView();
            purchaseGoods.display();

        } catch (InventoryControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return;
        }
    }

    private void saveStartDate(int startDate) {
        
        try {
            GameControl.setStartDate(startDate);
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return;
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            return;
        }
        this.findThisDay();
        
    }

    private void findThisDay() {        
        String calDate = "";
        try {
            calDate = GameControl.thisDay();
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
        this.console.println("\n*************************************************"
                          + "\n| StartDate: " + calDate
                          + "\n************************************************");
        this.displayNextView();
        
    }
}
