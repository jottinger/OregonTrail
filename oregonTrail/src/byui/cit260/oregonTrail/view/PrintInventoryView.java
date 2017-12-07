/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author jordan
 */
public class PrintInventoryView extends View {
     public PrintInventoryView() {
            super("\nPlease enter a location. You must enter a / after the directory name:");
    }

    @Override
    public boolean doAction(String filePath) {
        boolean done = false;
        String fileName = "current_inventory.txt";
        
        try (PrintWriter out = new PrintWriter(filePath + fileName)) {
        
        //print out header
        out.println("\n-----------------------------------");
        out.printf("%10s %15s", "Item", "Quantity");
        out.println("\n-----------------------------------");
        //print current inventory list
        InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
        for (InventoryItem item : inventory) {
            String name = item.getInventoryType().name();
            double quantity = item.getQuantityInStock();
            out.printf("%n%10s %15s", name, quantity);
           }
        //print closing footer
        out.println("\n-----------------------------------");
        
        this.console.println("\nFile saved to " + filePath + "/" + fileName);
    }    catch (FileNotFoundException ex) {
             ErrorView.display(this.getClass().getName(), "Unable to save file. Error:  " + ex.getMessage());

         } 
        InventoryView inventoryView = new InventoryView();
        inventoryView.display();
        
        return true;
     
    }
    

}
