/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Dresen_HP
 */
class PurchaseView extends View{
    
    public PurchaseView() {
        super("\n How many would you like to purchase?");
   
    }




    @Override
    public boolean doAction(String value) {
        int number;
        try {
            number = parseInt(value);
        } catch (NumberFormatException nf) {
            System.out.println("\nYou must enter a valid number. Try again or enter Q to quit.");
            getInput();
        }
    
        
    return false;
    
}}
