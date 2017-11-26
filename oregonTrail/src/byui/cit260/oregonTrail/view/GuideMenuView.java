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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class GuideMenuView extends View {
    private String menu;
    private String promptMessage;

    public GuideMenuView() {
            super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Hire a Guide Menu                                 |"
                    +"\n----------------------------------------------------"
                    +"\nY - Hire a Guide"
                    +"\nN - Go without a Guide"
                    +"\nQ - Return to previous menu"
                    +"\n----------------------------------------------------");
    }

    @Override
    public boolean doAction(String menuOption) {
         menuOption = menuOption.toUpperCase(); //convert choice to upper case
         
         switch (menuOption) {
             
            case "Y":
                try {
                 this.setRiverGuideYes();
            } catch (InventoryControlException ex) {
                 System.out.println(ex.getMessage());
                    }
                break;
            case "N":
                this.setRiverGuideNo();   
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
            }
                 
        return false;
    }

    private void setRiverGuideYes() throws InventoryControlException {
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        double quantity = inventory[InventoryType.Guide.ordinal()].getQuantityInStock();
        if (quantity != 0) {
        System.out.println("\nYou already have a guide. You are ready to cross the river.");    
        } else {
            InventoryControl.addToInventory(InventoryType.Guide, 1);
            InventoryControl.subtractFromInventory(InventoryType.Money, 50);
            System.out.println("\nYou have hired a guide. You are ready to cross the river.");
            RiverMenuView riverMenuView = new RiverMenuView();
            riverMenuView.display();
    }}

    private void setRiverGuideNo() {
        RiverMenuView riverMenuView = new RiverMenuView();
        riverMenuView.display();
    }
}

        